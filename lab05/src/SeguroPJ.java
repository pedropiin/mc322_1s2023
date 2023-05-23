import java.time.LocalDate;
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
        super(id, valorMensal, dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
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

    public void calcularValor() {
        
    }
}