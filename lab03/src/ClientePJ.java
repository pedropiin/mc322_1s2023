package mc322_1s2023;

import java.util.Date;

public class ClientePJ extends Cliente {
    private String cnpj;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, Date dataLicenca,
                    String educacao, String genero, String classeEconomica, 
                    ArrayList<Veiculo> listaVeiculos, String cnpj,
                    Date dataFundacao) {
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        String all = "Nome: " + getNome() +
                "\nEndereço: " + getEndereco() +
                "\nData da Licença: " + getDataLicenca() +
                "\nEducação: " + getEducacao() +
                "\nGenero: " + getGenero() +
                "\nClasse Econômica: " + getClasseEconomica() +
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

    public Date getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(Date novaDataFundacao) {
        this.dataFundacao = novaDataFundacao;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES
}