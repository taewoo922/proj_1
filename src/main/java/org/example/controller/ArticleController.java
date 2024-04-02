package org.example.controller;


import org.example.DTO.Member;
import org.example.container.Container;
import org.example.DTO.Article;
import org.example.service.ArticleService;
import org.example.service.MemberService;
import org.example.util.Util;

import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private Scanner sc;

    private String cmd;
    private String actionMethodName;
    private ArticleService articleService;
    private MemberService memberService;
    private Session session;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articleService = Container.articleService;
        memberService = Container.memberService;
        session = Container.getSession();
    }
    public void doAction(String cmd, String actionMethodName) {
        this.actionMethodName = actionMethodName;
        this.cmd = cmd;

        switch ( actionMethodName ) {
            case "작성" :
                doWrite();
                break;
            case "목록" :
                showList();
                break;
            case "상세" :
                showDetail();
                break;
            case "수정" :
                doModify();
                break;
            case "삭제" :
                doDelete();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }


    public void doWrite() {
        System.out.println("제목 : ");
        String title = sc.nextLine();
        System.out.println("내용 : ");
        String body = sc.nextLine();

        int memberId = session.getLoginedMember().getId();
        int boardId = session.getCurrentBoard().getId();

        int newId = articleService.write(memberId,boardId, title, body);

        System.out.printf("%d번 게시물이 생성되었습니다.\n", newId);
    }

    public void showList() {

        List<Article> forPrintArticles = articleService.getArticles();

//        String searchKeyword = cmd.substring("게시물 목록".length()).trim();
        //사용자가 검색어를 입력하면 searchKeyword에 담는다
//        List<Article> forPrintArticles = Container.articleService.getForPrintArticles(searchKeyword);

//        if (forPrintArticles.size() == 0) {
//            System.out.println("검색결과가 존재하지 않습니다");
//            return;
//        }


        System.out.println(" 번호 | 작성자 | 조회 | 제목 ");      //만약 검색어를 입력하지 않고 그냥 검색어 목록만
        for (int i = forPrintArticles.size() - 1; i >= 0; i--) {  //입력하면 모든 목록을 보여준다.
            Article article = forPrintArticles.get(i);
            String writerName = memberService.getMemberNameById(article.memberId);

            System.out.printf(" %4d | %6s | %4d | %s \n", article.id, writerName, article.hit, article.title);
        }
    }


    public  void doModify() {

        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = articleService.getArticleById(id);
        Member loginedMember = session.getLoginedMember();

            if (foundArticle == null) {
                //foundarticle이 null값이라면 실행
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

            if ( foundArticle.memberId != loginedMember.id ) {
                System.out.printf("권한이 없습니다.\n");
            }


            System.out.println("제목 : ");
            String title = sc.nextLine();
            System.out.println("내용 : ");
            String body = sc.nextLine();

            foundArticle.title = title;
            foundArticle.body = body;

            System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
        }


    public void doDelete() {


        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundArticle = articleService.getArticleById(id);
        Member loginedMember = session.getLoginedMember();

            if (foundArticle == null) {
                //foundarticle이 null값이라면 실행
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

        if ( foundArticle.memberId != loginedMember.id ) {
            System.out.printf("권한이 없습니다.\n");
            return;
        }

        articleService.remove(foundArticle);
        System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
        }



    public void showDetail() {


        String[] cmdBits = cmd.split(" ");
        int id = Integer.parseInt(cmdBits[2]);

        Article foundarticle = articleService.getForPrintArticle(id);


            if (foundarticle == null) {
                //foundarticle이 null값이라면 실행
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

            foundarticle.increaseHit();

            System.out.printf("번호 : %d\n", foundarticle.id);
            System.out.printf("날짜 : %s\n", foundarticle.regDate);
            System.out.printf("작성자 : %d\n", foundarticle.memberId);
            System.out.printf("제목 : %s\n", foundarticle.title);
            System.out.printf("내용 : %s\n", foundarticle.body);
            System.out.printf("조회 : %d\n", foundarticle.hit);
        }
}

