package controller;

import dao.IStatistiquesDAO;
import dao.StatistiquesDAO;
import dao.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class AdminStatsController {
    private final IStatistiquesDAO statsDAO;

    public AdminStatsController() {
        Connection conn = DatabaseConnection.getStaticConnection();
        this.statsDAO = new StatistiquesDAO(conn);
    }

    public double getTotalVentes() {
        return statsDAO.getTotalVentes();
    }

    public List<String> getTopArticles() {
        return statsDAO.getTopArticles();
    }

    public List<String> getReductionsAppliquees() {
        return statsDAO.getReductionsAppliquees();
    }
}
