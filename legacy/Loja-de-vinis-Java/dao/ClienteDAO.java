package dao;

import Cliente;
import util.ConexaoMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) para operações com a entidade Cliente no banco de dados.
 * Responsável por inserir, buscar, atualizar e excluir clientes.
 */
public class ClienteDAO {
    
    /**
     * Insere um novo cliente no banco de dados.
     * Insere primeiro na tabela pessoas e depois na tabela clientes.
     * @param cliente O objeto Cliente a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void inserir(Cliente cliente) throws SQLException {
        String sqlPessoa = "INSERT INTO pessoas (cpf, nome, email, tipo) VALUES (?, ?, ?, 'CLIENTE')";
        String sqlCliente = "INSERT INTO clientes (cpf, tipo_cliente) VALUES (?, ?)";
        
        Connection conn = null;
        try {
            conn = util.ConexaoMySQL.getConexao();
            conn.setAutoCommit(false); // Inicia transação
            
            // Insere na tabela pessoas
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            stmtPessoa.setString(1, cliente.getCpf());
            stmtPessoa.setString(2, cliente.getNome());
            stmtPessoa.setString(3, cliente.getEmail());
            stmtPessoa.executeUpdate();
            
            // Insere na tabela clientes
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);
            stmtCliente.setString(1, cliente.getCpf());
            stmtCliente.setString(2, cliente.getTipoCliente());
            stmtCliente.executeUpdate();
            
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
     * Busca um cliente pelo CPF.
     * @param cpf CPF do cliente a ser buscado
     * @return Cliente encontrado ou null se não existir
     * @throws SQLException se houver erro na operação
     */
    public Cliente buscarPorCPF(String cpf) throws SQLException {
        String sql = "SELECT p.*, c.tipo_cliente FROM pessoas p " +
                     "INNER JOIN clientes c ON p.cpf = c.cpf " +
                     "WHERE p.cpf = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("tipo_cliente")
                );
            }
            return null;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca clientes por nome (filtro com busca parcial).
     * @param nome Nome ou parte do nome para busca
     * @return Lista de clientes encontrados
     * @throws SQLException se houver erro na operação
     */
    public List<Cliente> buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT p.*, c.tipo_cliente FROM pessoas p " +
                     "INNER JOIN clientes c ON p.cpf = c.cpf " +
                     "WHERE p.nome LIKE ?";
        
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%"); // Busca parcial
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("tipo_cliente")
                ));
            }
            return clientes;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Lista todos os clientes cadastrados no banco de dados.
     * @return Lista com todos os clientes
     * @throws SQLException se houver erro na operação
     */
    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT p.*, c.tipo_cliente FROM pessoas p " +
                     "INNER JOIN clientes c ON p.cpf = c.cpf";
        
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getString("tipo_cliente")
                ));
            }
            return clientes;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Atualiza os dados de um cliente existente.
     * @param cliente Cliente com os dados atualizados
     * @throws SQLException se houver erro na operação
     */
    public void atualizar(Cliente cliente) throws SQLException {
        String sqlPessoa = "UPDATE pessoas SET nome = ?, email = ? WHERE cpf = ?";
        String sqlCliente = "UPDATE clientes SET tipo_cliente = ? WHERE cpf = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            conn.setAutoCommit(false);
            
            PreparedStatement stmtPessoa = conn.prepareStatement(sqlPessoa);
            stmtPessoa.setString(1, cliente.getNome());
            stmtPessoa.setString(2, cliente.getEmail());
            stmtPessoa.setString(3, cliente.getCpf());
            stmtPessoa.executeUpdate();
            
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);
            stmtCliente.setString(1, cliente.getTipoCliente());
            stmtCliente.setString(2, cliente.getCpf());
            stmtCliente.executeUpdate();
            
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
     * Exclui um cliente do banco de dados.
     * Primeiro exclui da tabela clientes, depois da tabela pessoas.
     * @param cpf CPF do cliente a ser excluído
     * @throws SQLException se houver erro na operação
     */
    public void excluir(String cpf) throws SQLException {
        String sqlCliente = "DELETE FROM clientes WHERE cpf = ?";
        String sqlPessoa = "DELETE FROM pessoas WHERE cpf = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            conn.setAutoCommit(false);
            
            PreparedStatement stmtCliente = conn.prepareStatement(sqlCliente);
            stmtCliente.setString(1, cpf);
            stmtCliente.executeUpdate();
            
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

