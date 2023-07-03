import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

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
	public boolean cadastrarCliente(String tipoCliente) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (tipoCliente == "pf" || tipoCliente == "PF") {
			System.out.println("Digite o nome do cliente: ");
			String nome = scan.nextLine();
			if (!Validacao.apenasChars(nome)) {
				System.out.println("Nomes não podem conter caracteres além das letras e espaços. Por favor tente novamente.");
			} else {
				System.out.println("Digite o CPF do cliente: ");
				String cpf = scan.nextLine();
				if (!Validacao.validarCPF(cpf)) {
					System.out.println("Por favor verifique os dígitos inseridos e tente novamente.");
				} else {
					System.out.println("Digite o endereço do cliente: ");
					String endereco = scan.nextLine();
					System.out.println("Digite o telefone do cliente: ");
					String telefone = scan.nextLine();
					System.out.println("Digite o email do cliente: ");
					String email = scan.nextLine();
					System.out.println("Digite o grau de educação do cliente: ");
					String educacao = scan.nextLine();
					System.out.println("Digite o gênero do cliente: ");
					String genero = scan.nextLine();
					System.out.println("Digite a data de nascimento do cliente no formato DD/MM/YYYYY: ");
					LocalDate dataNascimento = LocalDate.parse(scan.nextLine(), dtf);

					ClientePF novoCliente = new ClientePF(nome, endereco, telefone, email, educacao, genero, new ArrayList<Veiculo>(), cpf, dataNascimento);
					getListaClientes().add(novoCliente);
					return true;
				}
			}
		} else if (tipoCliente == "pj" || tipoCliente == "PJ") {
			System.out.println("Digite o nome do cliente: ");
			String nome = scan.nextLine();
			System.out.println("Digite o CNPJ do cliente: ");
			String cnpj = scan.nextLine();
			if (!Validacao.validarCNPJ(cnpj)) {
				System.out.println("Por favor verifique os dígitos inseridos e tente novamente.");
			} else {
				System.out.println("Digite o endereço do cliente: ");
				String endereco = scan.nextLine();
				System.out.println("Digite o telefone do cliente: ");
				String telefone = scan.nextLine();
				System.out.println("Digite o email do cliente: ");
				String email = scan.nextLine();
				System.out.println("Digite a data de fundação do cliente no formato DD/MM/YYYYY: ");
				LocalDate dataFundacao = LocalDate.parse(scan.nextLine(), dtf);
				System.out.println("Digite o número de funcionários do cliente: ");
				int quantidadeFuncionarios = scan.nextInt();
				scan.nextLine();

				ClientePJ novoCliente = new ClientePJ(nome, endereco, telefone, email, quantidadeFuncionarios, cnpj, dataFundacao, new ArrayList<Frota>());
				getListaClientes().add(novoCliente);
				return true;
			}
		}
		System.out.println("Por favor insira um tipo de cliente válido.");
		return false;
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

	/*
	 * Método que cancela um seguro associado à seguradora
	 * em questão. Para isso, pergunta ao usuário qual
	 * seguro ele deseja remover
	 */
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
			System.out.println("Selecione o cliente desejado.");
			for (int i = 0; i < getListaClientes().size(); i++) {
				System.out.println("(" + i + ") - " + getListaClientes().get(i).getNome());
			}
			int indiceClienteEscolhido = scan.nextInt();
			scan.nextLine();
			return indiceClienteEscolhido;
		}
	}

	/*
	 * Método que mostra ao usuário todas os seguros cadastradas
	 * no cliente e retorna um inteiro que representa
	 * o índice do seguro que o usuário deseja utilizar
	 */
	public int escolheSeguro() {
		if (getListaSeguros().size() == 0) {
			System.out.println("A seguradora escolhida não possui nenhum seguro cadastrado.");
			return -1;
		} else {
			System.out.println("Selecione o seguro desejado.");
			for (int i = 0; i < getListaSeguros().size(); i++) {
				System.out.println("(" + i  + ") - Código: " + getListaSeguros().get(i).getId() + " / Cliente: " + getListaSeguros().get(i).getCliente().getNome());
			}
			int indiceSeguroEscolhido = scan.nextInt();
			scan.nextLine();
			return indiceSeguroEscolhido;
		}
	}

	/*
	 * Método que cria um novo seguro para um seguro 
	 * inputado pelo cliente. Para isso, leva em consideração se
	 * o cliente é físico ou jurídico.
	 */
	public boolean gerarSeguro() {
		int indiceCliente, indiceVeiculo, indiceFrota;
		indiceCliente = escolheCliente();
		if (indiceCliente == -1) {
			return false;
		} else {
			if (listaClientes.get(indiceCliente) instanceof ClientePF) {
				ClientePF cliente = ((ClientePF) listaClientes.get(indiceCliente));
				indiceVeiculo = cliente.escolheVeiculo();
				if (indiceVeiculo == -1) {
					return false;
				} else {
					System.out.println(cliente.getDataNascimento());
					SeguroPF novoSeguroPF = new SeguroPF(LocalDate.now(), LocalDate.now().plusYears(1), this, new ArrayList<Sinistro>(), new ArrayList<Condutor>(), cliente.getListaVeiculos().get(indiceVeiculo), cliente);
					getListaSeguros().add(novoSeguroPF);
					System.out.println("Novo seguro com id " + novoSeguroPF.getId() + " cadastrado com sucesso em nome de " + listaClientes.get(indiceCliente).getNome() + ".");
				}
			} else if (listaClientes.get(indiceCliente) instanceof ClientePJ) {
				ClientePJ cliente = ((ClientePJ) listaClientes.get(indiceCliente));
				indiceFrota = cliente.escolheFrota();
				if (indiceFrota == -1) {
					return false;
				} else {
					SeguroPJ novoSeguroPJ = new SeguroPJ(LocalDate.now(), LocalDate.now().plusYears(1), this, new ArrayList<Sinistro>(), new ArrayList<Condutor>(), cliente.getListaFrotas().get(indiceFrota), cliente);
					getListaSeguros().add(novoSeguroPJ);
					System.out.println("Novo seguro com id " + novoSeguroPJ.getId() + " cadastrado com sucesso em nome de" + listaClientes.get(indiceCliente).getNome() + ".");
				}
			}
			return true;
		}
	}

	/*
	 * Método que retorna um ArrayList contendo todos os seguros
	 * associados à um cliente passado como parâmetro
	 */
	public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente) {
		ArrayList<Seguro> listaSegurosCliente = new ArrayList<Seguro>();
		for (Seguro seguro : listaSeguros) {
			if (seguro.getCliente() == cliente) {
				listaSegurosCliente.add(seguro);
			}
		}

		return listaSegurosCliente;
	}

	/*
	 * Método que retorna um ArrayList contendo todos os sinistros 
	 * associados à um cliente passado como parâmetro
	 */
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
	public boolean visualizarSinistro(Cliente cliente) {
		for (Seguro seguro : getListaSeguros()) {
			if (seguro.getCliente() == cliente) {
				for (Sinistro sinistro : seguro.getListaSinistros()) {
					System.out.println(sinistro);
				}
			}
		}
		return true;
	}


	/*
	 * Método que transfere o seguro de um cliente para outro
	 * dentro de uma mesma seguradora. Para isso, transfere
	 * todos os veículos do primeiro para o segundo, e, no fim,
	 * recalcula o preço do seguro de ambos.
	 */
	public void transferirSeguro() {
		System.out.println("Primeiramente, selecione o cliente cujo seguro deverá partir de.");
		int indiceClienteOriginal = escolheCliente();
		if (indiceClienteOriginal >= 0) {
			System.out.println("Por fim, o cliente para qual o seguro será transferido");
			int indiceClienteRecebido = escolheCliente();
			if (indiceClienteRecebido >= 0) {
				if (getListaClientes().get(indiceClienteOriginal) instanceof ClientePF && getListaClientes().get(indiceClienteRecebido) instanceof ClientePF) {
					for (int i = 0; i < ((ClientePF) getListaClientes().get(indiceClienteOriginal)).getListaVeiculos().size(); i++) {
						((ClientePF) getListaClientes().get(indiceClienteRecebido)).getListaVeiculos().add(((ClientePF) getListaClientes().get(indiceClienteOriginal)).getListaVeiculos().get(0));
						((ClientePF) getListaClientes().get(indiceClienteOriginal)).getListaVeiculos().remove(0);
					}
				} else if (getListaClientes().get(indiceClienteOriginal) instanceof ClientePJ && getListaClientes().get(indiceClienteRecebido) instanceof ClientePF) {
					for (int i = 0; i < ((ClientePJ) getListaClientes().get(indiceClienteOriginal)).getListaFrotas().size(); i++) {
						((ClientePJ) getListaClientes().get(indiceClienteRecebido)).getListaFrotas().add(((ClientePJ) getListaClientes().get(indiceClienteOriginal)).getListaFrotas().get(0));
						((ClientePJ) getListaClientes().get(indiceClienteOriginal)).getListaFrotas().remove(0);
					}
				} else {
					System.out.println("Os clientes selecionados são de categorias diferentes. Por favor tente novamente.");
				}
			}
		}
	}
}
