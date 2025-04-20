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
}
