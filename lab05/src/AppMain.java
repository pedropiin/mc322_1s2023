import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AppMain {
    public static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
    static Scanner scan = new Scanner(System.in);

    /*
     * Método que permite a escolha de forma visual 
     * da seguradora que o usuário deseja interagir com
     */
    public static int escolheSeguradora() {
        if (listaSeguradoras.size() == 0) {
            System.out.println("Nosso sistema ainda não possui nenhuma seguradora cadastrada.");
            return -1;
        } else {
            System.out.println("Digite o número relacionado à seguradora relacionada ao cliente em questão.");
            for (int i = 0; i < listaSeguradoras.size(); i++) {
                System.out.println("(" + i + ") - " + listaSeguradoras.get(i).getNome());
            }
            int entrada = scan.nextInt();
            scan.nextLine();
            return entrada;
        }
    }

    /*
     * Método que, recebendo uma seguradora específica, 
     * faz com que o usuário escolha um cliente dela, para assim
     * retornar seu índice
     */
    public static int escolheCliente(Seguradora seguradoraCliente) {
        if (seguradoraCliente.getListaClientes().size() == 0) {
            System.out.println("A seguradora escolhida ainda não possui nenhum cliente cadastrado. Por favor tente novamente.");
            return -1;
        } else {
            System.out.println("Digite o número relacionado ao cliente desejado.");
            for (int i = 0; i < seguradoraCliente.getListaClientes().size(); i++) {
                System.out.println("(" + i + ") - " + seguradoraCliente.getListaClientes().get(i).getNome());
            }
            int entrada = scan.nextInt();
            scan.nextLine();
            return entrada;
        }
    }
    /*
     * Assim como a escolheCliente e escolheSeguradora,
     * faz com que o usuário escolha um veículo de um específico
     * cliente, retornando seu índice
     */
    public static int escolheVeiculo(Seguradora seguradoraCliente, Cliente cliente) {
        if (cliente instanceof ClientePF) {
            ArrayList<Veiculo> listaVeiculosTemp = ((ClientePF) cliente).getListaVeiculos();
            int tamanhoListaVeiculos = listaVeiculosTemp.size();
            if (tamanhoListaVeiculos == 0) {
                System.out.println("O cliente escolhido ainda não possui nenhum veículo cadastrado.");
                return -1;
            } else {
                System.out.println("Digite o índice do veículo em questão.");
                for (int i = 0; i < tamanhoListaVeiculos; i++) {
                    System.out.println("(" + i + ") - " +
                                    listaVeiculosTemp.get(i).getMarca() +
                                    listaVeiculosTemp.get(i).getModelo() +
                                    listaVeiculosTemp.get(i).getPlaca());
                }
                int indiceVeiculo = scan.nextInt();
                scan.nextLine();
                return indiceVeiculo;
            }
        } else {
            int indiceFrota = ((ClientePJ) cliente).escolheFrota();
            ArrayList<Veiculo> listaVeiculosTemp = ((ClientePJ) cliente).getListaFrotas().get(indiceFrota).getListaVeiculos();
            int tamanhoListaVeiculos = listaVeiculosTemp.size();
            if (tamanhoListaVeiculos == 0) {
                System.out.println("O cliente escolhido ainda não possui nenhum veículo cadastrado.");
                return -1;
            } else {
                System.out.println("Digite o índice do veículo em questão.");
                for (int i = 0; i < tamanhoListaVeiculos; i++) {
                    System.out.println("(" + i + ") - " +
                            listaVeiculosTemp.get(i).getMarca() +
                            listaVeiculosTemp.get(i).getModelo() +
                            listaVeiculosTemp.get(i).getPlaca());
                }
                int indiceVeiculo = scan.nextInt();
                scan.nextLine();
                return indiceVeiculo;
            }
        }
    }

    /*
     * Assim como o cadastrar clientepf e cliente pj,
     * é um método interativo que solicita e recebe
     * do usuário todas as informações necessárias
     * para o cadastro de um novo veículo
     */
    public static void cadastrarVeiculo(Seguradora seguradoraDesejada, Cliente cliente) {
        System.out.println("Digite a placa do veículo: ");
        String placa = scan.nextLine();
        System.out.println("Digite a marca do veículo: ");
        String marca = scan.nextLine();
        System.out.println("Digite o modelo do veículo: ");
        String modelo = scan.nextLine();
        System.out.println("Digite o ano de fabricação do veículo: ");
        int anoFabricacao = scan.nextInt();
        scan.nextLine();

        Veiculo novoVeiculo = new Veiculo(placa,
                                        marca,
                                        modelo,
                                        anoFabricacao);
        
        if (cliente instanceof ClientePF) {
            ((ClientePF) cliente).getListaVeiculos().add(novoVeiculo);
        } else {
            int indiceFrota = ((ClientePJ) cliente).escolheFrota();
            ((ClientePJ) cliente).getListaFrotas().get(indiceFrota).getListaVeiculos().add(novoVeiculo);
        }
    }


    /*
     * Assim como o cadastrar clientepf e cliente pj,
     * é um método interativo que solicita e recebe
     * do usuário todas as informações necessárias
     * para o cadastro de uma nova seguradora
     */
    public static void cadastrarSeguradora() {
        System.out.println("Digite o nome da nova seguradora: ");
        String nome = scan.nextLine();
        System.out.println("Digite o telefone da nova seguradora: ");
        String telefone = scan.nextLine();
        System.out.println("Digite o email da nova seguradora: ");
        String email = scan.nextLine();
        System.out.println("Digite o endereço da nova seguradora: ");
        String endereco = scan.nextLine();
        System.out.println("Digite o CNPJ da nova seguradora: ");
        String cnpj = scan.nextLine();

        Seguradora novaSeguradora = new Seguradora(nome, telefone, email, endereco, new ArrayList<Cliente>(), new ArrayList<Seguro>(), cnpj);
        listaSeguradoras.add(novaSeguradora);
    }


    /*
     * Método utilizado no menu interativo que, com 
     * base no nome do dono de um veículo e sua seguradora, 
     * remove o veículo desejado 
     */
    public static boolean removerVeiculo(Seguradora seguradoraCliente) {
        int indiceFrota, indiceCliente = seguradoraCliente.escolheCliente();
        if (seguradoraCliente.getListaClientes().get(indiceCliente) instanceof ClientePF) {
            ((ClientePF) seguradoraCliente.getListaClientes().get(indiceCliente)).removerVeiculo();
        } else {
            indiceFrota = ((ClientePJ) seguradoraCliente.getListaClientes().get(indiceCliente)).escolheFrota();
            ((ClientePJ) seguradoraCliente.getListaClientes().get(indiceCliente)).getListaFrotas().get(indiceFrota).removeVeiculo();
        }
        return true;
    }
    
    /*
     * Método utilizado no menu interativo que, com 
     * base no nome do cliente associado ao sinistro
     * e sua seguradora, remove um sinistro dele
     */
    public static void removerSinistro(Seguradora seguradoraCliente) {
        System.out.println("Digite o nome do condutor associado ao sinistro que será removido.");
        String nomeCondutor = scan.nextLine();
        int indiceRemoverSinistro = -1;
        for (Seguro seguro : seguradoraCliente.getListaSeguros()) {
            for (int i = 0; i < seguro.getListaSinistros().size(); i++) {
                if (seguro.getListaSinistros().get(i).getCondutor().getNome() == nomeCondutor) {
                    indiceRemoverSinistro = i;
                    break;
                }
            }
            if (indiceRemoverSinistro == -1) {
                System.out.println("Nenhum sinistro foi encontrado associado ao condutor inserido. Por favor tente novamente.");
            } else {
                seguro.getListaSinistros().remove(indiceRemoverSinistro);
                System.out.println("O sinistro foi removido com sucesso.");
            }
        }
    }


    /*
     * Método interativo do funcionamento de todas as 
     * seguradoras. Funciona com base em um loop while 
     * geral e múltiplos switch case considerando o comando
     * inputado pelo usuário. Esses são "convertidos"
     * em operações de acordo com o enum MenuOperacoes
     */
    public static void menuInterativo() {
        int entradaPrimaria, entradaTemp, i = 0, j = 0;
        boolean loop = true;
        double entradaSecundaria;
        MenuOperacoes comandoPrimario, comandoSecundario;

        System.out.println("Bem-vindo ao sistema interativo de seguradoras.");
        // só acaba quando o usuário selecionar 0
        while (loop) {
            System.out.println("Qual serviço deseja acessar?\n" + 
                                "[1] - Cadastros\n" + 
                                "[2] - Listar\n" + 
                                "[3] - Excluir\n" + 
                                "[4] - Gerar Sinistro\n" + 
                                "[5] - Transferir Seguro\n" + 
                                "[6] - Calcular Receita Seguradora\n" + 
                                "[0] - Sair.");
            entradaPrimaria = scan.nextInt();
            scan.nextLine();
            comandoPrimario = MenuOperacoes.getEnumComando(entradaPrimaria);

            switch (comandoPrimario) {
                case CADASTRAR:
                    System.out.println("Deseja realizar qual função?\n" +
                                        "[1] - Cadastrar Cliente PF/PJ\n" + 
                                        "[2] - Cadastrar Veiculo\n" + 
                                        "[3] - Cadastrar Seguradora\n" + 
                                        "[4] - Cadastrar Frota\n" +
                                        "[5] - Cadastrar Condutor\n" + 
                                        "[6] - Voltar");
                    entradaSecundaria = scan.nextDouble();
                    scan.nextLine();
                    entradaSecundaria = entradaPrimaria + entradaSecundaria / 10;
                    comandoSecundario = MenuOperacoes.getEnumComando(entradaSecundaria);

                    switch (comandoSecundario) {
                        case CADASTRAR_CLIENTE_PF_PJ:
                            i = escolheSeguradora();
                            if (i >= 0) {
                                Seguradora seguradoraCliente = listaSeguradoras.get(i);
                                System.out.println("Deseja cadastrar um cliente físico (1) ou jurídico (2)?");
                                entradaTemp = scan.nextInt();
                                scan.nextLine();
                                if (entradaTemp == 1) {
                                    seguradoraCliente.cadastrarCliente("pf");
                                } else if (entradaTemp == 2) {
                                    seguradoraCliente.cadastrarCliente("pj");
                                } else {
                                    System.out.println("Por favor tente novamente.");
                                }
                            }
                            break;

                        case CADASTRAR_VEICULO:
                            i = escolheSeguradora();
                            if (i >= 0) {
                                Seguradora seguradoraVeiculo = listaSeguradoras.get(i);
                                int indiceCliente = seguradoraVeiculo.escolheCliente();
                                if (indiceCliente >= 0) {
                                    if (seguradoraVeiculo.getListaClientes().get(indiceCliente) instanceof ClientePF) {
                                        ((ClientePF) seguradoraVeiculo.getListaClientes().get(indiceCliente)).cadastrarVeiculo();
                                    } else {
                                        int indiceFrota = ((ClientePJ) seguradoraVeiculo.getListaClientes().get(indiceCliente)).escolheFrota();
                                        ((ClientePJ) seguradoraVeiculo.getListaClientes().get(indiceCliente)).getListaFrotas().get(indiceFrota).addVeiculo();
                                    }
                                }
                            }
                            break;

                        case CADASTRAR_SEGURADORA:
                            cadastrarSeguradora();
                            break;
                        
                        case CADASTRAR_FROTA:
                            i = escolheSeguradora();
                            if (i >= 0) {
                                Seguradora seguradoraFrota = listaSeguradoras.get(i);
                                int indiceCliente = seguradoraFrota.escolheCliente();
                                if (indiceCliente >= 0) {
                                    if (seguradoraFrota.getListaClientes().get(indiceCliente) instanceof ClientePF) {
                                        System.out.println("O cliente selecionado é do tipo físico. Portanto, não é possvível cadastrar uma frota em seu nome. Por favor tente novamente");
                                    } else {
                                        ((ClientePJ) seguradoraFrota.getListaClientes().get(indiceCliente)).cadastrarFrota();
                                    }
                                }
                            }
                            break;

                        case CADASTRAR_CONDUTOR:
                            i = escolheSeguradora();
                            if (i >= 0) {
                                Seguradora seguradoraCondutor = listaSeguradoras.get(i);
                                int indiceSeguro = seguradoraCondutor.escolheSeguro();
                                seguradoraCondutor.getListaSeguros().get(indiceSeguro).cadastrarCondutor();
                            }
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
                                        "[6] - Listar Seguros por Cliente\n" + 
                                        "[7] - Voltar");
                    entradaSecundaria = scan.nextDouble();
                    scan.nextLine();
                    entradaSecundaria = entradaPrimaria + entradaSecundaria / 10;
                    comandoSecundario = MenuOperacoes.getEnumComando(entradaSecundaria);

                    switch (comandoSecundario) {
                        case LISTAR_CLIENTES_POR_SEGURADORA:
                            System.out.println("Deseja visualizar os clientes físicos (1) ou jurídicos (2)?");
                            entradaTemp = scan.nextInt();
                            scan.nextLine();
                            if (listaSeguradoras.size() == 0) {
                                System.out.println("Não há nenhuma seguradora cadastrada.");
                            } else {
                                for (i = 0; i < listaSeguradoras.size(); i++) {
                                    if (entradaTemp == 1) {
                                        System.out.println("--- Clientes físicos da seguradora " + listaSeguradoras.get(i).getNome() + "---");
                                        listaSeguradoras.get(i).listarClientes("pf");
                                    } else {
                                        System.out.println("--- Clientes jurídicos da seguradora " + listaSeguradoras.get(i).getNome() + "---");
                                        listaSeguradoras.get(i).listarClientes("pj");
                                    }
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
                                    listaSeguradoras.get(i).visualizarSinistro(listaClientesTemp.get(j));
                                }
                            }
                            break;

                        case LISTAR_VEICULOS_POR_CLIENTE:
                            ArrayList<Veiculo> listaVeiculosTemp;
                            for (Seguradora seguradora : listaSeguradoras) {
                                listaClientesTemp = seguradora.getListaClientes();
                                for (j = 0; j < listaClientesTemp.size(); j++) {
                                    if (listaClientesTemp.get(j) instanceof ClientePF) {
                                        listaVeiculosTemp = ((ClientePF) listaClientesTemp.get(j)).getListaVeiculos();
                                        System.out.println("--- Veículos do cliente " + listaClientesTemp.get(j).getNome() + " ---");
                                        for (Veiculo veiculo : listaVeiculosTemp) {
                                            System.out.println(veiculo);
                                        }
                                    } else {
                                        ((ClientePJ) listaClientesTemp.get(j)).getVeiculosPorFrota();
                                    }
                                }
                            }
                            break;

                        case LISTAR_VEICULOS_POR_SEGURADORA:
                            for (Seguradora seguradora : listaSeguradoras) {
                                listaClientesTemp = seguradora.getListaClientes();
                                System.out.println("--- Veículos da seguradora " + listaSeguradoras.get(i).getNome());
                                for (j = 0; j < listaClientesTemp.size(); j++) {
                                    if (listaClientesTemp.get(j) instanceof ClientePF) {
                                        for (Veiculo veiculo : ((ClientePF) listaClientesTemp.get(j)).getListaVeiculos()) {
                                            System.out.println(veiculo);
                                        }
                                    } else {
                                        for (Frota frota : ((ClientePJ) listaClientesTemp.get(j)).getListaFrotas()) {
                                            for (Veiculo veiculo : frota.getListaVeiculos()) {
                                                System.out.println(veiculo);
                                            }
                                        }
                                    }
                                }
                            }
                            break;

                        case LISTAR_SEGUROS_POR_CLIENTE:
                            for (Seguradora seguradora: listaSeguradoras) {
                                for (Cliente cliente : seguradora.getListaClientes()) {
                                    ArrayList<Seguro> listaSegurosCliente = seguradora.getSegurosPorCliente(cliente);
                                    if (listaSegurosCliente.size() == 0) {
                                        System.out.println("O cliente " + cliente.getNome() + " não possui nenhum seguro cadastrado.");
                                    } else {
                                        System.out.println("--- Seguros do cliente " + cliente.getNome() + " ---");
                                        for (Seguro seguro : listaSegurosCliente) {
                                            System.out.println(seguro);
                                        }
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
                                        "[4] - Excluir Seguro\n" +
                                        "[5] - Excluir Condutor\n" +
                                        "[6] - Excluir Frota\n" +
                                        "[7] - Voltar");
                    entradaSecundaria = scan.nextDouble();
                    scan.nextLine();
                    entradaSecundaria = entradaPrimaria + entradaSecundaria / 10;
                    comandoSecundario = MenuOperacoes.getEnumComando(entradaSecundaria);

                    switch (comandoSecundario) {
                        case EXCLUIR_CLIENTE:
                            i = escolheSeguradora();
                            listaSeguradoras.get(i).removerCliente();
                            break;

                        case EXCLUIR_VEICULO:
                            i = escolheSeguradora();
                            removerVeiculo(listaSeguradoras.get(i));
                            break;
                            
                        case EXCLUIR_SINISTRO:
                            i = escolheSeguradora();
                            removerSinistro(listaSeguradoras.get(i));
                            break;
                        
                        case EXCLUIR_SEGURO:
                            i = escolheSeguradora();
                            listaSeguradoras.get(i).cancelarSeguro();
                            break;

                        case EXCLUIR_CONDUTOR:
                            i = escolheSeguradora();
                            int indiceSeguro = listaSeguradoras.get(i).escolheSeguro();
                            if (indiceSeguro >= 0) {
                                listaSeguradoras.get(i).getListaSeguros().get(indiceSeguro).removerCondutor();
                            }
                            break;

                        case EXCLUIR_FROTA:
                            i = escolheSeguradora();
                            int indiceCliente = listaSeguradoras.get(i).escolheCliente();
                            if (indiceCliente >= 0) {
                                if (listaSeguradoras.get(i).getListaClientes().get(indiceCliente) instanceof ClientePJ) {
                                    ClientePJ clienteExcluirFrota = ((ClientePJ) listaSeguradoras.get(i).getListaClientes().get(indiceCliente));
                                    int indiceFrota = clienteExcluirFrota.escolheFrota();
                                    clienteExcluirFrota.getListaFrotas().remove(indiceFrota);
                                } else {
                                    System.out.println("O cliente selecionado é do tipo físico. Portanto, não é possvível cadastrar uma frota em seu nome. Por favor tente novamente");
                                }
                            }
                            break;

                        case VOLTAR_EXCLUIR:
                            break;
                    }
                    break;
                
                case GERAR_SINISTRO:
                    i = escolheSeguradora();
                    int indiceSeguro = listaSeguradoras.get(i).escolheSeguro();
                    if (indiceSeguro < 0) {
                        System.out.println("Por favor tente novamente.");
                        break;
                    }
                    listaSeguradoras.get(i).getListaSeguros().get(indiceSeguro).gerarSinistro();
                    break;

                case TRANSFERIR_SEGURO:
                    i = escolheSeguradora();
                    listaSeguradoras.get(i).transferirSeguro();
                    break;

                case CALCULAR_RECEITA:
                    i = escolheSeguradora();
                    double receitaSeguradora = listaSeguradoras.get(i).calcularReceita();
                    System.out.println("A receita da seguradora " + listaSeguradoras.get(i).getNome() + " é " + receitaSeguradora);
                    break;

                case SAIR:
                    System.out.println("Encerrando sessão.");
                    loop = false;
                    break;

                default:
                    System.out.println("Por favor digite uma tecla válida.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        //INSTANCIAÇÃO DAS SEGURADORAS, CLIENTE E VEÍCULOS
        Seguradora seguradora1 = new Seguradora("Seguradora POO", 
                                            "11995807321",
                                            "email.seguradora@gmail.com",
                                            "Rua da Seguradora 101",
                                            new ArrayList<Cliente>(),
                                            new ArrayList<Seguro>(),
                                            "77.693.689/0001-01");

        Seguradora seguradora2 = new Seguradora("Seguradora do IC",
                                                "19123456789",
                                                "seguradora@ic.com.br",
                                                "Avenida Albert Einstein 100",
                                                new ArrayList<Cliente>(),
                                                new ArrayList<Seguro>(),
                                                "45.380.783/0001-14");

        ClientePF clientePF1 = new ClientePF("Pedro da Rosa",
                                            "Rua Pascal 99",
                                            "11995807321",
                                            "pedro@gmail.com",
                                            "Ensino Médio Completo",
                                            "Masculino",
                                            new ArrayList<Veiculo>(),
                                            "449.219.868-78", 
                                            LocalDate.of(2004, 04, 19));

        ClientePF clientePF2 = new ClientePF("Lucas da Rosa Pinheiro",
                                            "Avenida Doutor Romeu Tórtima 909",
                                            "11995804321",
                                            "lucas@gmail.com",
                                            "Ensino Superior Completo",
                                            "Masculino",
                                            new ArrayList<Veiculo>(),
                                            "562.297.046.68",
                                            LocalDate.of(1995, 11, 03));

        ClientePJ clientePJ1 = new ClientePJ("Apple",
                                            "Rua da Apple",
                                            "08007610867",
                                            "apple@gmail.com",
                                            10000,
                                            "00.623.904/0001-73",
                                            LocalDate.of(1976, 04, 01),
                                            new ArrayList<Frota>());

        ClientePJ clientePJ2 = new ClientePJ("Amazon",
                                            "Rua da Amazon",
                                            "08000380541",
                                            "amazon@gmail.com",
                                            25000,
                                            "15.436.940/0001-03",
                                            LocalDate.of(1994, 07, 5),
                                            new ArrayList<Frota>());

        Veiculo veiculoClientePF1 = new Veiculo("DDD2311",
                                            "Hyundai",
                                            "Civic",
                                            2020);

        Veiculo veiculoClientePF2 = new Veiculo("ECP21234",
                                            "Volkwswagen",
                                            "Nivus",
                                            2022);

        Veiculo veiculoClientePJ1 = new Veiculo ("ABC0000",
                                                "Toyota",
                                                "Supra",
                                                1998);

        Veiculo veiculoClientePJ2 = new Veiculo("GNG8764",
                                                "Subaru",
                                                "WRX",
                                                1995);
                                                
                                                //ADICIONANDO AS SEGURAODAS E CLIENTES À LISTA DE SEGURADORAS
                                                //duas seguradoras, cada uma com um cliente físico e um jurídico
        listaSeguradoras.add(seguradora1);
        listaSeguradoras.get(0).getListaClientes().add(clientePF1);
        listaSeguradoras.get(0).getListaClientes().add(clientePJ1);
        listaSeguradoras.add(seguradora2);
        listaSeguradoras.get(1).getListaClientes().add(clientePF2);
        listaSeguradoras.get(1).getListaClientes().add(clientePJ2);

        //ADICIONANDO OS VEÍCULOS AOS CLIENTES FÍSICOS
        ((ClientePF) listaSeguradoras.get(0).getListaClientes().get(0)).getListaVeiculos().add(veiculoClientePF1);
        ((ClientePF) listaSeguradoras.get(1).getListaClientes().get(0)).getListaVeiculos().add(veiculoClientePF2);
        
        //CRIANDO FROTA
        ((ClientePJ) listaSeguradoras.get(0).getListaClientes().get(1)).cadastrarFrota();
        ((ClientePJ) listaSeguradoras.get(1).getListaClientes().get(1)).cadastrarFrota();

        //ADICIONANDO OS VEÍCULOS ÀS FROTAS
        ((ClientePJ) listaSeguradoras.get(0).getListaClientes().get(1)).getListaFrotas().get(0).getListaVeiculos().add(veiculoClientePJ1);
        ((ClientePJ) listaSeguradoras.get(1).getListaClientes().get(1)).getListaFrotas().get(0).getListaVeiculos().add(veiculoClientePJ2);

        //GERANDO SEGUROS
        listaSeguradoras.get(0).gerarSeguro();
        listaSeguradoras.get(1).gerarSeguro();
        
        //GERANDO CONDUTORES
        Condutor condutorSeguradora1 = new Condutor("449.219.868-78",
                                                    "Pedro",
                                                    "11995807321",
                                                    "Rua Endereço",
                                                    "condutor@gmail.com",
                                                    LocalDate.of(2004, 04, 19),
                                                    new ArrayList<Sinistro>());

        Condutor condutorSeguradora2 = new Condutor("019.445.860-16",
                                                    "André Santos",
                                                    "62996978007",
                                                    "Rua da Casa do André",
                                                    "pixandre10@gmail.com",
                                                    LocalDate.of(2003, 11, 03),
                                                    new ArrayList<Sinistro>());

        listaSeguradoras.get(0).getListaSeguros().get(0).getListaCondutores().add(condutorSeguradora1);
        listaSeguradoras.get(1).getListaSeguros().get(0).getListaCondutores().add(condutorSeguradora2);

        //GERANDO SINISTROS
        listaSeguradoras.get(0).getListaSeguros().get(0).gerarSinistro();
        listaSeguradoras.get(1).getListaSeguros().get(0).gerarSinistro();

        //TO STRING DE CADA CLASSE
        System.out.println("TO STRING DA SEGURADORA");
        System.out.println(seguradora1);
        System.out.println("TO STRING DO CLIENTE FÍSICO");
        System.out.println(clientePF1);
        System.out.println("TO STRING DO CLIENTE JURÍDICO");
        System.out.println(clientePF2);
        System.out.println("TO STRING DO CONDUTOR");
        System.out.println(condutorSeguradora1);
        System.out.println("TO STRING DA FROTA");
        System.out.println(((ClientePJ) listaSeguradoras.get(0).getListaClientes().get(1)).getListaFrotas().get(0));
        System.out.println("TO STRING DO SEGURO FÍSICO");
        System.out.println(listaSeguradoras.get(0).getListaSeguros().get(0));
        System.out.println("TO STRING DO SEGURO JUŔIDICO");
        System.out.println(listaSeguradoras.get(0).getListaSeguros().get(0));
        System.out.println("TO STRING DO SINISTRO");
        System.out.println(listaSeguradoras.get(0).getListaSeguros().get(0).getListaSinistros().get(0));
        System.out.println("TO STRING DO VEÍCULO");
        System.out.println(veiculoClientePF1);

        //PRINCIPAIS MÉTODOS DA SEGURADORA
        
            //gerarSeguro já foi utilizado
        
        //     //cadastrarCliente 
        // listaSeguradoras.get(0).cadastrarCliente("pf");

        //     //listarClientes
        // listaSeguradoras.get(0).listarClientes("pf");

        //     //cancelarSeguro
        // listaSeguradoras.get(0).cancelarSeguro();

        //     //removerCliente
        // listaSeguradoras.get(0).removerCliente();

        //     //getSegurosPorCliente
        // ArrayList<Seguro> listaSegurosTemp = listaSeguradoras.get(0).getSegurosPorCliente(clientePF1);

        //     //getSinistrosPorCliente
        // ArrayList<Sinistro> listaSinistrosTemp = listaSeguradoras.get(0).getSinistrosPorCliente(clientePF1);

        //     // calcularReceita
        // System.out.println(listaSeguradoras.get(0).calcularReceita());

        menuInterativo();
    }
}