import java.util.ArrayList;
import java.util.Random;

public class Frota {
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

    public String geraCode() { 
        Random rand = new Random();
        String code = "";
        int minCharMinusculo = 97, minCharMaiusculo = 65, maxRangeAscii = 26;
        char novoChar;

        for (int i = 0; i < 20; i++) {
            int asciiNovoChar = rand.nextInt(maxRangeAscii);
            if (i % 2 == 0) {
                novoChar = (char)(asciiNovoChar + minCharMinusculo);
            } else {
                novoChar = (char)(asciiNovoChar + minCharMaiusculo);
            }
            code += novoChar;
        }

        return code;
    }
}