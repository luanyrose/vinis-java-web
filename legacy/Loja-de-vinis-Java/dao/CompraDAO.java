package dao;

import Compra;
import Cliente;
import Vinil;
import ItemCompra;
import util.ConexaoMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe DAO (Data Access Object) para operações com a entidade Compra no banco de dados.
 * Responsável por inserir, buscar, atualizar e excluir compras e seus itens.
 */
public class CompraDAO {
    
    private ClienteDAO clienteDAO;
    private VinilDAO vinilDAO;
    
    /**
     * Construtor da classe CompraDAO.
     * Inicializa os DAOs necessários para buscar clientes e vinis.
     */
    public CompraDAO() {
        this.clienteDAO = new ClienteDAO();
        this.vinilDAO = new VinilDAO();
    }
    
    /**
     * Insere uma nova compra no banco de dados.
     * Insere a compra e todos os seus itens em uma transação.
     * @param compra O objeto Compra a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void inserir(Compra compra) throws SQLException {
        String sqlCompra = "INSERT INTO compras (data_compra, cliente_cpf, valor_total) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO itens_compra (compra_id, vinil_codigo, quantidade, valor_item) VALUES (?, ?, ?, ?)";
        
        Connection conn = null;
        try {
            conn = util.ConexaoMySQL.getConexao();
            conn.setAutoCommit(false); // Inicia transação
            
            // Insere a compra
            PreparedStatement stmtCompra = conn.prepareStatement(sqlCompra, Statement.RETURN_GENERATED_KEYS);
            stmtCompra.setDate(1, new java.sql.Date(compra.getDataCompra().getTime()));
            stmtCompra.setString(2, compra.getCliente().getCpf());
            stmtCompra.setDouble(3, compra.getValorTotal());
            stmtCompra.executeUpdate();
            
            // Obtém o ID da compra inserida
            ResultSet rs = stmtCompra.getGeneratedKeys();
            int compraId = 0;
            if (rs.next()) {
                compraId = rs.getInt(1);
            }
            
            // Insere os itens da compra
            for (ItemCompra item : compra.getItens()) {
                PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                stmtItem.setInt(1, compraId);
                stmtItem.setInt(2, item.getVinil().getCodigo());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setDouble(4, item.getValorItem());
                stmtItem.executeUpdate();
                
                // Atualiza a quantidade disponível do vinil
                Vinil vinil = item.getVinil();
                int novaQuantidade = vinil.getQtdDisponivel() - item.getQuantidade();
                vinilDAO.atualizarQuantidade(vinil.getCodigo(), novaQuantidade);
            }
            
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
     * Busca uma compra pelo ID.
     * @param id ID da compra a ser buscada
     * @return Compra encontrada ou null se não existir
     * @throws SQLException se houver erro na operação
     */
    public Compra buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM compras WHERE id = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Busca o cliente
                Cliente cliente = clienteDAO.buscarPorCPF(rs.getString("cliente_cpf"));
                
                // Cria a compra
                Compra compra = new Compra(
                    new Date(rs.getDate("data_compra").getTime()),
                    cliente
                );
                
                // Busca os itens da compra
                List<ItemCompra> itens = buscarItensPorCompraId(id);
                for (ItemCompra item : itens) {
                    compra.adicionarItem(item.getVinil(), item.getQuantidade());
                }
                
                return compra;
            }
            return null;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca compras por CPF do cliente (filtro).
     * @param cpfCliente CPF do cliente
     * @return Lista de compras encontradas
     * @throws SQLException se houver erro na operação
     */
    public List<Compra> buscarPorCliente(String cpfCliente) throws SQLException {
        String sql = "SELECT * FROM compras WHERE cliente_cpf = ? ORDER BY data_compra DESC";
        
        List<Compra> compras = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpfCliente);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorCPF(rs.getString("cliente_cpf"));
                Compra compra = new Compra(
                    new Date(rs.getDate("data_compra").getTime()),
                    cliente
                );
                
                // Busca os itens da compra
                List<ItemCompra> itens = buscarItensPorCompraId(rs.getInt("id"));
                for (ItemCompra item : itens) {
                    compra.adicionarItem(item.getVinil(), item.getQuantidade());
                }
                
                compras.add(compra);
            }
            return compras;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca compras por intervalo de datas (filtro).
     * @param dataInicio Data inicial do intervalo
     * @param dataFim Data final do intervalo
     * @return Lista de compras encontradas
     * @throws SQLException se houver erro na operação
     */
    public List<Compra> buscarPorData(Date dataInicio, Date dataFim) throws SQLException {
        String sql = "SELECT * FROM compras WHERE data_compra BETWEEN ? AND ? ORDER BY data_compra DESC";
        
        List<Compra> compras = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(dataInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(dataFim.getTime()));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorCPF(rs.getString("cliente_cpf"));
                Compra compra = new Compra(
                    new Date(rs.getDate("data_compra").getTime()),
                    cliente
                );
                
                List<ItemCompra> itens = buscarItensPorCompraId(rs.getInt("id"));
                for (ItemCompra item : itens) {
                    compra.adicionarItem(item.getVinil(), item.getQuantidade());
                }
                
                compras.add(compra);
            }
            return compras;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Lista todas as compras cadastradas no banco de dados.
     * @return Lista com todas as compras
     * @throws SQLException se houver erro na operação
     */
    public List<Compra> listarTodas() throws SQLException {
        String sql = "SELECT * FROM compras ORDER BY data_compra DESC";
        
        List<Compra> compras = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Cliente cliente = clienteDAO.buscarPorCPF(rs.getString("cliente_cpf"));
                Compra compra = new Compra(
                    new Date(rs.getDate("data_compra").getTime()),
                    cliente
                );
                
                List<ItemCompra> itens = buscarItensPorCompraId(rs.getInt("id"));
                for (ItemCompra item : itens) {
                    compra.adicionarItem(item.getVinil(), item.getQuantidade());
                }
                
                compras.add(compra);
            }
            return compras;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca os itens de uma compra específica.
     * Método auxiliar privado.
     * @param compraId ID da compra
     * @return Lista de itens da compra
     * @throws SQLException se houver erro na operação
     */
    private List<ItemCompra> buscarItensPorCompraId(int compraId) throws SQLException {
        String sql = "SELECT * FROM itens_compra WHERE compra_id = ?";
        
        List<ItemCompra> itens = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, compraId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Vinil vinil = vinilDAO.buscarPorCodigo(rs.getInt("vinil_codigo"));
                if (vinil != null) {
                    ItemCompra item = new ItemCompra(vinil, rs.getInt("quantidade"));
                    itens.add(item);
                }
            }
            return itens;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Exclui uma compra do banco de dados.
     * Primeiro exclui os itens, depois a compra.
     * @param id ID da compra a ser excluída
     * @throws SQLException se houver erro na operação
     */
    public void excluir(int id) throws SQLException {
        String sqlItem = "DELETE FROM itens_compra WHERE compra_id = ?";
        String sqlCompra = "DELETE FROM compras WHERE id = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            conn.setAutoCommit(false);
            
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            stmtItem.setInt(1, id);
            stmtItem.executeUpdate();
            
            PreparedStatement stmtCompra = conn.prepareStatement(sqlCompra);
            stmtCompra.setInt(1, id);
            stmtCompra.executeUpdate();
            
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

