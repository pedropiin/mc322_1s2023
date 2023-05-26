public abstract class Cliente {
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	
	//Constructor do cliente
	public Cliente(String nome, 
					String endereco,
					String telefone,
					String email) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}

	//Método padrão toString
	public String toString() {
		String all ="Nome: " + getNome() + 
					"\nEndereço: " + getEndereco() +
					"\nNúmero de Telefone: " + getTelefone() +
					"\nEmail: " + getEmail();

		return all;
	}

	public String getNome() {
		return this.nome;
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

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String novoTelefone) {
		this.telefone = novoTelefone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}
}
