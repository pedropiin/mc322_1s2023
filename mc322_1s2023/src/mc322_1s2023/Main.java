package mc322_1s2023;

public class Main {
	
	public static void main(String[] args) {
		
		Cliente pessoa = new Cliente(18, "Pedro da Rosa Pinheiro", "449.219.868-78", "19/04/2004", "Avenida Doutor Romeu Tortima 909");

		if (pessoa.validarCPF(pessoa.getCpf())) {
			System.out.println("O cpf " + pessoa.getCpf() + " é válido");
		} else {
			System.out.println("O cpf " + pessoa.getCpf() + " não é valido");
		}

		pessoa.setCpf("449.219.868-77");

		if (pessoa.validarCPF(pessoa.getCpf())) {
			System.out.println("O cpf " + pessoa.getCpf() + " é válido");
		} else {
			System.out.println("O cpf " + pessoa.getCpf() + " não é valido");
		}
		
	}
}
