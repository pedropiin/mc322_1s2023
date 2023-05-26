import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Seguro {
    Scanner scan = new Scanner(System.in);
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
        this.valorMensal = novoValorMensal;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate novaDataInicio) {
        this.dataInicio = novaDataInicio;
    } 

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate novaDataFim) {
        this.dataFim = novaDataFim;
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

    public abstract Cliente getCliente();

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    public boolean autorizarCondutor() {
        int indiceCondutor = escolheCondutor();
        if (indiceCondutor >= 0) {
            getListaCondutores().get(indiceCondutor).setAutorizacao(true);
            return true;
        }
        return false;
    }

    public abstract double calcularValor();

    public boolean desautorizarCondutor() {
        int indiceCondutor = escolheCondutor();
        if (indiceCondutor >= 0) {
            getListaCondutores().get(indiceCondutor).setAutorizacao(false);
            return true;
        }
        return false;
    }

    public int escolheCondutor() {
        int entrada;
        if (getListaCondutores().size() == 0) {
            System.out.println("Não há nenhum condutor cadastrado no seguro em questão.");
            return -1;
        } else {
            System.out.println("Selecione o condutor desejado.");
            for (int i = 0; i < getListaCondutores().size(); i++) {
                System.out.println("(" + i + ") - " + getListaCondutores().get(i).getNome());
            }
            entrada = scan.nextInt();
            scan.nextLine();
            return entrada;
        }
    }

    /*
     * Método que gera um id aleatório para cada objeto
     * da classe
     */
    public int geraId() {
        Random rand = new Random();
        int limite = 999999999;
        return rand.nextInt(limite);
    }

    public void gerarSinistro() {
        int indiceCondutor = escolheCondutor();
        String endereco;
        System.out.println("Por favor digite o endereço do acidente.");
        endereco = scan.nextLine();
        Sinistro novoSinistro = new Sinistro(LocalDate.now(), endereco, getListaCondutores().get(indiceCondutor), this);
        listaSinistros.add(novoSinistro);
    }
}
