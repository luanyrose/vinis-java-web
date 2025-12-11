/**
 * Classe que representa um funcionário da loja.
 * Herda da classe Pessoa, adicionando informações específicas de funcionário
 * como cargo e salário.
 */
public class Funcionario extends Pessoa {
    // Cargo/função do funcionário na loja (ex.: Atendente, Gerente, Vendedor)
    private String cargo;
    
    // Salário do funcionário
    private double salario;

    /**
     * Construtor da classe Funcionario.
     * @param nome Nome completo do funcionário
     * @param cpf CPF do funcionário
     * @param email Email de contato do funcionário
     * @param cargo Cargo/função do funcionário na loja
     * @param salario Salário do funcionário
     */
    public Funcionario(String nome, String cpf, String email, String cargo, double salario) {
        super(nome, cpf, email); // Chama o construtor da classe pai (Pessoa)
        this.cargo = cargo;
        this.salario = salario;
    }

    /**
     * Exibe as informações do funcionário no console.
     * Sobrescreve o método abstrato da classe pai (Pessoa).
     */
    @Override
    public void exibirInfo() {
        System.out.println("Funcionário: " + nome + " | Cargo: " + cargo + " | Salário: R$" + salario);
    }
    
    /**
     * Retorna o cargo do funcionário.
     * @return O cargo/função do funcionário
     */
    public String getCargo() {
        return cargo;
    }
    
    /**
     * Retorna o salário do funcionário.
     * @return O salário do funcionário
     */
    public double getSalario() {
        return salario;
    }
}
