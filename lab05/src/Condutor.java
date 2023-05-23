import java.time.LocalDate;
import java.util.ArrayList;

public class Condutor {
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNascimento;
    private ArrayList<Sinistro> listaSinistros;

    public Condutor(String cpf,
                    String nome,
                    String telefone,
                    String endereco,
                    String email,
                    LocalDate dataNascimento,
                    ArrayList<Sinistro> listaSinistros) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = listaSinistros;
    }

    public String toString() {
        String all = "Cpf: " + getCPF() + 
                    "\nNome: " + getNome() + 
                    "\nTelefone: " + getTelefone() + 
                    "\nEndereço: " + getEndereco() + 
                    "\nEmail: " + getEmail() + 
                    "\nData de Nascimento: " + getDataNascimento();

        return all;
    }

    public String getCPF() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String novoEndereco) {
        this.endereco = novoEndereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate novaDataNascimento) {
        this.dataNascimento = novaDataNascimento;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    //INÍCIO DOS MÉTODOS NÃO PADRÕES 

    public void adicionarSinistro() {
        
    }
}