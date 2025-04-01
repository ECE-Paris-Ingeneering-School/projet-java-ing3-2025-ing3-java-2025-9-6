import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/shopping_db"; // Nom de ta base
    private static final String USER = "root"; // Ton utilisateur MySQL
    private static final String PASSWORD = "root"; // Ton mot de passe MySQL

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion réussie à la base de données !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion : " + e.getMessage());
        }
        return conn;
    }
}