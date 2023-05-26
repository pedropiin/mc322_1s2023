import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientePF extends Cliente {
    Scanner scan = new Scanner(System.in);
    private String educacao;
    private String genero;
    private String cpf;
    private LocalDate dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome, 
                    String endereco,
                    String telefone,
                    String email,  
                    String educacao,
                    String genero, 
                    ArrayList<Veiculo> listaVeiculos,
                    String cpf, 
                    LocalDate dataNascimento) {
        super(nome, endereco, telefone, email);
        this.educacao = educacao;
        this.genero = genero;
        this.listaVeiculos = listaVeiculos;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        String all = "Nome: " + getNome() +
                "\nEndereço: " + getEndereco() +
                "\nNúmero de Telefone: " + getTelefone() + 
                "\nEmail: " + getEmail() +
                "\nGenero: " + getGenero() +
                "\nGrau de Educação: " + getEducacao() +
                "\nCPF: " + getCPF() +
                "\nData de Nascimento: " + getDataNascimento();

        return all;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String novaEducacao) {
        this.educacao = novaEducacao;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String novoGenero) {
        this.genero = novoGenero;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public String getCPF() {
        return this.cpf;
    }

    public void setCPF(String novoCPF) {
        this.cpf = novoCPF;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate novaDataNascimento) {
        this.dataNascimento = novaDataNascimento;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    /*
     * Método que cálcula o valor de cobrado de um cliente
     * por parte da seguradora, levando em conta taxas
     * associadas a sua idade.
     */
    public double calculaScore() {
        int idadeCliente = Period.between(dataNascimento, LocalDate.now()).getYears();
        int quantidadeCarros = this.getListaVeiculos().size();
        double score = 0;

        if (18 <= idadeCliente && idadeCliente < 30) {
            score = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_18_30.getValor() * quantidadeCarros;
        } else if (30 <= idadeCliente && idadeCliente < 60) {
            score = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_30_60.getValor() * quantidadeCarros;
        } else if (60 <= idadeCliente && idadeCliente <= 90) {
            score = CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_60_90.getValor() * quantidadeCarros;
        }
        
        return score;
    }

    public boolean cadastrarVeiculo() {
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
        getListaVeiculos().add(novoVeiculo);
        return true;
    }

    public int escolheVeiculo() {
        int indiceVeiculo, numVeiculos = listaVeiculos.size();
        if (numVeiculos == 0) {
            System.out.println("O cliente selecionado não possui nenhum veículo cadastrado.");
            return -1;
        } else {
            System.out.println("Selecione o veículo desejado");
            for (int i = 0; i < numVeiculos; i++) {
                System.out.println("(" + i + ") - " + listaVeiculos.get(i).getMarca() + " " + listaVeiculos.get(i).getModelo() + " " + listaVeiculos.get(i).getPlaca());
            }
            indiceVeiculo = scan.nextInt();
            scan.nextLine();

            return indiceVeiculo;
        }
    }

    public boolean removerVeiculo() {
        if (getListaVeiculos().size() == 0) {
            System.out.println("O cliente " + getNome() + " não possui nenhum veículo cadastrado.");
            return false;
        } else {
            Veiculo veiculoIteracao;
            System.out.println("Digite o número relacionado ao veículo desejado.");
            for (int i = 0; i < listaVeiculos.size(); i++) {
                veiculoIteracao = listaVeiculos.get(i);
                System.out.println("(" + i + ") - " + veiculoIteracao.getMarca() + " " + veiculoIteracao.getModelo()
                        + " " + veiculoIteracao.getPlaca());
            }
            int entrada = scan.nextInt();
            scan.nextLine();
            if (entrada < 0 || entrada > listaVeiculos.size() - 1) {
                System.out.println("Por favor tente novamente, digitando um número válido.");
                return false;
            } else {
                listaVeiculos.remove(entrada);
                System.out.println("Veículo removido com sucesso");
                return true;
            }
        }
    }
}