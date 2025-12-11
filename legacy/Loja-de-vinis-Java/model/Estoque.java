import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa o estoque da loja de vinis.
 * Utiliza o relacionamento de agregação: o estoque conhece vários vinis,
 * mas os vinis podem existir independentemente do estoque.
 */
public class Estoque {
    // Lista que armazena todos os vinis disponíveis no estoque
    private List<Vinil> vinis;

    /**
     * Construtor da classe Estoque.
     * Inicializa a lista de vinis como uma ArrayList vazia.
     */
    public Estoque() {
        vinis = new ArrayList<>();
    }

    /**
     * Adiciona um vinil à lista do estoque.
     * @param vinil O objeto Vinil a ser adicionado ao estoque
     */
    public void adicionarVinil(Vinil vinil) {
        vinis.add(vinil);
    }

    /**
     * Consulta a quantidade disponível de um vinil específico no estoque.
     * @param vinil O vinil cuja quantidade deseja-se verificar
     * @return A quantidade disponível do vinil informado
     */
    public int quantidadeDisponivel(Vinil vinil) {
        return vinil.getQtdDisponivel();
    }

    /**
     * Retorna a lista completa de vinis do estoque.
     * @return Lista de todos os vinis cadastrados no estoque
     */
    public List<Vinil> getVinis() {
        return vinis;
    }
}
