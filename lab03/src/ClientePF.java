package mc322_1s2023;

import java.util.Date;

public class ClientePF extends Cliente {
    private String cpf;
    private Date dataNascimento;

    public ClientePF(String nome, String endereco, 
                    Date dataLicenca, String educacao,
                    String genero, String classeEconomica,
                    ArrayList<Veiculo> listaVeiculos,
                    String cpf, Date dataNascimento) {

        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos, cpf, dataNascimento);
        this.cpf = cpf;
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

    public String getCPF() {
        return this.cpf;
    }

    public void setCPF(String novoCPF) {
        this.cpf = novoCPF;
    }

    public Date getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Date novaDataNascimento) {
        this.dataFundacao = novaDataNascimento;
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
     * dos dígitos verificadores do CPF, de modo a garantir
     * que se trata de um CPF verdadeiro
     */
    public boolean digitosCpfValidos(String s) {
        int soma = 0;
        int fatorInicial = 10;
        int resto = 0;
        for (int i = 0; i < s.length() - 2; i++) {
            soma += (Character.getNumericValue(s.charAt(i)) * (fatorInicial - i));
        }

        resto = soma % 11;
        if (resto < 2) {
            if (Character.getNumericValue(s.charAt(9)) != 0) {
                return false;
            }
        } else {
            if (Character.getNumericValue(s.charAt(9)) != (11 - resto)) {
                return false;
            }
        }

        soma = 0;
        fatorInicial = 11;
        for (int i = 0; i < s.length() - 1; i++) {
            soma += Character.getNumericValue(s.charAt(i)) * (fatorInicial - i);
        }

        resto = soma % 11;
        if (resto < 2) {
            if (Character.getNumericValue(s.charAt(10)) != 0) {
                return false;
            }
        } else {
            if (Character.getNumericValue(s.charAt(10)) != (11 - resto)) {
                return false;
            }
        }

        return true;
    }


    /*
     * Método principal no processo de validar o CPF, pois
     * chama todos os outros métodos relacionados, e verifica
     * outras características necessárias, como o tamanho
     * do CPF
     */
    public boolean validarCPF(String cpf) {
        String cpf1 = cpf.replaceAll("\\.", "");
        String cpfAlterado = cpf1.replaceAll("\\-", "");

        if (!charsNumericos(cpfAlterado)) {
            return false;
        }

        if (cpfAlterado.length() != 11) {
            return false;
        }

        if (charsIguais(cpfAlterado)) {
            return false;
        }

        if (!digitosCpfValidos(cpfAlterado)) {
            return false;
        }
        return true;
    }
}