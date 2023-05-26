import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Seguradora {
	Scanner scan = new Scanner(System.in);
	private final String cnpj;
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Seguro> listaSeguros;
	
	//Constructor da classe Seguradora
	public Seguradora(String nome, 
					String telefone,
					String email,
					String endereco,
					ArrayList<Cliente> listaClientes,
					ArrayList<Seguro> listaSeguros, 
					String cnpj) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.listaSeguros = listaSeguros;
		this.listaClientes = listaClientes;
		this.cnpj = cnpj;
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

	public String getCNPJ() {
		return cnpj;
	}	

	public ArrayList<Seguro> getListaSeguros() {
		return this.listaSeguros;
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
	 * Método que soma o preço de todos os seguros de todos 
	 * associados à seguradora em questão.
	 */
	public double calcularReceita() {
		double receita = 0;
		for (Seguro seguro : getListaSeguros()) {
			receita += seguro.getValorMensal();
		}

		return receita;
	}

	public boolean cancelarSeguro() {
		int indiceSeguro;
		if (getListaSeguros().size() == 0) {
			System.out.println("Não há nenhum seguro cadastrado na seguradora " + getNome() + ".");
			return false;
		} else {
			System.out.println("Selecione o seguro que se deseja cancelar");
			for (int i = 0; i < getListaSeguros().size(); i++) {
				System.out.println("(" + i + ") - "  + getListaSeguros().get(i).getId());
			}
			indiceSeguro = scan.nextInt();
			scan.nextLine();
			getListaSeguros().remove(indiceSeguro);
			System.out.println("Seguro removido com sucesso.");
			return true;
		}
	}

	/*
	 * Método que, assim como o escolheSeguradora da classe
	 * AppMain, permite que o usuário, interativamente,
	 * selecione o usuário que deseja interagir com
	 */
	public int escolheCliente() {
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

	public boolean gerarSeguro() {
		int indiceCliente, indiceVeiculo, indiceFrota;
		indiceCliente = escolheCliente();
		if (indiceCliente == -1) {
			return false;
		} else {
			if (listaClientes.get(indiceCliente) instanceof ClientePF) {
				ClientePF cliente = ((ClientePF) listaClientes.get(indiceCliente));
				indiceVeiculo = cliente.escolheVeiculo();
				SeguroPF novoSeguroPF = new SeguroPF(0, LocalDate.now(), LocalDate.now().plusYears(1), this, new ArrayList<Sinistro>(), new ArrayList<Condutor>(), cliente.getListaVeiculos().get(indiceVeiculo), cliente);
				getListaSeguros().add(novoSeguroPF);
			} else if (listaClientes.get(indiceCliente) instanceof ClientePJ) {
				ClientePJ cliente = ((ClientePJ) listaClientes.get(indiceCliente));
				indiceFrota = cliente.escolheFrota();
				SeguroPJ novoSeguroPJ = new SeguroPJ(0, LocalDate.now(), LocalDate.now().plusYears(1), this, new ArrayList<Sinistro>(), new ArrayList<Condutor>(), cliente.getListaFrotas().get(indiceFrota), cliente);
				getListaSeguros().add(novoSeguroPJ);
			}
			return true;
		}
	}

	public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
		ArrayList<Seguro> listaSegurosCliente = new ArrayList<Seguro>();
		for (Seguro seguro : listaSeguros) {
			if (seguro.getCliente() == cliente) {
				listaSegurosCliente.add(seguro);
			}
		}

		return listaSegurosCliente;
	}

	public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente) {
		ArrayList<Sinistro> listaSinistrosCliente = new ArrayList<Sinistro>();
		for (Seguro seguro : getListaSeguros()) {
			if (seguro.getCliente().getNome() == cliente.getNome()) {
				for (Sinistro sinistro : seguro.getListaSinistros()) {
					listaSinistrosCliente.add(sinistro);
				}
			}
		}

		return listaSinistrosCliente;
	}

	/*
	 * Método que recebe uma string da forma "pf" ou "pj",
	 * representando um tipo de cliente. Assim, com base
	 * em tal String, passar por toda a listaCLientes, 
	 * printando apenas aqueles que são de tal tipo
	 */
	public void listarClientes(String tipoCliente) {
		for (Cliente clienteCadastrado : listaClientes) {
			if (clienteCadastrado instanceof ClientePF) {
				if (tipoCliente == "PF" || tipoCliente == "pf") {
					System.out.println(clienteCadastrado);
				} else {
					continue;
				}
			} else if (clienteCadastrado instanceof ClientePJ) {
				if (tipoCliente == "PJ" || tipoCliente == "pj") {
					System.out.println(clienteCadastrado);
				} else {
					continue;
				}
			}
		}
	}

	/*
	 * Método que printa todos os sinistros contidos na
	 * listaSinistros
	 */
	public void listarSinistros() {
		for (int i = 0; i < getListaSeguros().size(); i++) {
			System.out.println("--- Sinistros do seguro " + getListaSeguros().get(i).getId() + " ---");
			for (int j = 0; j < getListaSeguros().get(i).getListaSinistros().size(); j++) {
				System.out.println(getListaSeguros().get(i).getListaSinistros().get(j));
			}
		}
	}

	public void listarVeiculos() {
		for (int i = 0; i < listaClientes.size(); i++) {
			
		}
	}

	// /*
	//  * Método que passa pela lista de sinistros procurando 
	//  * sinistros associados ao cliente passado como parâmetro.
	//  * Assim, retorna o número de tais acidentes
	//  */
	// public int numSinistrosCliente(Cliente cliente) {
	// 	int numSinistros = getsini
	// }

	/*
	 * Mètodo que recebe o nome de um cliente e passa 
	 * por toda a listaClientes, procurando um cliente
	 * com tal nome para remove-lo
	 */
	public boolean removerCliente() {
		int indiceCliente = escolheCliente();
		if (indiceCliente >= 0) {
			listaClientes.remove(indiceCliente);
			return true;
		}
		return false;
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
