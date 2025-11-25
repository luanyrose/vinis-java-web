package lrz.ifpe.vinisweb.model;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // Vinis (2 exemplos)
        Vinil v1 = new Vinil(1, "Igor", "Tyler, the Creator", 80.0, 5, "Rap");
        Vinil v2 = new Vinil(2, "Puberty 2", "Mitski", 60.0, 3, "Indie");

        // Estoque (agregação com vinis)
        Estoque estoque = new Estoque();
        estoque.adicionarVinil(v1);
        estoque.adicionarVinil(v2);

        // Clientes (2 exemplos) – herança de Pessoa
        Cliente c1 = new Cliente("Ana", "00000000000", "ana@mail.com", "Regular");
        Cliente c2 = new Cliente("Bruno", "11111111111", "bruno@mail.com", "VIP");

        // Funcionários (2 exemplos) – herança de Pessoa
        Funcionario f1 = new Funcionario("Carla", "22222222222", "carla@mail.com", "Atendente", 2500.0);
        Funcionario f2 = new Funcionario("Diego", "33333333333", "diego@mail.com", "Gerente", 3800.0);

        // Compras (2 exemplos) – composição com ItemCompra e associação com Cliente
        Compra comp1 = new Compra(new Date(), c1);
        comp1.adicionarItem(v1, 2); // ItemCompra 1
        comp1.adicionarItem(v2, 1); // ItemCompra 2
        c1.adicionarCompra(comp1);

        Compra comp2 = new Compra(new Date(), c2);
        comp2.adicionarItem(v2, 2); // ItemCompra 3
        c2.adicionarCompra(comp2);

        System.out.println("=== VINIS ===");
        System.out.println(v1);
        System.out.println(v2);

        System.out.println("\n=== ESTOQUE (qtd disponível) ===");
        System.out.println(v1.getTitulo() + ": " + estoque.quantidadeDisponivel(v1));
        System.out.println(v2.getTitulo() + ": " + estoque.quantidadeDisponivel(v2));

        // Mostra herança funcionando
        System.out.println("\n=== PESSOAS ===");
        c1.exibirInfo();
        c2.exibirInfo();
        f1.exibirInfo();
        f2.exibirInfo();

        // Mostra composição (itens) e totais das compras
        System.out.println("\n=== COMPRAS ===");
        System.out.println(comp1);
        System.out.println("Total 1: R$ " + comp1.getValorTotal());
        System.out.println(comp2);
        System.out.println("Total 2: R$ " + comp2.getValorTotal());
    }
}