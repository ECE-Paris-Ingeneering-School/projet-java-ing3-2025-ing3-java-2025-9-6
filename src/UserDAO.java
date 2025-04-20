package dao;
import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAO implements UserDAOInterface {
    private final Connection connection;

    // Modification du constructeur pour accepter une Connection directement
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // 🔍 Récupérer tous les utilisateurs (sous forme d’objets)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        "ancien".equalsIgnoreCase(rs.getString("type_client")),
                        "admin".equalsIgnoreCase(rs.getString("role"))
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
