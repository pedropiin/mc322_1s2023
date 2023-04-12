import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

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

	public ArrayList<Cliente> getListaClientes() {
		return this.listaClientes;
	}

	//INÍCIO DOS MÉTODOS NÃO PADRÕES

	public boolean cadastrarCliente(Cliente cliente) {
		for (Cliente element : listaClientes) {
			if (element.getNome() == cliente.getNome()) {
				System.out.println("O cliente " + cliente.getNome() + "já esta cadastrádo. ");
				return false;
			}
		}
		listaClientes.add(cliente);
		System.out.println("Cliente " + cliente.getNome() + " cadastrado.");

		return true;
	}

	public boolean removerCliente(String cliente) {
		for (Cliente element : listaClientes) {
			if (element.getNome() == cliente) {
				listaClientes.remove(element);
				System.out.println("A remoção do cliente " + cliente + " foi bem sucedida.");
				return true;
			}
		}
		System.out.println("O cliente " + cliente + "não existe.");
		return false;
	}

	public ArrayList<Cliente> listarClientes(String tipoCliente) {
		ArrayList<Cliente> listaClientesTipo = new ArrayList<Cliente>();
		for (Cliente clienteCadastrado : listaClientes) {
			if (clienteCadastrado instanceof ClientePF) {
				if (tipoCliente == "PF" || tipoCliente == "pf") {
					System.out.println(clienteCadastrado);
					listaClientesTipo.add(clienteCadastrado);
				} else {
					continue;
				}
			} else if (clienteCadastrado instanceof ClientePJ) {
				if (tipoCliente == "PJ" || tipoCliente == "pj") {
					System.out.println(clienteCadastrado);
					listaClientesTipo.add(clienteCadastrado);
				} else {
					continue;
				}
			}
		}
		return listaClientesTipo;
	}

	public boolean gerarSinistro(Veiculo veiculo, Cliente cliente) {
		Random rand = new Random();
		int ano = 2023;
		int mes = rand.nextInt(12 - 1) + 1;
		int dia = rand.nextInt(28 - 1) + 1;

		LocalDate dataSinistro = LocalDate.of(ano, mes, dia);
		Sinistro novoSinistro = new Sinistro(dataSinistro,
											"Rua do Acidente 666",
											this,
											veiculo,
											cliente);
		this.listaSinistros.add(novoSinistro);

		return true;
	}

	public boolean visualizarSinistro(String cliente) {
		if (listaSinistros.size() == 0) {
			System.out.println("Não há sinistros registrados no nome desse cliente.");
			return false;
		} else {
			int numSinistrosCliente = 0;
			for (Sinistro elementSin : listaSinistros) {
				if (elementSin.cliente.getNome() == cliente) {
					System.out.println(elementSin);
					numSinistrosCliente++;
				}
			}
			if (numSinistrosCliente > 0) {
				return true;
			} else {
				System.out.println("O cliente não possui sinistros.");
				return false;
			}
		}
	}

	public ArrayList<Sinistro> listarSinistros() {
		for (int i = 0; i < this.listaSinistros.size(); i++) {
			System.out.println(listaSinistros.get(i));
			System.out.println('\n');
		}
		return this.listaSinistros;
	}
}
