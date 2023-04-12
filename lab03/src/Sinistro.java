import java.time.LocalDate;
import java.util.Random;

public class Sinistro {
	private int id;
	private LocalDate data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	public Cliente cliente;
	
	//Constructor da classe Sinistro
	public Sinistro(LocalDate data, String endereco,
					Seguradora seguradora, Veiculo veiculo,
					Cliente cliente) {
		this.data = data;
		this.endereco = endereco;
		this.id = geraId();
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}

	public String toString() {
		String all = "Data: " + getData() + 
					"\nEndereço: " + getEndereco() +
					"\nId: " + getId() + 
					"\nSeguradora: " + seguradora.getNome() + 
					"\nPlaca do Veículo: " + veiculo.getPlaca() +
					"\nModelo do Veículo: " + veiculo.getModelo() +
					"\nNome do Cliente: " + cliente.getNome();

		return all;
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

	public Seguradora getSeguradora() {
		return this.seguradora;
	}

	public void setSeguradora(Seguradora novaSeguradora) {
		this.seguradora = novaSeguradora;
	}

	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo novoVeiculo) {
		this.veiculo = novoVeiculo;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente novoClente) {
		this.cliente = novoClente;
	}
}
