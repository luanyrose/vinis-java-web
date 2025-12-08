package lrz.ifpe.vinisweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

// Classe base (mãe) de pessoas do sistema.
// Outras classes herdam dela para reaproveitar nome, cpf e email.
@MappedSuperclass
public abstract class Pessoa {
    @Column(nullable = false)
    protected String nome;
    
    @Column(nullable = false, unique = true)
    protected String cpf;
    
    @Column(nullable = false)
    protected String email;

    public Pessoa() {
    }

    public Pessoa(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public abstract void exibirInfo();

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
