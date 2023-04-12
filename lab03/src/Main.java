import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

        // INSTANCIAÇÃO DA SEGURADORA PRINCIPAL
        Seguradora seguradora = new Seguradora("Seguradora POO", "11995807321", "email.seguradora@gmail.com", "Rua da Seguradora 101", new ArrayList<Sinistro>(), new ArrayList<Cliente>());

        // CRIAÇÃO, CADASTRO E LISTAGEM DE CLIENTES FÍSICOS E JURÍDICOS
        LocalDate dataLicenca = LocalDate.now();
        LocalDate dataNascimento = LocalDate.of(2004, 04, 19);
        LocalDate dataFundacao = LocalDate.of(1994, 07, 5);

        ClientePF cliente1 = new ClientePF("Pedro da Rosa",
                                        "Rua Pascal 99", 
                                        dataLicenca, 
                                        "Ensino Médio Completo",
                                        "Masculino", 
                                        "Média", 
                                        new ArrayList<Veiculo>(), 
                                        "449.219.868-78", dataNascimento);

        ClientePJ cliente2 = new ClientePJ("Amazon.com", 
                                        "Rua da Amazon", 
                                        new ArrayList<Veiculo>(),
                                        "15.436.940/0001-03",
                                        dataFundacao);

        boolean temp = seguradora.cadastrarCliente(cliente1);
        boolean temp1 = seguradora.cadastrarCliente(cliente2);
        System.out.println("\n---Fim do cadastro de clientes.---\n");

        ArrayList<Cliente> listaClientesTemp = seguradora.listarClientes("PF");
        System.out.println("\n---Fim da listagem de clientes.---\n");

        // TESTE DOS MÉTODOS DE VALIDAÇÃO DE CPF E CNPJ

        boolean temp2 = cliente1.validarCPF(cliente1.getCPF());
        boolean temp3 = cliente2.validarCNPJ(cliente2.getCNPJ());
        System.out.println("\n---Fim da validação de dados.---\n");

        // CRIAÇÃO E ADIÇÃO DOS VEÍCULOS DE CADA CLIENTE

        Veiculo veiculoCliente1 = new Veiculo("DDD2311",
                                            "Hyundai",
                                            "Civic", 
                                            2022);

        Veiculo veiculoCliente2 = new Veiculo("ECP8765", 
                                            "Kia",
                                            "Sportage",
                                            2013);

        cliente1.addVeiculo(veiculoCliente1);
        cliente2.addVeiculo(veiculoCliente2);

        // CRIAÇÃO E VISUALIZAÇÃO DE SINISTROS

        seguradora.gerarSinistro(veiculoCliente1, cliente1);
        
        seguradora.visualizarSinistro("Pedro da Rosa");
        System.out.println("\n---Fim da visualização de sinistros relacionados a um cliente.---\n");

        ArrayList<Sinistro> listaSinistroTemp = seguradora.listarSinistros();
        System.out.println("---Fim da listagem de sinistros.---\n");

        // REMOÇÃO DOS CLIENTES

        boolean temp4 = seguradora.removerCliente("Pedro da Rosa");
        System.out.println("\n---Fim da remoção de clientes.\n");

        // MÉTODO INTERATIVO COM SYSTEM.IN

        Scanner scan = new Scanner(System.in);
        int entrada = 1;
        System.out.println("Bem-vindo ao sistema interativo da " + seguradora.getNome() + ".");

        while (entrada != 0) {
            
        }

    } 
}
