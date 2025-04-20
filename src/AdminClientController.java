package controller;

import dao.UserDAO;
import dao.UserDAOInterface;
import model.User;
import dao.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class AdminClientController {
    private final UserDAOInterface userDAO;

    public AdminClientController() {
        Connection conn = DatabaseConnection.getStaticConnection();
        this.userDAO = new UserDAO(conn);
    }

    public List<Integer> getAllClientIds() {
        return userDAO.getAllClientIds();
    }

    public User getClientById(int id) {
        return userDAO.getClientById(id);
    }

    public void updateClient(User user) {
        userDAO.updateClient(user);
    }

    public void deleteClient(int id) {
        userDAO.deleteClient(id);
    }
}
