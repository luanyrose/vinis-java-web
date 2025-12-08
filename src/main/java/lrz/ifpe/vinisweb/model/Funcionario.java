package lrz.ifpe.vinisweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Funcionario extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Cargo e salário do funcionário da loja
    @Column(nullable = false)
    private String cargo;
    
    @Column(nullable = false)
    private double salario;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, String email, String cargo, double salario) {
        super(nome, cpf, email);
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Funcionário: " + nome + " | Cargo: " + cargo + " | Salário: R$" + salario);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
