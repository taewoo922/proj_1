package org.example.service;

import org.example.DTO.Board;
import org.example.container.Container;
import org.example.DTO.Article;
import org.example.container.Container;
import org.example.dao.ArticleDao;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private ArticleDao articleDao;
    public ArticleService() {
        articleDao = Container.articleDao;
    }

    public List<Article> getForPrintArticles(String searchKeyword) {
        return articleDao.getForPrintArticles(searchKeyword);
    }

    public List<Article> getForPrintArticles() {
        return articleDao.getForPrintArticles(null);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);
    }

    public void write(Article article) {
        articleDao.add(article);
    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public int write(int memberId, int boardId, String title, String body) {
        Article article = new Article(memberId, boardId, title, body);
        return articleDao.write(article);
    }

    public Board getBoard(int id) {
        return articleDao.getBoard(id);
    }

    public Article getForPrintArticle(int id) {
        return articleDao.getForPrintArticle(id);
    }
}
