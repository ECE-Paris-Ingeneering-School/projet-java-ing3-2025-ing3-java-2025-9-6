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
}
