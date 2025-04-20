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

    // 🔐 Connexion (login)
    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM User WHERE email = ? AND password = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password); // ⚠️ Plaintext pour l'instant, à sécuriser !

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        "ancien".equalsIgnoreCase(rs.getString("type_client")),
                        "administrateur".equalsIgnoreCase(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // échec de connexion
    }

    // 📝 Inscription
    public boolean register(User user, String password) {
        String sql = "INSERT INTO User (nom, email, password, type_client, role) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, password); // ⚠️ à chiffrer en prod !
            stmt.setString(4, user.isAncienClient() ? "ancien" : "nouveau");
            stmt.setString(5, "client");

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
