package dao;

import Vinil;
import util.ConexaoMySQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO (Data Access Object) para operações com a entidade Vinil no banco de dados.
 * Responsável por inserir, buscar, atualizar e excluir vinis.
 */
public class VinilDAO {
    
    /**
     * Insere um novo vinil no banco de dados.
     * @param vinil O objeto Vinil a ser inserido
     * @throws SQLException se houver erro na operação
     */
    public void inserir(Vinil vinil) throws SQLException {
        String sql = "INSERT INTO vinis (codigo, titulo, artista, preco_venda, genero, qtd_disponivel) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, vinil.getCodigo());
            stmt.setString(2, vinil.getTitulo());
            stmt.setString(3, vinil.getArtista());
            stmt.setDouble(4, vinil.getPrecoVenda());
            stmt.setString(5, vinil.getGenero());
            stmt.setInt(6, vinil.getQtdDisponivel());
            stmt.executeUpdate();
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca um vinil pelo código.
     * @param codigo Código do vinil a ser buscado
     * @return Vinil encontrado ou null se não existir
     * @throws SQLException se houver erro na operação
     */
    public Vinil buscarPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM vinis WHERE codigo = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Vinil(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getDouble("preco_venda"),
                    rs.getInt("qtd_disponivel"),
                    rs.getString("genero")
                );
            }
            return null;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca vinis por título (filtro com busca parcial).
     * @param titulo Título ou parte do título para busca
     * @return Lista de vinis encontrados
     * @throws SQLException se houver erro na operação
     */
    public List<Vinil> buscarPorTitulo(String titulo) throws SQLException {
        String sql = "SELECT * FROM vinis WHERE titulo LIKE ?";
        
        List<Vinil> vinis = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + titulo + "%"); // Busca parcial
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                vinis.add(new Vinil(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getDouble("preco_venda"),
                    rs.getInt("qtd_disponivel"),
                    rs.getString("genero")
                ));
            }
            return vinis;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca vinis por artista (filtro com busca parcial).
     * @param artista Nome do artista ou banda para busca
     * @return Lista de vinis encontrados
     * @throws SQLException se houver erro na operação
     */
    public List<Vinil> buscarPorArtista(String artista) throws SQLException {
        String sql = "SELECT * FROM vinis WHERE artista LIKE ?";
        
        List<Vinil> vinis = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + artista + "%"); // Busca parcial
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                vinis.add(new Vinil(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getDouble("preco_venda"),
                    rs.getInt("qtd_disponivel"),
                    rs.getString("genero")
                ));
            }
            return vinis;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Busca vinis por gênero (filtro).
     * @param genero Gênero musical para busca
     * @return Lista de vinis encontrados
     * @throws SQLException se houver erro na operação
     */
    public List<Vinil> buscarPorGenero(String genero) throws SQLException {
        String sql = "SELECT * FROM vinis WHERE genero LIKE ?";
        
        List<Vinil> vinis = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + genero + "%"); // Busca parcial
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                vinis.add(new Vinil(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getDouble("preco_venda"),
                    rs.getInt("qtd_disponivel"),
                    rs.getString("genero")
                ));
            }
            return vinis;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Lista todos os vinis cadastrados no banco de dados.
     * @return Lista com todos os vinis
     * @throws SQLException se houver erro na operação
     */
    public List<Vinil> listarTodos() throws SQLException {
        String sql = "SELECT * FROM vinis ORDER BY titulo";
        
        List<Vinil> vinis = new ArrayList<>();
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                vinis.add(new Vinil(
                    rs.getInt("codigo"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getDouble("preco_venda"),
                    rs.getInt("qtd_disponivel"),
                    rs.getString("genero")
                ));
            }
            return vinis;
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Atualiza os dados de um vinil existente.
     * @param vinil Vinil com os dados atualizados
     * @throws SQLException se houver erro na operação
     */
    public void atualizar(Vinil vinil) throws SQLException {
        String sql = "UPDATE vinis SET titulo = ?, artista = ?, preco_venda = ?, " +
                     "genero = ?, qtd_disponivel = ? WHERE codigo = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vinil.getTitulo());
            stmt.setString(2, vinil.getArtista());
            stmt.setDouble(3, vinil.getPrecoVenda());
            stmt.setString(4, vinil.getGenero());
            stmt.setInt(5, vinil.getQtdDisponivel());
            stmt.setInt(6, vinil.getCodigo());
            stmt.executeUpdate();
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Atualiza apenas a quantidade disponível de um vinil.
     * Útil para atualizar estoque após compras.
     * @param codigo Código do vinil
     * @param quantidade Nova quantidade disponível
     * @throws SQLException se houver erro na operação
     */
    public void atualizarQuantidade(int codigo, int quantidade) throws SQLException {
        String sql = "UPDATE vinis SET qtd_disponivel = ? WHERE codigo = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setInt(2, codigo);
            stmt.executeUpdate();
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
    
    /**
     * Exclui um vinil do banco de dados.
     * @param codigo Código do vinil a ser excluído
     * @throws SQLException se houver erro na operação
     */
    public void excluir(int codigo) throws SQLException {
        String sql = "DELETE FROM vinis WHERE codigo = ?";
        
        Connection conn = util.ConexaoMySQL.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } finally {
            util.ConexaoMySQL.fecharConexao(conn);
        }
    }
}

