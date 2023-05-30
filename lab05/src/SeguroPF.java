import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(int valorMensal,
                    LocalDate dataInicio,
                    LocalDate dataFim,
                    Seguradora seguradora,
                    ArrayList<Sinistro> listaSinistros,
                    ArrayList<Condutor> listaCondutores, 
                    Veiculo veiculo,
                    ClientePF cliente) {
        super(valorMensal, dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
        this.veiculo = veiculo;
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

    public double calcularValor() {
        int idadeCliente = Period.between(this.cliente.getDataNascimento(), LocalDate.now()).getYears();
        int quantidadeVeiculos = cliente.getListaVeiculos().size();
        int quantidadeSinistrosCliente = getListaSinistros().size();
        int quantidadeSinistrosCondutor = 0;
        for (int i = 0; i < getListaCondutores().size(); i++) {
            quantidadeSinistrosCondutor += getListaCondutores().get(i).getListaSinistros().size();
        }
        double valor = 0;

        valor = CalcSeguro.VALOR_BASE.getValor() *
                getFatorIdade(idadeCliente) *
                (1 + 1 / (quantidadeVeiculos / 2)) *
                (2 + quantidadeSinistrosCliente / 10) *
                (5 + quantidadeSinistrosCondutor / 10);

        return valor;
    }

    public double getFatorIdade(int idadeCliente) {
        if (idadeCliente < 30) {
            return CalcSeguro.FATOR_18_30.getValor();
        } else if (idadeCliente >= 30 && idadeCliente <= 60) {
            return CalcSeguro.FATOR_30_60.getValor();
        } else {
            return CalcSeguro.FATOR_60_90.getValor();
        }
    }
}