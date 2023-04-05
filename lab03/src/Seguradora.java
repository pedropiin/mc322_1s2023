package mc322_1s2023;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Cliente> listaClientes;
	
	//Constructor da classe Seguradora
	public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistros, ArrayList<Cliente> listaClientes) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSinistros = listaSinistros;
		this.listaClientes = listaClientes;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String novoTelefone) {
		this.telefone = novoTelefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String novoEndereco) {
		this.endereco = novoEndereco;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return this.listaSinistros;
	}

	//TODO: SETTER DA LISTA SINISTROS

	public ArrayList<Cliente> getListaClientes() {
		return this.listaClientes;
	}

	//TODO: SETTER DA LISTA CLIENTES

	//INÍCIO DOS MÉTODOS NÃO PADRÕES

	public boolean cadastrarCliente(Cliente cliente) {

	}

	public boolean removerCliente(String cliente) {

	}

	public ArrayList<Cliente> listarClientes(String tipoCliente) {

	}

	public boolean gerarSinistro() {

	}

	public boolean visualizarSinistro(String cliente) {

	}

	public ArrayList<Sinistro> listarSinistros() {

	}
}
