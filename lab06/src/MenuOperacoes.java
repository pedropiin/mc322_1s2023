public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA(6),
    AUTORIZAR_CONDUTOR(7),
    DESAUTORIZAR_CONDUTOR(8),
    SAIR(0),

    CADASTRAR_CLIENTE_PF_PJ(1.1),
    CADASTRAR_VEICULO(1.2),
    CADASTRAR_SEGURADORA(1.3),
    CADASTRAR_FROTA(1.4),
    CADASTRAR_CONDUTOR(1.5),
    VOLTAR_CADASTRO(1.6),

    LISTAR_CLIENTES_POR_SEGURADORA(2.1),
    LISTAR_SINISTROS_POR_SEGURADORA(2.2),
    LISTAR_SINISTROS_POR_CLIENTE(2.3),
    LISTAR_VEICULOS_POR_CLIENTE(2.4),
    LISTAR_VEICULOS_POR_SEGURADORA(2.5),
    LISTAR_SEGUROS_POR_CLIENTE(2.6),
    VOLTAR_LISTAR(2.7),

    EXCLUIR_CLIENTE(3.1),
    EXCLUIR_VEICULO(3.2),
    EXCLUIR_SINISTRO(3.3),
    EXCLUIR_SEGURO(3.4),
    EXCLUIR_CONDUTOR(3.5),
    EXCLUIR_FROTA(3.6),
    VOLTAR_EXCLUIR(3.7),

    ERRO(-1);

    public final double operacao;
    MenuOperacoes(double operacao) {
        this.operacao = operacao;
    }

    public double getOperacao() {
        return operacao;
    }

    public static MenuOperacoes getEnumComando(double entrada) {
        for (MenuOperacoes comando : MenuOperacoes.values()) {
            if (comando.getOperacao() == entrada) {
                return comando;
            }
        }
        return ERRO;
    }
}