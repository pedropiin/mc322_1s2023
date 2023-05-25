import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientePJ extends Cliente {
    Scanner scan = new Scanner(System.in);
    private int quantidadeFuncs;
    private String cnpj;
    private LocalDate dataFundacao;
    private ArrayList<Frota> listaFrotas;

    public ClientePJ(String nome, 
                    String endereco, 
                    String telefone,
                    String email,
                    int quantidadeFuncs,
                    String cnpj,
                    LocalDate dataFundacao, 
                    ArrayList<Frota> listaFrotas) {
        super(nome, endereco, telefone, email);
        this.quantidadeFuncs = quantidadeFuncs;
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.listaFrotas = listaFrotas;
    }

    @Override
    public String toString() {
        String all = "Nome: " + getNome() +
                "\nEndereço: " + getEndereco() +
                "\nNúmero de Telefone: " + getTelefone() +
                "\nEmail: " + getEmail() + 
                "\nQuantidade de Funcionários: " + getQuantidadeFuncs() + 
                "\nCNPJ: " + getCNPJ() +
                "\nData da Fundação: " + getDataFundacao();

        return all;
    }

    public int getQuantidadeFuncs() {
        return this.quantidadeFuncs;
    }

    public void setQuantidadeFuncs(int novaQuantidadeFuncs) {
        this.quantidadeFuncs = novaQuantidadeFuncs;
    }

    public String getCNPJ() {
        return this.cnpj;
    }

    public void setCNPJ(String novoCNPJ) {
        this.cnpj = novoCNPJ;
    }

    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate novaDataFundacao) {
        this.dataFundacao = novaDataFundacao;
    }

    public ArrayList<Frota> getListaFrotas() {
        return this.listaFrotas;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    public boolean atualizarFrota() {
        int entrada, indiceFrota = escolheFrota();
        System.out.println("Selecione a função desejada?\n" + 
                            "[1] - Adicionar um veículo à frota\n" + 
                            "[2] - Remover um veículo da frota\n" + 
                            "[3] - Remover frota");
        entrada = scan.nextInt();
        scan.nextLine();
        if (entrada == 1) {
            listaFrotas.get(indiceFrota).addVeiculo();
        } else if (entrada == 2) {
            listaFrotas.get(indiceFrota).removeVeiculo();
        } else if (entrada == 3) {
            System.out.println("Frota de código " + listaFrotas.get(indiceFrota).getCode() + " removida.");
            listaFrotas.remove(indiceFrota);
        } else {
            System.out.println("Por favor selecione uma opção válida.");
            return false;
        }
        return true;
    }

    public boolean cadastrarFrota() {
        Frota novaFrota = new Frota(new ArrayList<Veiculo>());
        getlistaFrotas().add(novaFrota);
        System.out.println("Uma nova frota (código: " + novaFrota.getCode() + ") foi cadastrada com sucesso");
        return true;
    }

    public int escolheFrota() {
		int indiceFrota;
		System.out.println("Selecione a frota desejada.");
		for (int i = 0; i < listaFrotas.size(); i++) {
			System.out.println("(" + i + ") - " + listaFrotas.get(i).getCode());
		}
		indiceFrota = scan.nextInt();
		scan.nextLine();

		return indiceFrota;
	}

    public boolean getVeiculosPorFrota() {
        if (getlistaFrotas().size() == 0) {
            System.out.println("O cliente " + getNome() + " não possui nenhuma frota cadastrada.");
            return false;
        } else {
            for (Frota frota : getlistaFrotas()) {
                System.out.println("---Veículos da frota de código " + frota.getCode() + "---");
                for (Veiculo veiculo : frota.getListaVeiculos()) {
                    System.out.println(veiculo.getMarca() + " " + veiculo.getModelo() + " " + veiculo.getPlaca());
                }
            }
            return true;
        }
    }
}