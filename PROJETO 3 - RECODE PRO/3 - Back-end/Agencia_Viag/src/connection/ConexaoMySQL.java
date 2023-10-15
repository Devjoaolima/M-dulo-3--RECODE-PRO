package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    public static Connection conectar() {
        String url = "jdbc:mysql://localhost:3306/AgenciaViagem";
        String usuario = "seuUsuario";
        String senha = "suaSenha";

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão com o MySQL estabelecida com sucesso.");
            return conexao;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao MySQL: " + e.getMessage());
            return null;
        }
    }

    public static void desconectar(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Conexão com o MySQL encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao desconectar do MySQL: " + e.getMessage());
        }
    }
}
