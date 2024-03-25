package org.example.service;

import org.example.Container;
import org.example.DTO.Article;
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

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);
    }
}