import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

	public String toString() {
		String all = "Nome: " + this.getNome() +
					"\nTelefone: " + this.getTelefone() +
					"\nEmail: " + this.getEmail() + 
					"\nEndereço: " + this.getEndereco();

		return all;
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

	/*
	 * Método que recebe um objeto cliente e o adiciona
	 * na listaClientes. Antes, passa por toda a lista 
	 * se certificando de que tal cliente já não esta 
	 * cadastrado.
	 */
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

	/*
	 * Mètodo que recebe o nome de um cliente e passa 
	 * por toda a listaClientes, procurando um cliente
	 * com tal nome para remove-lo
	 */
	public boolean removerCliente(String cliente) {
		for (Cliente element : listaClientes) {
			if (element.getNome() == cliente) {
				for (int i = 0; i < listaSinistros.size(); i++) {
					if (listaSinistros.get(i).cliente.getNome() == cliente) {
						listaSinistros.remove(listaSinistros.get(i));
					}
				}
				listaClientes.remove(element);
				System.out.println("A remoção do cliente " + cliente + " foi bem sucedida.");
				return true;
			}
		}
		System.out.println("O cliente " + cliente + "não existe.");
		return false;
	}

	/*
	 * Método que recebe uma string da forma "pf" ou "pj",
	 * representando um tipo de cliente. Assim, com base
	 * em tal String, passar por toda a listaCLientes, 
	 * printando apenas aqueles que são de tal tipo
	 */
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

	/*
	 * Método que recebe um objeto veículo e um objeto
	 * cliente como parâmetros, e cria um novo sinistro
	 * com data aleatória e o adiciona na listaSinistros
	 */
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

	/*
	 * Método que recebe uma string contendo o nome de um cliente
	 * e passa pela listaSinistros. Assim, printa todo sinistro 
	 * que encontrar associado ao nome do cliente em questão.
	 */
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

	/*
	 * Método que printa todos os sinistros contidos na
	 * listaSinistros
	 */
	public ArrayList<Sinistro> listarSinistros() {
		for (int i = 0; i < this.listaSinistros.size(); i++) {
			System.out.println(listaSinistros.get(i));
			System.out.println('\n');
		}
		return this.listaSinistros;
	}

	public void listarVeiculos() {
		for (int i = 0; i < listaClientes.size(); i++) {
			
		}
	}

	/*
	 * Método que passa pela lista de sinistros procurando 
	 * sinistros associados ao cliente passado como parâmetro.
	 * Assim, retorna o número de tais acidentes
	 */
	public int numSinistrosCliente(String nomeCliente) {
		int numSinistros = 0;
		for (Sinistro elemSinistro : listaSinistros) {
			if (elemSinistro.cliente.getNome() == nomeCliente) {
				numSinistros++;
			}
		}

		return numSinistros;
	}

	/*
	 * Método que calcula o valor total do seguro de um 
	 * cliente, levando em conta a quantidade de sinistros
	 * que ele possui
	 */
	public double calcularPrecoSeguroCliente(Cliente cliente) {
		double preco = 0;
		int quantidadeSinistros = numSinistrosCliente(cliente.getNome());

		preco = cliente.calculaScore() * (1 + quantidadeSinistros);
		if (preco < 0) {
			System.out.println("Tal cliente não existe / não esta registrado.");
		}
		return preco;
	}

	/*
	 * Método que soma o preço de todos os seguros de todos 
	 * os clientes da seguradora, de modo a retornar a receita
	 * total.
	 */
	public double calcularReceita() {
		double receita = 0;
		for (Cliente cliente : listaClientes) {
			receita += calcularPrecoSeguroCliente(cliente);
		}

		return receita;
	}
	
	/*
	 * Método que, assim como o escolheSeguradora da classe
	 * AppMain, permite que o usuário, interativamente,
	 * selecione o usuário que deseja interagir com
	 */
	public int escolheCliente() {
		Scanner scan = new Scanner(System.in);
		if (getListaClientes().size() == 0) {
			System.out.println("A seguradora escolhida ainda não possui nenhum cliente cadastrado. Por favor tente novamente.");
			return -1;
		} else {
			System.out.println("Digite o número relacionado ao cliente desejado.");
			for (int i = 0; i < getListaClientes().size(); i++) {
				System.out.println("(" + i + ") - " + getListaClientes().get(i).getNome());
			}
			int indiceClienteEscolhido = scan.nextInt();
			return indiceClienteEscolhido;
		}
	}

	/*
	 * Método que transfere o seguro de um cliente para outro
	 * dentro de uma mesma seguradora. Para isso, transfere
	 * todos os veículos do primeiro para o segundo, e, no fim,
	 * recalcula o preço do seguro de ambos.
	 */
	public void transferirSeguro() {
		System.out.println("Primeiramente necessitamos do cliente cujo seguro deverá partir de.");
		int indiceClienteOriginal = escolheCliente();
		if (indiceClienteOriginal >= 0) {
			System.out.println("Por fim, o cliente para qual o seguro será transferido");
			int indiceClienteRecebido = escolheCliente();
			if (indiceClienteRecebido >= 0) {
				for (int i = 0; i < getListaClientes().get(indiceClienteOriginal).getListaVeiculos().size(); i++) {
					getListaClientes().get(indiceClienteRecebido).getListaVeiculos().add(getListaClientes().get(indiceClienteOriginal).getListaVeiculos().get(i));
					getListaClientes().get(indiceClienteOriginal).getListaVeiculos().remove(0);
				}
				double novoPrecoOriginal = calcularPrecoSeguroCliente(getListaClientes().get(indiceClienteOriginal));
				double novoPrecoRecebido = calcularPrecoSeguroCliente(getListaClientes().get(indiceClienteRecebido));
				getListaClientes().get(indiceClienteOriginal).setValorSeguro(novoPrecoOriginal);
				getListaClientes().get(indiceClienteRecebido).setValorSeguro(novoPrecoRecebido);
			}
		}
	}
}
