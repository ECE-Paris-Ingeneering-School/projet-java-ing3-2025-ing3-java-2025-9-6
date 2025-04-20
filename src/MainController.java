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
                // Créer une instance de DatabaseConnection pour obtenir la connexion
                DatabaseConnection dbConnection = new DatabaseConnection();
                Connection connection = dbConnection.getConnection();

                if (connection != null) {
                    System.out.println("✅ Connexion à la base de données réussie !");

                    UserDAO userDAO = new UserDAO(connection);
                    ArticleDAO articleDAO = new ArticleDAO(connection);
                    CommandeDAO commandeDAO = new CommandeDAO(connection);

                    System.out.println("👤 Liste des utilisateurs :");
                    userDAO.getAllUsers().forEach(System.out::println);

                    System.out.println("\n🛒 Liste des articles :");
                    articleDAO.getAllArticles().forEach(System.out::println);

                    int clientId = 1;
                    int nbCommandes = commandeDAO.getNombreCommandesDerniers3Mois(clientId);
                    System.out.println("\n📦 Nombre de commandes (3 derniers mois) du client " + clientId + " : " + nbCommandes);

                    new WelcomeWindow(); // Vue
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur est survenue : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
