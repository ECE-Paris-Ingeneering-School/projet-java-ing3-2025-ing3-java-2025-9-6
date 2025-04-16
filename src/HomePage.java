import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends JFrame {
    private JPanel resultsPanel;
    private JTextField searchField;
    private List<PanierItem> panier;
    private User currentUser;

    public HomePage(User user) {
        this.currentUser = user;
        panier = new ArrayList<>();  // Initialiser le panier
        setTitle("Page d'Accueil");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());

        // Barre de recherche
        searchField = new JTextField(10);
        JButton searchButton = new JButton("Rechercher par marque");

        // Affichage du panier
        JButton panierButton = new JButton("Voir le Panier");
        panierButton.addActionListener(e -> openPanierPage());

        // Ajout des composants
        topPanel.add(new JLabel("🔍 Marque :"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        topPanel.add(panierButton);

        // Bouton Accueil
        JButton accueilButton = new JButton("Accueil");
        accueilButton.addActionListener(e -> {
            searchField.setText(""); // Réinitialiser le champ de recherche
            resultsPanel.removeAll(); // Vider les résultats actuels
            displayInfo(resultsPanel); // Réafficher les infos par défaut
            resultsPanel.revalidate();
            resultsPanel.repaint();
        });
        topPanel.add(accueilButton);

        add(topPanel, BorderLayout.NORTH);

        // Panneau inférieur (bouton admin)
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Panneau droit pour bouton "Gestion Articles"
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        if (currentUser != null && currentUser.isAdmin()) {
            JButton adminButton = new JButton("Gestion Articles (Admin)");
            adminButton.addActionListener(e -> new AdminArticleManager());

            JButton clientsButton = new JButton("Gérer les clients");
            clientsButton.addActionListener(e -> new AdminClientManager());
            rightPanel.add(clientsButton);
            rightPanel.add(adminButton);
        }

        add(bottomPanel, BorderLayout.SOUTH);

        // Panneau central d'infos
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new BoxLayout(resultsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(resultsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Panneau gauche pour bouton "Quitter"
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton quitButton = new JButton("Quitter");
        quitButton.addActionListener(e -> System.exit(0));
        leftPanel.add(quitButton);

        // Ajout des deux sous-panneaux au panneau du bas
        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(rightPanel, BorderLayout.EAST);

        // Ajout du panneau du bas à la fenêtre principale
        add(bottomPanel, BorderLayout.SOUTH);

        // Afficher les infos par défaut
        displayInfo(resultsPanel);

        // Action de recherche
        searchButton.addActionListener(e -> {
            String marque = searchField.getText().trim();
            if (!marque.isEmpty()) {
                searchArticlesByMarque(marque);
            } else {
                resultsPanel.removeAll();
                displayInfo(resultsPanel);
                resultsPanel.revalidate();
                resultsPanel.repaint();
            }
        });

        setVisible(true);
    }

    private void displayInfo(JPanel panel) {
        panel.removeAll(); // Nettoyer l'affichage précédent

        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            try {
                // Utilisateurs
                String sqlUsers = "SELECT * FROM User";
                try (PreparedStatement stmt = conn.prepareStatement(sqlUsers);
                     ResultSet rs = stmt.executeQuery()) {
                    panel.add(new JLabel("👤 Utilisateurs :"));
                    while (rs.next()) {
                        String userInfo = rs.getInt("id") + " - " + rs.getString("nom") + " (" + rs.getString("email") + ")";
                        panel.add(new JLabel(userInfo));
                    }
                }

                // Articles
                String sqlArticles = "SELECT * FROM Article";
                try (PreparedStatement stmt = conn.prepareStatement(sqlArticles);
                     ResultSet rs = stmt.executeQuery()) {

                    panel.add(new JLabel("\n🛒 Liste des articles :"));
                    while (rs.next()) {
                        try {
                            int articleId = rs.getInt("id");
                            String articleNom = rs.getString("nom");
                            String articleMarque = rs.getString("marque");
                            double articlePrix = rs.getDouble("prix_unitaire");

                            panel.add(new JLabel(articleId + " - " + articleNom + " (" + articleMarque + "), Prix: " + articlePrix + "€"));
                            JButton addButton = new JButton("Ajouter au panier");
                            addButton.addActionListener(e -> addToPanier(articleId, articleNom, articlePrix));
                            panel.add(addButton);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            panel.add(new JLabel("❌ Erreur : " + e.getMessage()));
                        }
                    }
                }

                // Nombre de commandes (client id = 1)
                panel.add(new JLabel("\n📦 Nombre de commandes des 3 derniers mois du client 1 :"));
                String sqlOrders = "SELECT COUNT(*) AS nbCommandes FROM Commande WHERE client_id = ? AND date_commande >= ?";
                try (PreparedStatement stmt = conn.prepareStatement(sqlOrders)) {
                    stmt.setInt(1, 1); // Client ID
                    stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now().minusMonths(3)));
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            int nb = rs.getInt("nbCommandes");
                            panel.add(new JLabel(nb + " commande(s)."));
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                panel.add(new JLabel("❌ Erreur : " + e.getMessage()));
            }
        }
    }

    private void searchArticlesByMarque(String marque) {
        resultsPanel.removeAll();

        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            try {
                String sql = "SELECT * FROM Article WHERE marque LIKE ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, "%" + marque + "%");
                    try (ResultSet rs = stmt.executeQuery()) {
                        resultsPanel.add(new JLabel("🔎 Résultats pour la marque : " + marque));
                        boolean found = false;
                        while (rs.next()) {
                            try {
                                found = true;
                                int articleId = rs.getInt("id");
                                String articleNom = rs.getString("nom");
                                String articleMarque = rs.getString("marque");
                                double articlePrix = rs.getDouble("prix_unitaire");

                                String articleInfo = articleId + " - " + articleNom +
                                        " (" + articleMarque + "), Prix: " +
                                        articlePrix + "€";
                                JButton addButton = new JButton("Ajouter au panier");
                                addButton.addActionListener(e -> addToPanier(articleId, articleNom, articlePrix));

                                resultsPanel.add(new JLabel(articleInfo));
                                resultsPanel.add(addButton);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                resultsPanel.add(new JLabel("❌ Erreur lors de la récupération des données de l'article : " + e.getMessage()));
                            }
                        }
                        if (!found) {
                            resultsPanel.add(new JLabel("Aucun article trouvé pour cette marque."));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                resultsPanel.add(new JLabel("❌ Erreur : " + e.getMessage()));
            }

            resultsPanel.revalidate();
            resultsPanel.repaint();
        }
    }

    private void addToPanier(int articleId, String nom, double prixUnitaire) {
        // Vérifier si l'article est déjà dans le panier
        for (PanierItem item : panier) {
            if (item.getArticleId() == articleId) {
                item.incrementQuantite();
                updatePanier();
                return;
            }
        }
        // Récupérer les infos vrac depuis la base de données
        Connection conn = DatabaseConnection.getConnection();
        if (conn != null) {
            try {
                String sql = "SELECT prix_vrac, quantite_vrac FROM Article WHERE id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, articleId);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            double prixVrac = rs.getDouble("prix_vrac");
                            int quantiteVrac = rs.getInt("quantite_vrac");
                            panier.add(new PanierItem(articleId, nom, prixUnitaire, prixVrac, quantiteVrac, 1));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        updatePanier();
    }

    private void updatePanier() {
        // Rafraîchir l'affichage du panier
        System.out.println("Panier mis à jour!");
        openPanierPage();
    }

    private void openPanierPage() {
        // Ouvrir la page du panier
        new PanierPage(panier);
    }
}