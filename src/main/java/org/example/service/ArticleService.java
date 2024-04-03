package org.example.service;

import org.example.DTO.ArticleReply;
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

    public List<Article> getForPrintArticles(String boardCode, String searchKeyword) {
        return articleDao.getForPrintArticles(boardCode, searchKeyword);
    }


    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public Article getArticle(int id) {
        return articleDao.getArticle(id);
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

    public void modify(int id, String title, String body) {
        articleDao.modify(id, title, body);
    }


    public void delete(int id) {
        articleDao.delete(id);
    }

    //댓글~~~~~~~~~~~~~~~~~~~~~~~~~~

    public int replyWrite(int articleId, int memberId, String replyBody) {
        return articleDao.replyWrite(articleId, memberId, replyBody);
    }

    public List<ArticleReply> getForPrintArticleReplies(int articleId) {
        return articleDao.getForPrintArticleReplies(articleId);
    }
}
