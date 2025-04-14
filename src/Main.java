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


    }
}
