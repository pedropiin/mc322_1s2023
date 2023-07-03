import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Frota {
    static Scanner scan = new Scanner(System.in);
    private String code;
    private ArrayList<Veiculo> listaVeiculos;

    public Frota(ArrayList<Veiculo> listaVeiculos) {
        this.code = geraCode();
        this.listaVeiculos = listaVeiculos;
    }

    public String toString() {
        String all = "Code: " + getCode();
        return all;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String novoCode) {
        this.code = novoCode;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES

    /*
     * Método que solicita ao usuário todas as informações
     * necessárias para a criação de um veículo. Assim,
     * instancia um objeto dessa classe e o adiciona na 
     * lista de veículos da frota
     */
    public boolean addVeiculo() {
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

    /*
     * Método que mostra ao usuário todas os veículos cadastradas
     * no cliente e retorna um inteiro que representa
     * o índice do veículo que o usuário deseja utilizar
     */
    public int escolheVeiculo() {
        int indiceVeiculo, numVeiculos = listaVeiculos.size();
        if (numVeiculos == 0) {
            System.out.println("A frota selecionada não possui nenhum veículo cadastrado.");
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

    /*
     * Método que gera uma string de 20 caracteres,
     * intercalando entre maiúsculos e minúsculos,
     * de modo a criar um código aleatório para cada frota
     */
    public String geraCode() {
        Random rand = new Random();
        String code = "";
        int minCharMinusculo = 97, minCharMaiusculo = 65, maxRangeAscii = 26;
        char novoChar;

        for (int i = 0; i < 20; i++) {
            int asciiNovoChar = rand.nextInt(maxRangeAscii);
            if (i % 2 == 0) {
                novoChar = (char) (asciiNovoChar + minCharMinusculo);
            } else {
                novoChar = (char) (asciiNovoChar + minCharMaiusculo);
            }
            code += novoChar;
        }

        return code;
    }

    /*
     * Método que solicita ao usuário o veículo que deseja
     * remover e o tira da lista de veículos da frota
     */
    public boolean removeVeiculo() {
        int indiceVeiculo = escolheVeiculo();
        if (indiceVeiculo >= 0) {
            getListaVeiculos().remove(indiceVeiculo);
            System.out.println("Veículo removido com sucesso.");
            return true;
        }
        return false;
    }
}