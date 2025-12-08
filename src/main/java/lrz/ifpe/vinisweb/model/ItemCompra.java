package lrz.ifpe.vinisweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Item que liga um vinil à compra, com quantidade e valor calculado
    @ManyToOne
    private Vinil vinil;
    
    @ManyToOne
    private Compra compra;
    
    @Column(nullable = false)
    private int quantidade;
    
    @Column(nullable = false)
    private double valorItem;

    public ItemCompra() {
    }

    public ItemCompra(Vinil vinil, int quantidade) {
        this.vinil = vinil;
        this.quantidade = quantidade;
        this.valorItem = vinil.getPrecoVenda() * quantidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Vinil getVinil() {
        return vinil;
    }
    public void setVinil(Vinil vinil) {
        this.vinil = vinil;
        if (vinil != null && quantidade > 0) {
            this.valorItem = vinil.getPrecoVenda() * quantidade;
        }
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        if (vinil != null && quantidade > 0) {
            this.valorItem = vinil.getPrecoVenda() * quantidade;
        }
    }

    public double getValorItem() {
        return valorItem;
    }
    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }
    
    public Compra getCompra() { return compra; }
    public void setCompra(Compra compra) { this.compra = compra; }
}
