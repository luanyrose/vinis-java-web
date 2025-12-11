/**
 * Classe abstrata base que representa uma pessoa no sistema.
 * Serve como classe pai (superclasse) para Cliente e Funcionario.
 * Utiliza herança para evitar duplicação de código comum (nome, CPF, email).
 * É abstrata porque não faz sentido instanciar uma "Pessoa" genérica.
 */
public abstract class Pessoa {
    // Nome completo da pessoa (protected para permitir acesso nas classes filhas)
    protected String nome;
    
    // CPF da pessoa (protected para permitir acesso nas classes filhas)
    protected String cpf;
    
    // Email de contato da pessoa (protected para permitir acesso nas classes filhas)
    protected String email;

    /**
     * Construtor da classe Pessoa.
     * @param nome Nome completo da pessoa
     * @param cpf CPF da pessoa
     * @param email Email de contato da pessoa
     */
    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    /**
     * Método abstrato que deve ser implementado pelas classes filhas.
     * Cada tipo de pessoa (Cliente, Funcionario) exibe suas informações de forma diferente.
     */
    public abstract void exibirInfo();

    /**
     * Retorna o nome da pessoa.
     * @return O nome completo
     */
    public String getNome() { return nome; }
    
    /**
     * Retorna o CPF da pessoa.
     * @return O CPF
     */
    public String getCpf() { return cpf; }
    
    /**
     * Retorna o email da pessoa.
     * @return O email de contato
     */
    public String getEmail() { return email; }
}
