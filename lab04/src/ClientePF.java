import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private String educacao;
    private String genero;
    private String classeEconomica;
    private String cpf;
    private LocalDate dataNascimento;
    private LocalDate dataLicenca;

    public ClientePF(String nome, String endereco, 
                    LocalDate dataLicenca, String educacao,
                    String genero, String classeEconomica,
                    ArrayList<Veiculo> listaVeiculos,
                    String cpf, LocalDate dataNascimento, double valorSeguro) {

        super(nome, endereco, listaVeiculos, valorSeguro);
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.cpf = cpf;
        this.dataLicenca = dataLicenca;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        String all = "Nome: " + getNome() +
                "\nEndereço: " + getEndereco() +
                "\nData da Licença: " + getDataLicenca() +
                "\nEducação: " + getEducacao() +
                "\nGenero: " + getGenero() +
                "\nClasse Econômica: " + getClasseEconomica() +
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

    public String getClasseEconomica() {
        return this.classeEconomica;
    }

    public void setClasseEconomica(String novaClasseEconomica) {
        this.classeEconomica = novaClasseEconomica;
    }

    public LocalDate getDataLicenca() {
        return this.dataLicenca;
    }

    public void setDataLicenca(LocalDate novaData) {
        this.dataLicenca = novaData;
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
}