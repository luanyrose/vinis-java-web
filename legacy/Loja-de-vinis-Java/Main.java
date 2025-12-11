import java.util.Date;

/**
 * Classe principal que contém o método main.
 * Demonstra o funcionamento do sistema de loja de vinis,
 * criando exemplos de vinis, estoque, clientes, funcionários e compras.
 */
public class Main {
    /**
     * Método principal que executa o programa.
     * Cria objetos de exemplo e demonstra os relacionamentos entre classes.
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // ========== CRIAÇÃO DE VINIS ==========
        // Cria dois vinis de exemplo com suas informações
        // Parâmetros: código, título, artista, preço, quantidade disponível, gênero
        Vinil v1 = new Vinil(1, "Igor", "Tyler, the Creator", 80.0, 5, "Rap");
        Vinil v2 = new Vinil(2, "Puberty 2", "Mitski", 60.0, 3, "Indie");

        // ========== CRIAÇÃO DO ESTOQUE ==========
        // Cria o estoque e adiciona os vinis (demonstra agregação)
        // Os vinis existem independentemente do estoque
        Estoque estoque = new Estoque();
        estoque.adicionarVinil(v1);
        estoque.adicionarVinil(v2);

        // ========== CRIAÇÃO DE CLIENTES ==========
        // Cria dois clientes de exemplo (demonstra herança de Pessoa)
        // Parâmetros: nome, CPF, email, tipo de cliente
        Cliente c1 = new Cliente("Ana", "00000000000", "ana@mail.com", "Regular");
        Cliente c2 = new Cliente("Bruno", "11111111111", "bruno@mail.com", "VIP");

        // ========== CRIAÇÃO DE FUNCIONÁRIOS ==========
        // Cria dois funcionários de exemplo (demonstra herança de Pessoa)
        // Parâmetros: nome, CPF, email, cargo, salário
        Funcionario f1 = new Funcionario("Carla", "22222222222", "carla@mail.com", "Atendente", 2500.0);
        Funcionario f2 = new Funcionario("Diego", "33333333333", "diego@mail.com", "Gerente", 3800.0);

        // ========== CRIAÇÃO DE COMPRAS ==========
        // Cria a primeira compra para o cliente Ana
        // Demonstra composição (ItemCompra) e associação (Cliente)
        Compra comp1 = new Compra(new Date(), c1);
        comp1.adicionarItem(v1, 2); // Adiciona 2 unidades do vinil v1
        comp1.adicionarItem(v2, 1); // Adiciona 1 unidade do vinil v2
        c1.adicionarCompra(comp1); // Registra a compra no histórico do cliente

        // Cria a segunda compra para o cliente Bruno
        Compra comp2 = new Compra(new Date(), c2);
        comp2.adicionarItem(v2, 2); // Adiciona 2 unidades do vinil v2
        c2.adicionarCompra(comp2); // Registra a compra no histórico do cliente

        // ========== EXIBIÇÃO DE RESULTADOS ==========
        
        // Exibe informações dos vinis criados
        System.out.println("=== VINIS ===");
        System.out.println(v1);
        System.out.println(v2);

        // Exibe a quantidade disponível de cada vinil no estoque
        // Após as compras, as quantidades devem ter diminuído
        System.out.println("\n=== ESTOQUE (qtd disponível) ===");
        System.out.println(v1.getTitulo() + ": " + estoque.quantidadeDisponivel(v1));
        System.out.println(v2.getTitulo() + ": " + estoque.quantidadeDisponivel(v2));

        // Exibe informações de pessoas (clientes e funcionários)
        // Demonstra o polimorfismo através do método exibirInfo() herdado de Pessoa
        System.out.println("\n=== PESSOAS ===");
        c1.exibirInfo();
        c2.exibirInfo();
        f1.exibirInfo();
        f2.exibirInfo();

        // Exibe informações das compras realizadas
        // Mostra a composição (itens) e os valores totais calculados
        System.out.println("\n=== COMPRAS ===");
        System.out.println(comp1);
        System.out.println("Total 1: R$ " + comp1.getValorTotal());
        System.out.println(comp2);
        System.out.println("Total 2: R$ " + comp2.getValorTotal());
    }
}