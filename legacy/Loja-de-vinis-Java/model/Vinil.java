import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um disco de vinil vendido na loja.
 * Armazena informações do produto e mantém controle de estoque.
 * Mantém uma relação bidirecional com ItemCompra para rastrear em quais compras foi vendido.
 */
public class Vinil {
    // Código único de identificação do vinil
    private int codigo;
    
    // Título do álbum/disco
    private String titulo;
    
    // Nome do artista ou banda
    private String artista;
    
    // Preço de venda do vinil
    private double precoVenda;
    
    // Gênero musical do vinil (ex.: Rap, Indie, Rock)
    private String genero;
    
    // Quantidade de unidades disponíveis no estoque
    private int qtdDisponivel;
    
    // Lista de itens de compra que contêm este vinil
    // Relação bidirecional: o vinil conhece os itens de compra que o referenciam
    private List<ItemCompra> itensCompra;

    /**
     * Construtor da classe Vinil.
     * @param codigo Código único de identificação
     * @param titulo Título do álbum
     * @param artista Nome do artista ou banda
     * @param precoVenda Preço de venda do vinil
     * @param qtdDisponivel Quantidade inicial disponível no estoque
     * @param genero Gênero musical
     */
    public Vinil(int codigo, String titulo, String artista,
                 double precoVenda, int qtdDisponivel,
                 String genero) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.artista = artista;
        this.precoVenda = precoVenda;
        this.qtdDisponivel = qtdDisponivel;
        this.genero = genero;
        this.itensCompra = new ArrayList<>(); // Inicializa lista vazia
    }

    // ========== MÉTODOS GETTERS ==========
    // Retornam os valores dos atributos privados
    
    public int getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public double getPrecoVenda() { return precoVenda; }
    public String getGenero() { return genero; }
    public int getQtdDisponivel() { return qtdDisponivel; }
    public List<ItemCompra> getItensCompra() { return itensCompra; }

    /**
     * Adiciona um ItemCompra à lista de itens que referenciam este vinil.
     * Este método é chamado automaticamente pelo construtor de ItemCompra
     * para manter a relação bidirecional.
     * @param itemCompra O item de compra que referencia este vinil
     */
    public void adicionarItemCompra(ItemCompra itemCompra) {
        // Validação: só adiciona se o item não for nulo e ainda não estiver na lista
        if (itemCompra != null && !itensCompra.contains(itemCompra)) {
            itensCompra.add(itemCompra);
        }
    }

    /**
     * Diminui a quantidade disponível no estoque deste vinil.
     * Usado quando uma compra é realizada.
     * Protege contra valores negativos e quantidades inválidas.
     * @param quantidade A quantidade a ser diminuída do estoque
     */
    public void diminuirEstoque(int quantidade) {
        // Validação: não permite diminuir quantidade inválida
        if (quantidade <= 0) return;
        
        // Só diminui se houver estoque suficiente
        if (qtdDisponivel >= quantidade) {
            qtdDisponivel -= quantidade;
        }
    }

    /**
     * Retorna uma representação em string do vinil.
     * @return String formatada com as informações principais do vinil
     */
    @Override
    public String toString() {
        return "Vinil{" +
                "codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", genero='" + genero + '\'' +
                ", precoVenda=" + precoVenda +
                ", qtdDisponivel=" + qtdDisponivel +
                '}';
    }
}