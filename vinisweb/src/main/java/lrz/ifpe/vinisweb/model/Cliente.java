import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {
    // Tipo do cliente (ex.: Regular, VIP)
    private String tipoCliente;
    // Histórico simples de compras relacionadas ao cliente (associação)
    private List<Compra> historicoCompras;

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
}
