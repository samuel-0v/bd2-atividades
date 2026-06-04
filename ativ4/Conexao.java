import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:postgresql://localhost:5432/bd2";
        String user = "samuel";
        String password = "senha123";

        Connection conn = DriverManager.getConnection(url, user, password);

        conn.createStatement().execute(
            "SET search_path TO ativ4"
        );

        return conn;
    }
}
