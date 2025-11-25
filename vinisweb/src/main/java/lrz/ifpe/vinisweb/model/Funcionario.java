public class Funcionario extends Pessoa {
    // Cargo e salário do funcionário da loja
    private String cargo;
    private double salario;

    public Funcionario(String nome, String cpf, String email, String cargo, double salario) {
        super(nome, cpf, email);
        this.cargo = cargo;
        this.salario = salario;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Funcionário: " + nome + " | Cargo: " + cargo + " | Salário: R$" + salario);
    }
}
