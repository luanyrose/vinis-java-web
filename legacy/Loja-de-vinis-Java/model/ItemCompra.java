/**
 * Classe que representa um item individual de uma compra.
 * Liga um vinil específico a uma compra, armazenando a quantidade comprada
 * e calculando o valor total do item.
 * Mantém uma relação bidirecional com Vinil.
 */
public class ItemCompra {
    // Vinil que está sendo comprado (relação direta com Vinil)
    private Vinil vinil;
    
    // Quantidade de unidades deste vinil na compra
    private int quantidade;
    
    // Valor total deste item (preço do vinil × quantidade)
    private double valorItem;

    /**
     * Construtor da classe ItemCompra.
     * Cria um item de compra vinculado a um vinil específico.
     * @param vinil O vinil que está sendo comprado (não pode ser nulo)
     * @param quantidade A quantidade de unidades do vinil a serem compradas
     * @throws IllegalArgumentException Se o vinil for nulo
     */
    public ItemCompra(Vinil vinil, int quantidade) {
        // Validação: garante que o vinil não seja nulo
        if (vinil == null) {
            throw new IllegalArgumentException("ItemCompra deve estar ligado a um Vinil");
        }
        this.vinil = vinil;
        this.quantidade = quantidade;
        
        // Calcula o valor total do item multiplicando preço pela quantidade
        this.valorItem = vinil.getPrecoVenda() * quantidade;
        
        // Registra este ItemCompra no Vinil para criar a relação bidirecional
        // Isso permite que o vinil saiba em quais compras ele foi vendido
        vinil.adicionarItemCompra(this);
    }

    /**
     * Retorna o vinil associado a este item de compra.
     * @return O objeto Vinil deste item
     */
    public Vinil getVinil() {
        return vinil;
    }

    /**
     * Retorna a quantidade de unidades deste item na compra.
     * @return A quantidade comprada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Retorna o valor total deste item (preço × quantidade).
     * @return O valor calculado do item
     */
    public double getValorItem() {
        return valorItem;
    }
}
