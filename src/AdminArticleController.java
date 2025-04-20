package controller;

import dao.ArticleDAO;
import dao.IArticleDAO;
import model.Article;
import dao.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class AdminArticleController {
    private final IArticleDAO articleDAO;

    public AdminArticleController() {
        Connection conn = DatabaseConnection.getStaticConnection();
        this.articleDAO = new ArticleDAO(conn);
    }

    public List<Article> getAllArticles() {
        return articleDAO.getAllArticles();
    }

    public void addArticle(Article article) {
        articleDAO.addArticle(article);
    }

    public void updateArticle(Article article) {
        articleDAO.updateArticle(article);
    }

    public void deleteArticle(int id) {
        articleDAO.deleteArticle(id);
    }

    public Article getArticleById(int id) {
        return articleDAO.getArticleById(id);
    }
}
