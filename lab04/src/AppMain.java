import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AppMain {
    private ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
    Scanner scan = new Scanner(System.in);

    public int escolheSeguradora() {
        if (listaSeguradoras.size() == 0) {
            System.out.println("Nosso sistema ainda não possui nenhuma seguradora cadastrada.");
            return -1;
        } else {
            System.out.println("Digite o número relacionado à seguradora na qual se deseja cadastrar o novo cliente.");
            for (int i = 0; i < listaSeguradoras.size(); i++) {
                System.out.println("(" + i + ") - " + listaSeguradoras.get(i).getNome());
            }
            int entrada = scan.nextInt();
            return entrada;
        }
    }

    public int escolheCliente(Seguradora seguradoraCliente) {
        if (seguradoraCliente.getListaClientes().size() == 0) {
            System.out.println("A seguradora escolhida ainda não possui nenhum cliente cadastrado. Por favor tente novamente.");
            return -1;
        } else {
            System.out.println("Digite o número relacionado ao cliente desejado.");
            for (int i = 0; i < seguradoraCliente.getListaClientes().size(); i++) {
                System.out.println("(" + i + ") - " + seguradoraCliente.getListaClientes().get(i).getNome());
            }
            int entrada = scan.nextInt();
            return entrada;
        }
    }

    public void cadastrarClientePF(Seguradora seguradoraDesejada) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Digite o nome do cliente: ");
        String nome = scan.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        String endereco = scan.nextLine();
        System.out.println("Digite a data da licença do cliente no formato DD/MM/YYYYY: ");
        LocalDate dataLicenca = LocalDate.parse(scan.nextLine(), dtf);
        System.out.println("Digite o grau de educação do cliente: ");
        String educacao = scan.nextLine();
        System.out.println("Digite o gênero do cliente: ");
        String genero = scan.nextLine();
        System.out.println("Digite a classe econômica do cliente: ");
        String classeEconomica = scan.nextLine();
        System.out.println("Digite o CPF do cliente: ");
        String cpf = scan.nextLine();
        while (!Validacao.validarCPF(cpf)) {
            System.out.println("Por favor verifique os dígitos inseridos e tente novamente.");
        }
        System.out.println("Digite a data de nascimento do cliente no formato DD/MM/YYYYY: ");
        LocalDate dataNascimento = LocalDate.parse(scan.nextLine(), dtf);
        
        ClientePF novoCliente = new ClientePF(nome,
                                        endereco, 
                                        dataLicenca,
                                        educacao,
                                        genero,
                                        classeEconomica,
                                        new ArrayList<Veiculo>(),
                                        cpf,
                                        dataNascimento,
                                        0);
        double seguro = seguradoraDesejada.calcularPrecoSeguroCliente(novoCliente);
        novoCliente.setValorSeguro(seguro);
        boolean temp = seguradoraDesejada.cadastrarCliente(novoCliente);
    }

    public void cadastrarClientePJ(Seguradora seguradoraDesejada) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Digite o nome do cliente: ");
        String nome = scan.nextLine();
        System.out.println("Digite o endereço do cliente: ");
        String endereco = scan.nextLine();
        System.out.println("Digite o CNPJ do cliente: ");
        String cnpj = scan.nextLine();
        while (!Validacao.validarCNPJ(cnpj)) {
            System.out.println("Por favor verifique os dígitos inseridos e tente novamente.");
        }
        System.out.println("Digite a data de fundação do cliente no formato DD/MM/YYYYY: ");
        LocalDate dataFundacao = LocalDate.parse(scan.nextLine(), dtf);
        System.out.println("Digite o número de funcionários do cliente: ");
        int quantidadeFuncionarios = scan.nextInt();

        ClientePJ novoCliente = new ClientePJ(nome,
                                            endereco,
                                            new ArrayList<Veiculo>(),
                                            cnpj,
                                            dataFundacao,
                                            0,
                                            quantidadeFuncionarios);
        double seguro = seguradoraDesejada.calcularPrecoSeguroCliente(novoCliente);
        novoCliente.setValorSeguro(seguro);

        boolean temp = seguradoraDesejada.cadastrarCliente(novoCliente);
    }

    public void cadastrarVeiculo(Seguradora seguradoraDesejada, int indiceCliente) {
        System.out.println("Digite a placa do veículo: ");
        String placa = scan.nextLine();
        System.out.println("Digite a marca do veículo: ");
        String marca = scan.nextLine();
        System.out.println("Digite o modelo do veículo: ");
        String modelo = scan.nextLine();
        System.out.println("Digite o ano de fabricação do veículo: ");
        int anoFabricacao = scan.nextInt();

        Veiculo novoVeiculo = new Veiculo(placa,
                                        marca,
                                        modelo,
                                        anoFabricacao);
        boolean temp = seguradoraDesejada.getListaClientes().get(indiceCliente).getListaVeiculos().add(novoVeiculo);
    }

    public void cadastrarSeguradora() {
        System.out.println("Digite o nome da nova seguradora: ");
        String nome = scan.nextLine();
        System.out.println("Digite o telefone da nova seguradora: ");
        String telefone = scan.nextLine();
        System.out.println("Digite o email da nova seguradora: ");
        String email = scan.nextLine();
        System.out.println("Digite o endereço da nova seguradora: ");
        String endereco = scan.nextLine();

        Seguradora novaSeguradora = new Seguradora(nome, telefone, email, endereco, new ArrayList<Sinistro>(), new ArrayList<Cliente>());
        listaSeguradoras.add(novaSeguradora);
    }

    public void menuInterativo() {
        int entradaPrimaria, entradaTemp, i = 0, j = 0, h = 0;
        double entradaSecundaria;
        MenuOperacoes comandoPrimario, comandoSecundario;

        System.out.println("Bem-vindo ao sistema interativo de seguradoras.");
        // só acaba quando o usuário selecionar 0
        while (true) {
            System.out.println("Qual serviço deseja acessar?\n" + 
                                "[1] - Cadastros\n" + 
                                "[2] - Listar\n" + 
                                "[3] - Excluir\n" + 
                                "[4] - Gerar Sinistro\n" + 
                                "[5] - Transferir Seguro\n" + 
                                "[6] - Calcular Receita Seguradora\n" + 
                                "[0] - Sair.");
            entradaPrimaria = scan.nextInt();
            comandoPrimario = MenuOperacoes.getEnumComando(entradaPrimaria);

            switch (comandoPrimario) {
                case CADASTRAR:
                    System.out.println("Deseja realizar qual função?\n" +
                                        "[1] - Cadastrar Cliente PF/PJ\n" + 
                                        "[2] - Cadastrar Veiculo\n" + 
                                        "[3] - Cadastrar Seguradora\n" + 
                                        "[4] - Voltar");
                    entradaSecundaria = scan.nextDouble();
                    entradaSecundaria = entradaPrimaria + entradaSecundaria / 10;
                    comandoSecundario = MenuOperacoes.getEnumComando(entradaSecundaria);

                    switch (comandoSecundario) {
                        case CADASTRAR_CLIENTE_PF_PJ:
                            i = escolheSeguradora();
                            if (i >= 0) {
                                Seguradora seguradoraCliente = listaSeguradoras.get(i);
                                System.out.println("Deseja cadastrar um cliente físico (1) ou jurídico (2)?");
                                entradaTemp = scan.nextInt();
                                if (entradaTemp == 1) {
                                    cadastrarClientePF(seguradoraCliente);
                                } else if (entradaTemp == 2) {
                                    cadastrarClientePJ(seguradoraCliente);
                                } else {
                                    System.out.println("Por favor tente novamente.");
                                }
                            }
                            break;

                        case CADASTRAR_VEICULO:
                            i = escolheSeguradora();
                            if (i >= 0) {
                                Seguradora seguradoraVeiculo = listaSeguradoras.get(i);
                                i = escolheCliente(seguradoraVeiculo);
                                if (i >= 0) {
                                    cadastrarVeiculo(seguradoraVeiculo, i);
                                }
                            }
                            break;

                        case CADASTRAR_SEGURADORA:
                            cadastrarSeguradora();
                            break;

                        case VOLTAR_CADASTRO:
                            break;
                    }
                    break;

                case LISTAR:
                    System.out.println("Deseja realizar qual função?\n" +
                                        "[1] - Listar Cliente (PF/PJ) por Seguradora\n" + 
                                        "[2] - Listar Sinistros por Seguradora\n" +
                                        "[3] - Listar Sinistro por Cliente\n" + 
                                        "[4] - Listar Veiculo por Cliente\n" + 
                                        "[5] - Listar Veiculo por Seguradora\n" + 
                                        "[6] - Voltar");
                    entradaSecundaria = scan.nextDouble();
                    entradaSecundaria = entradaPrimaria + entradaSecundaria / 10;
                    comandoSecundario = MenuOperacoes.getEnumComando(entradaSecundaria);

                    switch (comandoSecundario) {
                        case LISTAR_CLIENTES_POR_SEGURADORA:
                            System.out.println("Deseja visualizar os clientes físicos (1) ou jurídicos (2)?");
                            entradaTemp = scan.nextInt();
                            for (i = 0; i < listaSeguradoras.size(); i++) {
                                if (entradaTemp == 1) {
                                    System.out.println("--- Clientes físicos da seguradora " + listaSeguradoras.get(i).getNome() + "---");
                                    listaSeguradoras.get(i).listarClientes("pf");
                                } else {
                                    System.out.println("--- Clientes jurídicos da seguradora " + listaSeguradoras.get(i).getNome() + "---");
                                    listaSeguradoras.get(i).listarClientes("pj");
                                }
                            }
                            break;

                        case LISTAR_SINISTROS_POR_SEGURADORA:
                            for (i = 0; i < listaSeguradoras.size(); i++) {
                                System.out.println("--- Sinistros da seguradora " + listaSeguradoras.get(i).getNome() + "---");
                                listaSeguradoras.get(i).listarSinistros();
                            }
                            break;

                        case LISTAR_SINISTROS_POR_CLIENTE:
                            ArrayList<Cliente> listaClientesTemp;
                            for (i = 0; i < listaSeguradoras.size(); i++) {
                                listaClientesTemp = listaSeguradoras.get(i).getListaClientes();
                                for (j = 0; j < listaClientesTemp.size(); j++) {
                                    System.out.println("--- Sinistros do cliente " + listaClientesTemp.get(j).getNome() + "---");
                                    listaSeguradoras.get(i).visualizarSinistro(listaClientesTemp.get(j).getNome());
                                }
                            }
                            break;

                        case LISTAR_VEICULOS_POR_CLIENTE:
                            ArrayList<Veiculo> listaVeiculoTemp;
                            for (i = 0; i < listaSeguradoras.size(); i++) {
                                listaClientesTemp = listaSeguradoras.get(i).getListaClientes();
                                for (j = 0; j < listaClientesTemp.size(); j++) {
                                    listaVeiculoTemp = listaClientesTemp.get(j).getListaVeiculos();
                                    System.out.println("--- Veículos do cliente " + listaClientesTemp.get(j).getNome() + "---");
                                    for (h = 0; h < listaVeiculoTemp.size(); h++) {
                                        System.out.println(listaVeiculoTemp.get(h));
                                    }
                                }
                            }
                            break;

                        case LISTAR_VEICULOS_POR_SEGURADORA:
                            for (i = 0; i < listaSeguradoras.size(); i++) {
                                listaClientesTemp = listaSeguradoras.get(i).getListaClientes();
                                System.out.println("--- Veículos da seguradora " + listaSeguradoras.get(i).getNome());
                                for (j = 0; j < listaClientesTemp.size(); j++) {
                                    listaVeiculoTemp = listaClientesTemp.get(j).getListaVeiculos();
                                    for (h = 0; h < listaVeiculoTemp.size(); h++) {
                                        System.out.println(listaVeiculoTemp.get(h));
                                    }
                                }
                            }
                            break;

                        case VOLTAR_LISTAR:
                            break;
                    }
                    break;

                case EXCLUIR:
                    System.out.println("Deseja realizar qual função?\n" + 
                                        "[1] - Excluir Cliente\n" + 
                                        "[2] - Excluir Veiculo\n" +
                                        "[3] - Excluir Sinistro\n" +
                                        "[4] - Voltar");
                    entradaSecundaria = scan.nextDouble();
                    entradaSecundaria = entradaPrimaria + entradaSecundaria / 10;
                    comandoSecundario = MenuOperacoes.getEnumComando(entradaSecundaria);

                    switch (comandoSecundario) {
                        case EXCLUIR_CLIENTE:
                            break;

                        case EXCLUIR_VEICULO:
                            break;
                            
                        case EXCLUIR_SINISTRO:
                            break;

                        case VOLTAR_EXCLUIR:
                            break;
                    }
                    break;
                
                case GERAR_SINISTRO:
                    break;

                case TRANSFERIR_SEGURO:
                    break;

                case CALCULAR_RECEITA:
                    break;

                case SAIR:
                    System.out.println("Encerrando sessão.");
                    break;

                default:
                    System.out.println("Por favor digite uma tecla válida.");
                    break;
            }
        }
    }
}