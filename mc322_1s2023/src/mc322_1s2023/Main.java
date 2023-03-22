package mc322_1s2023;

public class Main {
	
	public static void main(String[] args) {
		
		//Instanciando e usando a classe Veculo
		Veiculo veiculo1 = new Veiculo("DDD2311", "Chevrolet", "Camaro");

		veiculo1.setModelo("Sportage");
		System.out.println(veiculo1.getModelo());

		//Instanciando e usando a classe Seguradora
		Seguradora pessoa1 = new Seguradora("Pedro da Rosa Pinheiro", "11995807322", "pedroteste@gmail.com", "Rua Teste 99");

		pessoa1.setNome("Lucas da Rosa Pinheiro");
		System.out.println(pessoa1.getNome());

		// Instanciando a classe Sinistro
		Sinistro caso1 = new Sinistro("21/03/2023", "Rua Teste 100");

		caso1.setData("28/02/2022");
		System.out.println(caso1.getData());

		caso1.geraId();
		System.out.println(caso1.getId());

		//Instanciando e usando a clase Cliente
		Cliente user1 = new Cliente(18, "Pedro da Rosa Pinheiro", "120.102.984-89", "19/04/2004", "Rua Teste 99");

		user1.setIdade(22);
		System.out.println(user1.getIdade());

		if (user1.validarCPF(user1.getCpf())) {
			System.out.println("O cpf do usuário é valido.");
		} else {
			System.out.println("O cpf do usuário é inválido.");
		}

		user1.toString();
	}
}
