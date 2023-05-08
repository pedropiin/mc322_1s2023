public static class Validacao {
    /*
     * Método que apenas retorna o resultado
     * do método de mesmo nome (charsIguais) da 
     * classe pai (Cliente.java)
     */
    public boolean charsIguais(String s) {
        for (int i = 0; i < s.length(); i++) {
			if (s.charAt(0) != s.charAt(i)) {
				return false;
			}
		}
		return true;
    }

    /*
     * Método que apenas retorna o resultado
     * do método de mesmo nome (charsNumericos) da 
     * classe pai (Cliente.java)
     */
    public boolean charsNumericos(String s) {
        for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
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

        boolean cpfValido = true;

        if (!charsNumericos(cpfAlterado)) {
            cpfValido = false;
        }

        if (cpfAlterado.length() != 11) {
            cpfValido = false;
        }

        if (charsIguais(cpfAlterado)) {
            cpfValido = false;
        }

        if (!digitosCpfValidos(cpfAlterado)) {
            cpfValido = false;
        }

        if (cpfValido) {
            System.out.println("O CPF é valido.");
        } else {
            System.out.println("O CPF é inválido");
        }
        return cpfValido;
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