package lrz.ifpe.vinisweb.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Tipo do cliente (ex.: Regular, VIP)
    @Column(nullable = false)
    private String tipoCliente;
    
    // Histórico simples de compras relacionadas ao cliente (associação)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Compra> historicoCompras;

    public Cliente() {
        this.historicoCompras = new ArrayList<>();
    }

    public Cliente(String nome, String cpf, String email, String tipoCliente) {
        super(nome, cpf, email);
        this.tipoCliente = tipoCliente;
        this.historicoCompras = new ArrayList<>();
    }

    // Adiciona uma compra no histórico
    public void adicionarCompra(Compra compra) {
        historicoCompras.add(compra);
    }

    @Override
    public void exibirInfo() {
        System.out.println("Cliente: " + nome + " | Tipo: " + tipoCliente);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }
    
    public List<Compra> getHistoricoCompras() { return historicoCompras; }
    public void setHistoricoCompras(List<Compra> historicoCompras) { this.historicoCompras = historicoCompras; }
}
