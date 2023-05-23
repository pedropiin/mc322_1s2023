import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class Seguro {
    private final int id;
    private int valorMensal;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;

    public Seguro(int valorMensal,
                LocalDate dataInicio,
                LocalDate dataFim,
                Seguradora seguradora,
                ArrayList<Sinistro> listaSinistros,
                ArrayList<Condutor> listaCondutores) {
        this.id = geraId();
        this.valorMensal = valorMensal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = listaSinistros;
        this.listaCondutores = listaCondutores;
    }

    public String toString() {
        String all = "ID: " + getId() + 
                    "\nValor Mensal: " + getValorMensal() + 
                    "\nData de Início: " + getDataInicio() +
                    "\nData de Fim: " + getDataFim() + 
                    "\nSeguradora: " + getSeguradora();
        
        return all;
    }

    public int getId() {
        return this.id;
    }

    public int getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(int novoValorMensal) {
        this.valorMensal = valorMensal;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate novaDataInicio) {
        this.dataInicio = dataInicio;
    } 

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate novaDataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    /*
     * Método que gera um id aleatório para cada objeto
     * da classe
     */
    public int geraId() {
        Random rand = new Random();
        int limite = 999999999;
        return rand.nextInt(limite);
    }

    public void autorizarCondutor() {

    }

    public void desautorizarCondutor() {

    }

    public void calcularValor() {

    }

    public void gerarSinistro() {

    }
}
