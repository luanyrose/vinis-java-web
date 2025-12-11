package dao;

import Funcionario;
import util.ConexaoMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) para operações com a entidade Funcionario no banco de dados.
 * Responsável por inserir, buscar, atualizar e excluir funcionários.
 */
public class FuncionarioDAO {
    
    /**
     * Insere um novo funcionário no banco de dados.
     * Insere primeiro na tabela pessoas e depois na tabela funcionarios.
     * @param funcionario O objeto Funcionario a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void inserir(Funcionario funcionario) throws SQLException {
        String sqlPessoa = "INSERT INTO pessoas (cpf, nome, email, tipo) VALUES (?, ?, ?, 'FUNCIONARIO')";
        String sqlFuncionario = "INSERT INTO funcionarios (cpf, cargo, salario) VALUES (?, ?, ?)";
        
        Connection conn = null;
        try {
            conn = util.ConexaoMySQL.getConexao();
            conn.setAutoCommit(false); // Inicia transação
            
            // Insere na tabela pessoas
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            stmtPessoa.setString(1, funcionario.getCpf());
            stmtPessoa.setString(2, funcionario.getNome());
            stmtPessoa.setString(3, funcionario.getEmail());
            stmtPessoa.executeUpdate();
            
            // Insere na tabela funcionarios
            PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario);
            stmtFuncionario.setString(1, funcionario.getCpf());
            stmtFuncionario.setString(2, funcionario.getCargo());
            stmtFuncionario.setDouble(3, funcionario.getSalario());
            stmtFuncionario.executeUpdate();
            
            conn.commit(); // Confirma transação
        } catch (SQLException e) {
            if (conn != null) conn.rollback(); // Reverte em caso de erro
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true);
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca um funcionário pelo CPF.
     * @param cpf CPF do funcionário a ser buscado
     * @return Funcionario encontrado ou null se não existir
     * @throws SQLException se houver erro na operação
     */
    public Funcionario buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT p.*, f.cargo, f.salario FROM pessoas p " +
                     "INNER JOIN funcionarios f ON p.cpf = f.cpf " +
                     "WHERE p.cpf = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Funcionario(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("cargo"),
                    rs.getDouble("salario")
                );
            }
            return null;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca funcionários por nome (filtro com busca parcial).
     * @param nome Nome ou parte do nome para busca
     * @return Lista de funcionários encontrados
     * @throws SQLException se houver erro na operação
     */
    public List<Funcionario> buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT p.*, f.cargo, f.salario FROM pessoas p " +
                     "INNER JOIN funcionarios f ON p.cpf = f.cpf " +
                     "WHERE p.nome LIKE ?";
        
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%"); // Busca parcial
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                funcionarios.add(new Funcionario(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("cargo"),
                    rs.getDouble("salario")
                ));
            }
            return funcionarios;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca funcionários por cargo (filtro).
     * @param cargo Cargo para busca
     * @return Lista de funcionários encontrados
     * @throws SQLException se houver erro na operação
     */
    public List<Funcionario> buscarPorCargo(String cargo) throws SQLException {
        String sql = "SELECT p.*, f.cargo, f.salario FROM pessoas p " +
                     "INNER JOIN funcionarios f ON p.cpf = f.cpf " +
                     "WHERE f.cargo LIKE ?";
        
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + cargo + "%"); // Busca parcial
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                funcionarios.add(new Funcionario(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("cargo"),
                    rs.getDouble("salario")
                ));
            }
            return funcionarios;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Lista todos os funcionários cadastrados no banco de dados.
     * @return Lista com todos os funcionários
     * @throws SQLException se houver erro na operação
     */
    public List<Funcionario> listarTodos() throws SQLException {
        String sql = "SELECT p.*, f.cargo, f.salario FROM pessoas p " +
                     "INNER JOIN funcionarios f ON p.cpf = f.cpf";
        
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                funcionarios.add(new Funcionario(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("cargo"),
                    rs.getDouble("salario")
                ));
            }
            return funcionarios;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Atualiza os dados de um funcionário existente.
     * @param funcionario Funcionario com os dados atualizados
     * @throws SQLException se houver erro na operação
     */
    public void atualizar(Funcionario funcionario) throws SQLException {
        String sqlPessoa = "UPDATE pessoas SET nome = ?, email = ? WHERE cpf = ?";
        String sqlFuncionario = "UPDATE funcionarios SET cargo = ?, salario = ? WHERE cpf = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            conn.setAutoCommit(false);
            
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            stmtPessoa.setString(1, funcionario.getNome());
            stmtPessoa.setString(2, funcionario.getEmail());
            stmtPessoa.setString(3, funcionario.getCpf());
            stmtPessoa.executeUpdate();
            
            PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario);
            stmtFuncionario.setString(1, funcionario.getCargo());
            stmtFuncionario.setDouble(2, funcionario.getSalario());
            stmtFuncionario.setString(3, funcionario.getCpf());
            stmtFuncionario.executeUpdate();
            
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true);
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Exclui um funcionário do banco de dados.
     * Primeiro exclui da tabela funcionarios, depois da tabela pessoas.
     * @param cpf CPF do funcionário a ser excluído
     * @throws SQLException se houver erro na operação
     */
    public void excluir(String cpf) throws SQLException {
        String sqlFuncionario = "DELETE FROM funcionarios WHERE cpf = ?";
        String sqlPessoa = "DELETE FROM pessoas WHERE cpf = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            conn.setAutoCommit(false);
            
            PreparedStatement stmtFuncionario = conn.prepareStatement(sqlFuncionario);
            stmtFuncionario.setString(1, cpf);
            stmtFuncionario.executeUpdate();
            
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            stmtPessoa.setString(1, cpf);
            stmtPessoa.executeUpdate();
            
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.setAutoCommit(true);
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
}

