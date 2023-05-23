import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private String educacao;
    private String genero;
    private String cpf;
    private LocalDate dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome, 
                    String endereco,
                    String telefone,
                    String email,  
                    String educacao,
                    String genero, 
                    ArrayList<Veiculo> listaVeiculos,
                    String cpf, 
                    LocalDate dataNascimento) {

        super(nome, endereco, telefone, email);
        this.educacao = educacao;
        this.genero = genero;
        this.listaVeiculos = listaVeiculos;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        String all = "Nome: " + getNome() +
                "\nEndereço: " + getEndereco() +
                "\nNúmero de Telefone: " + getTelefone() + 
                "\nEmail: " + getEmail() +
                "\nGenero: " + getGenero() +
                "\nGrau de Educação: " + getEducacao() +
                "\nCPF: " + getCPF() +
                "\nData de Nascimento: " + getDataNascimento();

        return all;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String novaEducacao) {
        this.educacao = novaEducacao;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String novoGenero) {
        this.genero = novoGenero;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public String getCPF() {
        return this.cpf;
    }

    public void setCPF(String novoCPF) {
        this.cpf = novoCPF;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate novaDataNascimento) {
        this.dataNascimento = novaDataNascimento;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    /*
     * Método que cálcula o valor de cobrado de um cliente
     * por parte da seguradora, levando em conta taxas
     * associadas a sua idade.
     */
    public double calculaScore() {
        int idadeCliente = Period.between(dataNascimento, LocalDate.now()).getYears();
        int quantidadeCarros = this.getListaVeiculos().size();
        double score = 0;

        if (18 <= idadeCliente && idadeCliente < 30) {
            score = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_18_30.getValor() * quantidadeCarros;
        } else if (30 <= idadeCliente && idadeCliente < 60) {
            score = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_30_60.getValor() * quantidadeCarros;
        } else if (60 <= idadeCliente && idadeCliente <= 90) {
            score = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_60_90.getValor() * quantidadeCarros;
        }
        
        return score;
    }

    public boolean cadastrarVeiculo() {

    }

    public boolean removerVeiculo() {
        
    }
}