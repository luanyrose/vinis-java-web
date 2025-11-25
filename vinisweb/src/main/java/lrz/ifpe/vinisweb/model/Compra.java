import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    // Data da compra, cliente relacionado e itens (composição)
    private Date dataCompra;
    private Cliente cliente;
    private double valorTotal;
    private List<ItemCompra> itens;

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
        itens.add(item);
        valorTotal += item.getValorItem();
        vinil.diminuirEstoque(quantidade);
    }

    public double getValorTotal() { return valorTotal; }
    public List<ItemCompra> getItens() { return itens; }

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
