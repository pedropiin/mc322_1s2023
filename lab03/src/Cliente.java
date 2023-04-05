package mc322_1s2023;

import java.util.Date;

public class Cliente {
	private String nome;
	private String endereco;
	private Date dataLicenca;
	private String educacao;
	private String genero;
	private String classeEconomica;
	private ArrayList<Veiculo> listaVeiculos;
	
	//Constructor do cliente
	public Cliente(String nome, String endereco, Date dataLicenca,
					String educacao, String genero, String classeEconomica,
					ArrayList<Veiculo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.educacao = educacao;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.dataLicenca = dataLicenca;
		this.listaVeiculos = listaVeiculos;
	}

	//Método padrão toString
	public String toString() {
		String all ="Nome: " + getNome() + 
					"\nEndereço: " + getEndereco() +
					"\nEducação: " + getEducacao() + 
					"\nGenero: " + getGenero() +
					"\nClasse Econômica: " + getClasseEconomica() + 
					"\nData da Licença: " + getDataLicenca();
		
		return all;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String novoEndereco) {
		this.endereco = novoEndereco;
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

	public ArrayList<Veiculo> getListaVeiculos() {
		return this.listaVeiculos;
	}

	// TODO: SETTER DA LISTA VEICULOS

	public Date getDataLicenca() {
		return this.dataLicenca;
	}

	public void setDataLicenca(Date novaData) {
		this.dataLicenca = novaData;
	}

	//INÍCIO DOS MÉTODOS NÃO PADRÕES;

	/*
	Método que recebe uma string e verifica se todos 
	os seus chars são iguais, devolvendo um booleano
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
	Método que aplica o algoritmo padrão de validação
	dos dígitos verificadores do CPF, de modo a garantir
	que se trata de um CPF verdadeiro
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
	Método que verifica se todos os dítigos de um 
	CPF recebido como parâmetro são númericos
	 */
	public boolean charsNumericos(String cpf) {
		for (int i = 0; i < cpf.length(); i++) {
			if (!Character.isDigit(cpf.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/*
	Método principal no processo de validar o CPF, pois 
	chama todos os outros métodos relacionados, e verifica
	outras características necessárias, como o tamanho
	do CPF
	 */
	public boolean validarCPF(String cpf) {
		String cpf1 = cpf.replaceAll("\\.","");
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
