package dao;

import dao.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatistiquesDAO implements IStatistiquesDAO {
    private final Connection conn;

    public StatistiquesDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public double getTotalVentes() {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT SUM(prix_total) AS total_ventes FROM Commande_Article");
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("total_ventes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @Override
    public List<String> getTopArticles() {
        List<String> top = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT a.nom, SUM(ca.quantite) AS quantite_totale " +
                        "FROM Article a " +
                        "JOIN Commande_Article ca ON a.id = ca.article_id " +
                        "GROUP BY a.nom " +
                        "ORDER BY quantite_totale DESC " +
                        "LIMIT 5");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                top.add(rs.getString("nom") + " : " + rs.getInt("quantite_totale") + " unités");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return top;
    }
}
