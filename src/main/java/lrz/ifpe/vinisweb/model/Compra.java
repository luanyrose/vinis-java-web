package lrz.ifpe.vinisweb.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Data da compra, cliente relacionado e itens (composição)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dataCompra;
    
    @ManyToOne
    private Cliente cliente;
    
    @Column(nullable = false)
    private double valorTotal;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<ItemCompra> itens;

    public Compra() {
        this.valorTotal = 0.0;
        this.itens = new ArrayList<>();
    }

    public Compra(Date dataCompra, Cliente cliente) {
        this.dataCompra = dataCompra;
        this.cliente = cliente;
        this.valorTotal = 0.0;
        this.itens = new ArrayList<>();
    }

    // Cria um ItemCompra, adiciona na lista e soma no total
    public void adicionarItem(Vinil vinil, int quantidade) {
        if (vinil == null || quantidade <= 0) return;
        ItemCompra item = new ItemCompra(vinil, quantidade);
        item.setCompra(this);
        itens.add(item);
        valorTotal += item.getValorItem();
        vinil.diminuirEstoque(quantidade);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Date getDataCompra() { return dataCompra; }
    public void setDataCompra(Date dataCompra) { this.dataCompra = dataCompra; }
    
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    
    public List<ItemCompra> getItens() { return itens; }
    public void setItens(List<ItemCompra> itens) { this.itens = itens; }

    @Override
    public String toString() {
        return "Compra{" +
                "dataCompra=" + dataCompra +
                ", cliente=" + (cliente != null ? cliente.getNome() : "-") +
                ", valorTotal=" + valorTotal +
                ", itens=" + itens.size() +
                '}';
    }
}
