import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String endereco;
	private ArrayList<Veiculo> listaVeiculos;
	
	//Constructor do cliente
	public Cliente(String nome, String endereco,
					ArrayList<Veiculo> listaVeiculos) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = listaVeiculos;
	}

	//Método padrão toString
	public String toString() {
		String all ="Nome: " + getNome() + 
					"\nEndereço: " + getEndereco();

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

	public ArrayList<Veiculo> getListaVeiculos() {
		return this.listaVeiculos;
	}

	//INÍCIO DOS MÉTODOS NÃO PADRÕES;

	/*
	 * Nota-se que não há um método set para a lista
	 * de veículos, já que não faz sentido. Para mais, essa 
	 * variável é private, tornando-a inacessável para 
	 * alterações. Então, necessita-se de um método para 
	 * adicionar novos veículos.
	 */
	public void addVeiculo(Veiculo novoVeiculo) {
		this.listaVeiculos.add(novoVeiculo);
	}

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
}
