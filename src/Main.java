public class Main {
    public static void main(String[] args) {
        try {
            // Connexion à la base de données
            Connection connection = DatabaseConnection.getConnection();

            if (connection != null) {
                UserDAO userDAO = new UserDAO(connection);
                ArticleDAO articleDAO = new ArticleDAO(connection);
                CommandeDAO commandeDAO = new CommandeDAO(connection);


    }
}
