import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class SeguroPJ extends Seguro {
    public Frota frota;
    public ClientePJ cliente;

    public SeguroPJ(int id,
                    int valorMensal,
                    LocalDate dataInicio,
                    LocalDate dataFim,
                    Seguradora seguradora,
                    ArrayList<Sinistro> listaSinistros,
                    ArrayList<Condutor> listaCondutores, 
                    Frota frota,
                    ClientePJ cliente) {
        super(valorMensal, dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.frota = frota;
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        String all = "ID: " + getId() +
                    "\nValor Mensal: " + getValorMensal() +
                    "\nData de Início: " + getDataInicio() +
                    "\nData de Fim: " + getDataFim() +
                    "\nSeguradora: " + getSeguradora() +
                    "\nVeiculo: " + getVeiculo() + 
                    "\nCliente Responsável: " + getCliente();

        return all;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo novoVeiculo) {
        this.veiculo = novoVeiculo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    public boolean autorizarCondutor() {

    }

    public boolean desautorizarCondutor() {

    }

    public void gerarSinistro() {

    }

    public double calcularValor() {
        int quantidadeFuncionarios = cliente.getQuantidadeFuncs();
        int quantidadeVeiculos = 0;
        for (int i = 0; i < cliente.getListaFrota().size(); i++) {
            quantidadeVeiculos += cliente.getListaFrota().get(i).getListaVeiculos().size();
        }
        int anosPosFundacao = Period.between(cliente.getDataFundacao(), LocalDate.now()).getYears();
        int quantidadeSinistrosCliente = getListaSinistros().size();
        int quantidadeSinistrosCondutor = 0;
        for (int i = 0; i < getListaCondutores().size(); i++) {
            quantidadeSinistrosCondutor += getListaCondutores().get(i).getListaSinistros().size();
        }
        double valor = 0;

        valor = CalcSeguro.VALOR_BASE.getValor() * 
                (10 + (quantidadeFuncionarios) / 10) * 
                (1 + 1 / (quantidadeVeiculos + 2)) * 
                (1 + 1/ (anosPosFundacao + 2)) *
                (2 + quantidadeSinistrosCliente / 10) *
                (5 + quantidadeSinistrosCondutor / 10);

        return valor;
    }
}