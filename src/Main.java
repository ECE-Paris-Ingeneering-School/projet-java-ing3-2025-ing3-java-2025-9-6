public class Main {
    public static void main(String[] args) {
        try {
            // Connexion à la base de données
            Connection connection = DatabaseConnection.getConnection();

            if (connection != null) {
                UserDAO userDAO = new UserDAO(connection);
                ArticleDAO articleDAO = new ArticleDAO(connection);
                CommandeDAO commandeDAO = new CommandeDAO(connection);

                // Récupérer et afficher les utilisateurs
                System.out.println("👤 Liste des utilisateurs :");
                userDAO.getAllUsers().forEach(System.out::println);

                // Récupérer et afficher les articles
                System.out.println("\n🛒 Liste des articles :");
                articleDAO.getAllArticles().forEach(System.out::println);

                // Vérifier les commandes d'un client (exemple : Alice avec id = 1)
                int clientId = 1;
                int nbCommandes = commandeDAO.getNombreCommandesDerniers3Mois(clientId);
                System.out.println("\n📦 Nombre de commandes des 3 derniers mois du client " + clientId + " : " + nbCommandes);
            }
            } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
