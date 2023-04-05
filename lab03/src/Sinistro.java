package mc322_1s2023;

import java.util.Random;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;
	
	//Constructor da classe Sinistro
	public Sinistro(String data, String endereco,
					Seguradora seguradora, Veiculo veiculo,
					Cliente cliente) {
		this.data = data;
		this.endereco = endereco;
		this.id = geraId();
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	/*
	Método que gera um id aleatório para cada objeto
	da classe Sinistro
	 */
	public int geraId() {
		Random rand = new Random();
		int limite = 999999999;
		return rand.nextInt(limite);
	}
	
	public int getId() {
		return id;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String toString() {
		String all = "Data: " + this.data + "\nEndereço: " +
		this.endereco + "\nId: " + this.id + "\nSeguradora: " +
		this.seguradora + "\nVeiculo: " + this.veiculo +
		"\nCliente: " + this.cliente;
	}
}
