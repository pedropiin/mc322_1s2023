import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private String cnpj;
    private LocalDate dataFundacao;
    private ArrayList<Frota> listaFrota;

    public ClientePJ(String nome, 
                    String endereco, 
                    String telefone,
                    String email,
                    String cnpj,
                    LocalDate dataFundacao, 
                    ArrayList<Frota> listaFrota) {
        super(nome, endereco, telefone, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.listaFrota = listaFrota;
    }

    @Override
    public String toString() {
        String all = "Nome: " + getNome() +
                "\nEndereço: " + getEndereco() +
                "\nNúmero de Telefone: " + getTelefone() +
                "\nEmail: " + getEmail() + 
                "\nCNPJ: " + getCNPJ() +
                "\nData da Fundação: " + getDataFundacao();

        return all;
    }

    public String getCNPJ() {
        return this.cnpj;
    }

    public void setCNPJ(String novoCNPJ) {
        this.cnpj = novoCNPJ;
    }

    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate novaDataFundacao) {
        this.dataFundacao = novaDataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return this.listaFrota;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    /*
     * Método que cálcula o valor de cobrado de um cliente
     * por parte da seguradora, levando em conta taxas
     * associadas à quantidade de funcionários da empresa.
     */
    public double calculaScore() {
        int quantidadeCarros = this.getListaVeiculos().size();
        double score = CalcSeguro.VALOR_BASE.getValor() *
                                (1 + (quantidadeFuncionarios) / 100) *
                                quantidadeCarros;
        return score;
    }

    public boolean cadastrarFrota() {

    }

    public boolean atualizarFrota() {

    }

    public boolean getVeiculosPorFrota() {
        
    }
}