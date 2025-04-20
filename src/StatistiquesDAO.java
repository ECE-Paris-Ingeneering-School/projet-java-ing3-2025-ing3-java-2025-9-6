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
}
