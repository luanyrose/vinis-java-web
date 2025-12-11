import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que representa uma compra realizada na loja.
 * Utiliza composição com ItemCompra (os itens não existem sem a compra)
 * e associação com Cliente (o cliente existe independentemente da compra).
 */
public class Compra {
    // Data em que a compra foi realizada
    private Date dataCompra;
    
    // Cliente que realizou a compra (relacionamento de associação)
    private Cliente cliente;
    
    // Valor total da compra (soma de todos os itens)
    private double valorTotal;
    
    // Lista de itens da compra (relacionamento de composição)
    // Os itens só existem como parte desta compra
    private List<ItemCompra> itens;

    /**
     * Construtor da classe Compra.
     * @param dataCompra Data em que a compra foi realizada
     * @param cliente Cliente que está realizando a compra
     */
    public Compra(Date dataCompra, Cliente cliente) {
        this.dataCompra = dataCompra;
        this.cliente = cliente;
        this.valorTotal = 0.0; // Inicializa o valor total como zero
        this.itens = new ArrayList<>(); // Inicializa lista vazia de itens
    }

    /**
     * Adiciona um item à compra.
     * Cria um ItemCompra, adiciona na lista de itens, atualiza o valor total
     * e diminui a quantidade disponível no estoque do vinil.
     * @param vinil O vinil que está sendo comprado
     * @param quantidade A quantidade de unidades do vinil a serem compradas
     */
    public void adicionarItem(Vinil vinil, int quantidade) {
        // Validação: não permite adicionar vinil nulo ou quantidade inválida
        if (vinil == null || quantidade <= 0) return;
        
        // Cria um novo item de compra
        ItemCompra item = new ItemCompra(vinil, quantidade);
        
        // Adiciona o item à lista de itens da compra
        itens.add(item);
        
        // Atualiza o valor total da compra somando o valor do item
        valorTotal += item.getValorItem();
        
        // Diminui a quantidade disponível no estoque do vinil
        vinil.diminuirEstoque(quantidade);
    }

    /**
     * Retorna o valor total da compra.
     * @return O valor total calculado da compra
     */
    public double getValorTotal() { return valorTotal; }
    
    /**
     * Retorna a lista de itens da compra.
     * @return Lista de todos os itens que compõem esta compra
     */
    public List<ItemCompra> getItens() { return itens; }
    
    /**
     * Retorna a data da compra.
     * @return A data em que a compra foi realizada
     */
    public Date getDataCompra() { return dataCompra; }
    
    /**
     * Retorna o cliente que realizou a compra.
     * @return O objeto Cliente associado à compra
     */
    public Cliente getCliente() { return cliente; }

    /**
     * Retorna uma representação em string da compra.
     * @return String formatada com as informações principais da compra
     */
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
