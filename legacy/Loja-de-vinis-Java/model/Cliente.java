import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um cliente da loja.
 * Herda da classe Pessoa, adicionando informações específicas de cliente.
 * Mantém um histórico de compras realizadas (relacionamento de associação).
 */
public class Cliente extends Pessoa {
    // Tipo do cliente (ex.: Regular, VIP, Premium)
    private String tipoCliente;
    
    // Histórico de compras realizadas pelo cliente
    // Relacionamento de associação: o cliente conhece suas compras,
    // mas as compras também podem existir sem o cliente
    private List<Compra> historicoCompras;

    /**
     * Construtor da classe Cliente.
     * @param nome Nome completo do cliente
     * @param cpf CPF do cliente
     * @param email Email de contato do cliente
     * @param tipoCliente Tipo/categoria do cliente (Regular, VIP, etc.)
     */
    public Cliente(String nome, String cpf, String email, String tipoCliente) {
        super(nome, cpf, email); // Chama o construtor da classe pai (Pessoa)
        this.tipoCliente = tipoCliente;
        this.historicoCompras = new ArrayList<>(); // Inicializa lista vazia
    }

    /**
     * Adiciona uma compra ao histórico do cliente.
     * @param compra A compra realizada que será registrada no histórico
     */
    public void adicionarCompra(Compra compra) {
        historicoCompras.add(compra);
    }

    /**
     * Exibe as informações do cliente no console.
     * Sobrescreve o método abstrato da classe pai (Pessoa).
     */
    @Override
    public void exibirInfo() {
        System.out.println("Cliente: " + nome + " | Tipo: " + tipoCliente);
    }
    
    /**
     * Retorna o tipo do cliente.
     * @return O tipo/categoria do cliente
     */
    public String getTipoCliente() {
        return tipoCliente;
    }
}
