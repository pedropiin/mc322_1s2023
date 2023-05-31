import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public abstract class Seguro {
    Scanner scan = new Scanner(System.in);
    private final int id;
    private double valorMensal;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;

    public Seguro(LocalDate dataInicio,
                LocalDate dataFim,
                Seguradora seguradora,
                ArrayList<Sinistro> listaSinistros,
                ArrayList<Condutor> listaCondutores) {
        this.id = geraId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = listaSinistros;
        this.listaCondutores = listaCondutores;
        this.valorMensal = 0;
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

    public double getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(double novoValorMensal) {
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

    /*
     * Método que altera o status do condutor para autorizado
     */
    public boolean autorizarCondutor() {
        int indiceCondutor = escolheCondutor();
        if (indiceCondutor >= 0) {
            getListaCondutores().get(indiceCondutor).setAutorizacao(true);
            return true;
        }
        return false;
    }

    /*
     * Método que solicita e recebe todas as informações
     * necessárias para um novo condutor. Assim, isntancia
     * um objeto dessa classe e o adiciona na lista de condutores
     * do seguro em questão
     */
    public void cadastrarCondutor() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Digite o nome do novo condutor: ");
        String nome = scan.nextLine();
        if (!Validacao.apenasChars(nome)) {
            System.out.println("Nomes não podem conter caracteres além das letras e espaços. Por favor tente novamente.");
        } else {
            System.out.println("Digite o cpf do novo condutor: ");
            String cpf = scan.nextLine();
            if (!Validacao.validarCPF(cpf)) {
                System.out.println("Por favor verifique os dígitos inseridos e tente novamente.");
            } else { 
                System.out.println("Digite o telefone do novo condutor: ");
                String telefone = scan.nextLine();
                System.out.println("Digite o endereço do novo condutor: ");
                String endereco = scan.nextLine();
                System.out.println("Digite o email do novo condutor: ");
                String email = scan.nextLine();
                System.out.println("Digite a data de nascimento do novo condutor: ");
                LocalDate dataNascimento = LocalDate.parse(scan.nextLine(), dtf);

                Condutor novoCondutor = new Condutor(cpf, nome, telefone, endereco, email, dataNascimento, new ArrayList<Sinistro>());
                getListaCondutores().add(novoCondutor);
                System.out.println("Novo condutor cadastrado com sucesso.");
            }
        }
    }

    /*
     * Método abstrato que será utilizado para calcular
     * o valor de cada seguro
     */
    public abstract double calcularValor();

    /*
     * Método que altera o status do condutor para desautorizado
     */
    public boolean desautorizarCondutor() {
        int indiceCondutor = escolheCondutor();
        if (indiceCondutor >= 0) {
            getListaCondutores().get(indiceCondutor).setAutorizacao(false);
            return true;
        }
        return false;
    }

    /*
     * Método que mostra ao usuário todas os condutor cadastradas
     * no cliente e retorna um inteiro que representa
     * o índice do condutor que o usuário deseja utilizar
     */
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

    /*
     * Método que gera um novo sinistro, solicitado ao usuário
     * todas as informações necessárias. Por fim, o adiciona
     * na lista de sinistros do seguro em questão
     */
    public void gerarSinistro() {
        int indiceCondutor = escolheCondutor();
        if (indiceCondutor != -1) {
            String endereco;
            System.out.println("Por favor digite o endereço do acidente.");
            endereco = scan.nextLine();
            Sinistro novoSinistro = new Sinistro(LocalDate.now(), endereco, getListaCondutores().get(indiceCondutor), this);
            listaSinistros.add(novoSinistro);
        }
    }

    /*
     * Método que remove um condutor da lista de condutores
     * do seguro em questão
     */
    public void removerCondutor() {
        int indiceCondutor = escolheCondutor();
        if (indiceCondutor >= 0) {
            getListaCondutores().remove(indiceCondutor);
        }
    }
}
