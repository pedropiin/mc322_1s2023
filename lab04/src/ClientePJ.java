import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private String cnpj;
    private LocalDate dataFundacao;
    private int quantidadeFuncionarios;

    public ClientePJ(String nome, String endereco, 
                    ArrayList<Veiculo> listaVeiculos, String cnpj,
                    LocalDate dataFundacao, double valorSeguro,
                    int quantidadeFuncionarios) {
        super(nome, endereco, listaVeiculos, valorSeguro);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.quantidadeFuncionarios = quantidadeFuncionarios;
    }

    @Override
    public String toString() {
        String all = "Nome: " + getNome() +
                "\nEndereço: " + getEndereco() +
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

    public int getQuantidadeFuncionarios() {
        return this.quantidadeFuncionarios;
    }
    
    public void setQuantidadeFuncionarios(int novaQuantidadeFuncionarios) {
        this.quantidadeFuncionarios = novaQuantidadeFuncionarios;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    public double calculaScore() {
        int quantidadeCarros = this.getListaVeiculos().size();
        double score = CalcSeguro.VALOR_BASE.getValor() *
                                (1 + (quantidadeFuncionarios) / 100) *
                                quantidadeCarros;
        return score;
    }
}