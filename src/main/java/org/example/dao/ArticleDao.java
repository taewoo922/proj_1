package org.example.dao;

import org.example.DTO.Article;
import org.example.container.Container;
import org.example.db.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArticleDao extends Dao{
    private List<Article> articles;
    private DBConnection dbConnection;

    public List<Article> getArticles() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM article"));

        List<Article> articles = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows){
            articles.add(new Article((row)));
        }

        return articles;
    }
    public int getArticleIndexById(int id) {
        int i = 0;
        for (Article article : articles) {
            if (article.id == id) {
                return i;
            }
            i++;
        }
        return -1;
    }


    public Article getArticleById(int id) {
        int index = getArticleIndexById(id);

        if (index != -1) {
            return articles.get(index);
        }
        return null;
    }

    public ArticleDao() {
        articles = new ArrayList<>();
        dbConnection = Container.getDBConnection();
    }

    public void add(Article article) {
        articles.add(article);
        lastId = article.id;
    }

    public List<Article> getForPrintArticles(String searchKeyword) {

        if (searchKeyword != null && searchKeyword.length() != 0) {
            List<Article> forListArticles = new ArrayList<>();

                for (Article article : articles) {                //그리고 반복문을 통해 기존 리스트를 돌고
                    if (article.title.contains(searchKeyword)) {  //키워드를 포함한 타이틀이 있다면
                        forListArticles.add(article);              //그 article을 새로운 리스트에 추가한다.
                    }
                }
            return  forListArticles;
            }
        return articles;
    }

    public void remove(Article foundArticle) {
        articles.remove(foundArticle);
    }
}
