import java.time.LocalDate;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private String cnpj;
    private LocalDate dataFundacao;

    public ClientePJ(String nome, String endereco, 
                    ArrayList<Veiculo> listaVeiculos, String cnpj,
                    LocalDate dataFundacao) {
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
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

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    /*
     * Método que apenas retorna o resultado
     * do método de mesmo nome (charsIguais) da 
     * classe pai (Cliente.java)
     */
    public boolean charsIguais(String s) {
        return super.charsIguais(s);
    }

    /*
     * Método que apenas retorna o resultado
     * do método de mesmo nome (charsNumericos) da 
     * classe pai (Cliente.java)
     */
    public boolean charsNumericos(String s) {
        return super.charsNumericos(s);
    }

    /*
     * Método que aplica o algoritmo padrão de validação
     * dos dígitos verificadores do CNPJ, de modo a garantir
     * que se trata de um CNPJ verdadeiro
     */
    public boolean digitosCnpjValidos(String s) {
        int soma = 0;
        int fatorInicial = 5;
        int resto = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            if ((fatorInicial - i) == 1) {
                fatorInicial += 8;
            }
            soma += Character.getNumericValue(s.charAt(i)) * (fatorInicial - i);
        }

        resto = soma % 11;
        if (resto < 2) {
            if (Character.getNumericValue(s.charAt(12)) != 0) {
                return false;
            }
        } else {
            if (Character.getNumericValue(s.charAt(12)) != (11 - resto)) {
                return false;
            }
        }

        soma = 0;
        fatorInicial = 6;
        for (int i = 0; i < s.length() - 1; i++) {
            if ((fatorInicial - i) == 1) {
                fatorInicial += 8;
            }
            soma += Character.getNumericValue(s.charAt(i)) * (fatorInicial - i);
        }

        resto = soma % 11;
        if (resto < 2) {
            if (Character.getNumericValue(s.charAt(13)) != 0) {
                return false;
            }
        } else {
            if (Character.getNumericValue(s.charAt(13)) != (11 - resto)) {
                return false;
            }
        }

        return true;
    }


    /*
     * Método principal no processo de validar o CNPJ, pois
     * chama todos os outros métodos relacionados, e verifica
     * outras características necessárias, como o tamanho
     * do CNPJ
     */
    public boolean validarCNPJ(String cnpj) {
        String cnpj1 = cnpj.replaceAll("\\.", "");
        String cnpj2 = cnpj1.replaceAll("\\-", "");
        String cnpjAlterado = cnpj2.replaceAll("\\/", "");

        boolean cnpjValido = true;

        if (!charsNumericos(cnpjAlterado)) {
            cnpjValido = false;
        }

        if (cnpjAlterado.length() != 14) {
            cnpjValido = false;
        }

        if (charsIguais(cnpjAlterado)) {
            cnpjValido = false;
        }

        if (!digitosCnpjValidos(cnpjAlterado)) {
            cnpjValido = false;
        }

        if (cnpjValido) {
            System.out.println("O CNPJ é válido.");
        } else {
            System.out.println("O CNPJ é inválido.");
        }
        return cnpjValido;
    }
}