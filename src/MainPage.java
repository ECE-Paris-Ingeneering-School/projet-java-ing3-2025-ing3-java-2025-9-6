import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class MainPage {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Connexion à la base de données
                Connection connection = DatabaseConnection.getConnection();

                if (connection != null) {
                    UserDAO userDAO = new UserDAO(connection);
                    ArticleDAO articleDAO = new ArticleDAO(connection);
                    CommandeDAO commandeDAO = new CommandeDAO(connection);

                    // Affichage dans la console
                    System.out.println("✅ Connexion à la base de données réussie !");

                    System.out.println("👤 Liste des utilisateurs :");
                    userDAO.getAllUsers().forEach(System.out::println);

                    System.out.println("\n🛒 Liste des articles :");
                    articleDAO.getAllArticles().forEach(System.out::println);

                    int clientId = 1;
                    int nbCommandes = commandeDAO.getNombreCommandesDerniers3Mois(clientId);
                    System.out.println("\n📦 Nombre de commandes des 3 derniers mois du client " + clientId + " : " + nbCommandes);

                    // Lancer la fenêtre principale
                    new WelcomeWindow();
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

class WelcomeWindow extends JFrame {
    private JButton loginButton, registerButton;

    public WelcomeWindow() {
        setTitle("Page d'accueil");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        loginButton = new JButton("Se connecter");
        registerButton = new JButton("S'inscrire");

        add(loginButton);
        add(registerButton);

        // Action pour le bouton de connexion
        loginButton.addActionListener(e -> openLoginPage());

        // Action pour le bouton d'inscription
        registerButton.addActionListener(e -> openRegistrationPage());

        setVisible(true);
    }

    private void openLoginPage() {
        new LoginWindow(); // Ouvre la page de connexion
        this.dispose(); // Ferme la page d'accueil
    }

    private void openRegistrationPage() {
        new RegistrationWindow(); // Ouvre la page d'inscription
        this.dispose(); // Ferme la page d'accueil
    }
}