import java.util.Scanner;
import java.util.*;

public class AppMain {

    Scanner scan = new Scanner(System.in);
    Scanner scan2 = new Scanner(System.in);

    Seguradora seguradora = new Seguradora("Seguradora POO", "11995807321", "email.seguradora@gmail.com", "Rua da Seguradora 101", new ArrayList<Sinistro>(), new ArrayList<Cliente>());

    public void menuInterativo() {
        int entrada = 1, entrada2 = 1;

        System.out.println("Bem-vindo ao sistema interativo da " + seguradora.getNome() + ".");

        while (entrada != 0) {
            System.out.println("Qual serviço deseja acessar?\n" + 
                                "[1] - Cadastros\n" + 
                                "[2] - Listar\n" + 
                                "[3] - Excluir\n" + 
                                "[4] - Gerar Sinistro\n" + 
                                "[5] - Transferir Seguro\n" + 
                                "[6] - Calcular Receita Seguradora\n" + 
                                "[0] - Sair.");
            entrada = scan.nextInt();

            switch (entrada) {
                case 1:
                    System.out.println("Deseja realizar qual função?\n" +
                                        "[1] - Cadastrar Cliente PF/PJ\n" + 
                                        "[2] - Cadastrar Veiculo\n" + 
                                        "[3] - Cadastrar Seguradora\n" + 
                                        "[4] - Voltar");
                    entrada2 = scan.nextInt();

                    switch (entrada2) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Deseja realizar qual função?\n" +
                                        "[1] - Listar Cliente (PF/PJ) por Seguradora\n" + 
                                        "[2] - Listar Sinistros por Seguradora\n" +
                                        "[3] - Listar Sinistro por Cliente\n" + 
                                        "[4] - Listar Veiculo por Cliente\n" + 
                                        "[5] - Listar Veiculo por Seguradora\n" + 
                                        "[6] - Voltar");
                    entrada2 = scan.nextInt();

                    switch (entrada2) {
                        case 1:
                            Scanner scan3 = new Scanner(System.in);
                            int entrada3 = 1;
                            System.out.println("Deseja visualizar os clientes físicos (1) ou jurídicos (2)?");
                            entrada3 = scan.nextInt();

                            if (entrada3 == 1) {
                                System.out.println("--- Listando clientes físicos por Seguradora ---");
                                seguradora.listarClientes("pf");
                            } else {
                                System.out.println("--- Listando clientes jurídicos por Seguradora ---");
                                seguradora.listarClientes("pj");
                            }
                            
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Deseja realizar qual função?\n" + 
                                        "[1] - Excluir Cliente\n" + 
                                        "[2] - Excluir Veiculo\n" +
                                        "[3] - Excluir Sinistro\n" +
                                        "[4] - Voltar");
                    entrada2 = scan.nextInt();

                    switch (entrada2) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                    }
                    break;
                
                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    break;

                case 0:
                    System.out.println("Encerrando sessão.");
                    break;
            }
        }
    }
}