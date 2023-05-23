import java.time.LocalDate;
import java.util.Random;

public class Sinistro {
	private final int id;
	private LocalDate data;
	private String endereco;
	private Condutor condutor;
	private Seguro seguro;
	
	//Constructor da classe Sinistro
	public Sinistro(LocalDate data, 
					String endereco,
					Condutor condutor, 
					Seguro seguro) {
		this.id = geraId();
		this.data = data;
		this.endereco = endereco;
		this.condutor = condutor;
		this.seguro = seguro;
	}

	public String toString() {
		String all = "Id: " + getId() + 
					"\nEndereço: " + getEndereco() +
					"\nData: " + getData() + 
					"\nCondutor: " + getCondutor() + 
					"\nSeguro: " + getSeguro();

		return all;
	}
	
	public int getId() {
		return id;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate novaData) {
		this.data = novaData;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String novoEndereco) {
		this.endereco = novoEndereco;
	}

	public Condutor getCondutor() {
		return this.condutor;
	}

	public void setCondutor(Condutor novoCondutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return this.seguro;
	}

	public void setSeguro(Seguro novoSeguro) {
		this.seguro = novoSeguro;
	}

	//INÍCIO DOS MÉTODOS NÃO PADRÕES 

	/*
	 * Método que gera um id aleatório para cada objeto
	 * da classe
	 */
	public int geraId() {
		Random rand = new Random();
		int limite = 999999999;
		return rand.nextInt(limite);
	}
}
