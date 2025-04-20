package controller;

import dao.ArticleDAO;
import dao.CommandeDAO;
import model.Article;
import util.Session;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class HomeController {
    private final ArticleDAO articleDAO;
    private final CommandeDAO commandeDAO;

    public HomeController(Connection connection) {
        this.articleDAO = new ArticleDAO(connection);
        this.commandeDAO = new CommandeDAO(connection);
    }

    public List<Article> getAllArticles() {
        return articleDAO.getAllArticles();
    }

    public List<Article> searchArticlesByMarque(String marque) {
        return articleDAO.getArticlesByMarque(marque);
    }

    public int getCommandesCountLast3Months(int userId) {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        return commandeDAO.getNombreCommandesDepuis(userId, threeMonthsAgo);
    }
}
