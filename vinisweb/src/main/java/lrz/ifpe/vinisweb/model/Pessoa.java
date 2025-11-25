// Classe base (mãe) de pessoas do sistema.
// Outras classes herdam dela para reaproveitar nome, cpf e email.
public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String email;

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public abstract void exibirInfo();

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
}
