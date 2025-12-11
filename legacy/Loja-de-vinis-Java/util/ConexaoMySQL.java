package util; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a conexão com o banco de dados MySQL.
 * Fornece métodos estáticos para obter e fechar conexões.
 */
public class ConexaoMySQL {
    // Configurações de conexão
    private static final String URL = "jdbc:mysql://localhost:3306/loja_vinis";
    private static final String USUARIO = "raquel"; 
    private static final String SENHA = "79127912"; 
    
    /**
     * Estabelece conexão com o banco de dados MySQL
     * @return Connection objeto de conexão
     * @throws SQLException se houver erro na conexão
     */
    public static Connection getConexao() throws SQLException {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Retorna a conexão
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL não encontrado", e);
        }
    }
    
    /**
     * Fecha a conexão com o banco de dados
     * @param conexao Conexão a ser fechada
     */
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}