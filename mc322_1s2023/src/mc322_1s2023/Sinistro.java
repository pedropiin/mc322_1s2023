package mc322_1s2023;

import java.util.Random;

public class Sinistro {
	
	private int id;
	private String data;
	private String endereco;
	
	public Sinistro(String data, String endereco) {
		this.data = data;
		this.endereco = endereco;
		this.id = geraId();
	}
	
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
}
