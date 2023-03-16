package mc322_1s2023;

public class Cliente {
	private int idade;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String endereco;
	
	public Cliente(int idade, String nome, String cpf, String dataNascimento, String endereco) {
		this.idade = idade;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}

	public boolean charsIguais(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(0) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public boolean digitosCpfValidos(String s) {
		int soma = 0;
		int fatorInicial = 10;
		int resto = 0;
		for (int i = 0; i < s.length() - 2; i++) {
			soma += Character.getNumericValue(s.charAt(i)) * (fatorInicial - i);
		}

		resto = soma % 11;
		if (resto < 10) {
			if (s.charAt(9) != resto) {
				return false;
			}
		} else {
			if (s.charAt(9) != 0) {
				return false;
			}
		}

		soma = 0;
		fatorInicial = 11;
		for (int i = 0; i < s.length - 1; i++) {
			soma += Character.getNumericValue(s.charAt(i)) * (fatorInicial - i);
		}

		resto = soma % 11;
		if (resto < 10) {
			if (s.charAt(10) != resto) {
				return false;
			}
		} else {
			if (s.charAt(10) != 0) {
				return false;
			}
		}

		return true;
	}

	public boolean validarCPF(String cpf) {
		String cpfAlterado = cpf.replaceAll(".","");

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
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento; 
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
