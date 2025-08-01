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

    // 🔎 Récupérer un utilisateur par ID
    public User getUserById(int id) {
        String sql = "SELECT * FROM User WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        "ancien".equalsIgnoreCase(rs.getString("type_client")),
                        "admin".equalsIgnoreCase(rs.getString("role"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Integer> getAllClientIds() {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id FROM User WHERE role = 'client'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ids;
    }

    @Override
    public User getClientById(int id) {
        String sql = "SELECT * FROM User WHERE id = ? AND role = 'client'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        "ancien".equalsIgnoreCase(rs.getString("type_client")),
                        false // on sait que c'est un client ici
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateClient(User client) {
        String sql = "UPDATE User SET nom = ?, email = ?, type_client = ? WHERE id = ? AND role = 'client'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getEmail());
            stmt.setString(3, client.isAncienClient() ? "ancien" : "nouveau");
            stmt.setInt(4, client.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(int id) {
        String sql = "DELETE FROM User WHERE id = ? AND role = 'client'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
