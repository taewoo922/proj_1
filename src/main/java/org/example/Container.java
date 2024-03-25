package org.example;

import org.example.DTO.Article;
import org.example.DTO.Member;
import org.example.dao.ArticleDao;
import org.example.dao.MemberDao;

public class Container {
    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();
    }
}
