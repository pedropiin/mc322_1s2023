import java.util.ArrayList;

public class Cliente {
	private String nome;
	private String endereco;
	private ArrayList<Veiculo> listaVeiculos;
	private double valorSeguro;
	
	//Constructor do cliente
	public Cliente(String nome, String endereco,
					ArrayList<Veiculo> listaVeiculos,
					double valorSeguro) {
		this.nome = nome;
		this.endereco = endereco;
		this.listaVeiculos = listaVeiculos;
		this.valorSeguro = valorSeguro;
	}

	//Método padrão toString
	public String toString() {
		String all ="Nome: " + getNome() + 
					"\nEndereço: " + getEndereco() +
					"\nValor do Seguro: " + getValorSeguro();

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

	public double getValorSeguro() {
		return valorSeguro;
	}

	public void setValorSeguro(double novoValorSeguro) {
		this.valorSeguro = novoValorSeguro;
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
	 * Método que não deve ser utilizado em objetos dessa 
	 * classe, já que se torna de uma classe-pai. Assim, 
	 * esse método é sobrecarregado em cada uma das classes
	 * filho
	 */
	public double calculaScore() {
		return 0;
	}
}
