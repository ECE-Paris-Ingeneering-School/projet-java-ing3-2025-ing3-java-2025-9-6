package controller;

import dao.ArticleDAO;
import dao.CommandeDAO;
import dao.DatabaseConnection;
import dao.UserDAO;
import view.WelcomeWindow;

import javax.swing.*;
import java.sql.Connection;

public class MainController {

    public static void launchApp() {
        SwingUtilities.invokeLater(() -> {
            try {
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur est survenue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
