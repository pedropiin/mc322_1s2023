package mc322_1s2023;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	
	//Constructor da classe Veiculo
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}

	public String toString() {
		String all ="Placa: " + getPlaca() + 
					"\nMarca: " + getMarca() +
					"\nModelo: " + getModelo() +
					"\nAno de Fabricação: " + getAnoFabricacao();

		return all;
	}
	
	public String getPlaca() {
		return this.placa;
	}
	
	public void setPlaca(String novaPlaca) {
		this.placa = novaPlaca;
	}
	
	public String getMarca() {
		return this.marca;
	}
	
	public void setMarca(String novaMarca) {
		this.marca = novaMarca;
	}
	
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String novoModelo) {
		this.modelo = novoModelo;
	}

	public int getAnoFabricacao() {
		return this.anoFabricacao;
	}

	public void setModelo(int novoAnoFabricacao) {
		this.anoFabricacao = novoAnoFabricacao;
	}
}
