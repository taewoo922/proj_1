package org.example.controller;


import org.example.DTO.ArticleReply;
import org.example.DTO.Board;
import org.example.DTO.Member;
import org.example.container.Container;
import org.example.DTO.Article;
import org.example.service.ArticleService;
import org.example.service.MemberService;
import org.example.util.Util;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private Scanner sc;

    private String cmd;
    private String actionMethodName;
    private ArticleService articleService;
    private MemberService memberService;
    private Session session;

    public ArticleController() {
        sc = Container.getScanner();
        articleService = Container.articleService;
        memberService = Container.memberService;
        session = Container.getSession();
    }
    public void doAction(String cmd, String actionMethodName) {
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
            case "현재게시판":
                doCurrentBoard();
                break;
            case "게시판변경":
                doChangeBoard();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    private void doChangeBoard() {

        System.out.println("1. 공지 게시판");
        System.out.println("2. 자유 게시판");
        System.out.print("게시판 번호를 입력하세요) ");

        int boardId = checkScNum();

        try {
            boardId = sc.nextInt();
            sc.nextLine();
        }

        catch ( InputMismatchException e) {
            System.out.println("잘못 입력하셨습니다.");
            sc.nextLine();
            return;
        }

        Board board = articleService.getBoard(boardId);

        if (board == null) {
            System.out.println("해당 게시판은 존재하지 않습니다.");
        }
        else {
            System.out.printf("[%s 게시판]으로 변경되었습니다.\n", board.getName());
            session.setCurrentBoard(board);
        }
    }

    private void doCurrentBoard() {
        Board board = session.getCurrentBoard();
        System.out.printf("현재 게시판 : [%s 게시판]\n", board.getName());
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
        String searchKeyword = cmd.substring("게시물 목록".length()).trim();
        String boardCode = Container.getSession().getCurrentBoard().getCode();

        List<Article> forPrintArticles = articleService.getForPrintArticles(boardCode,searchKeyword);

        if (forPrintArticles.size() == 0) {
            System.out.println("검색결과가 존재하지 않습니다");
            return;
        }

        String boardName = Container.getSession().getCurrentBoard().getName();

        System.out.printf("[%s 게시판]\n", boardName);
        System.out.println(" 번호 | 작성자 | 조회 | 제목 ");      //만약 검색어를 입력하지 않고 그냥 검색어 목록만
        for (int i = forPrintArticles.size() - 1; i >= 0; i--) {  //입력하면 모든 목록을 보여준다.
            Article article = forPrintArticles.get(i);
            Member member = memberService.getMember(article.memberId);

            System.out.printf(" %4d | %5s | %4d | %s \n", article.id, member.name, article.hit, article.title);
        }
    }


    public  void doModify() {
        System.out.print("수정할 게시물 번호를 입력하세요) ");
        int id = checkScNum();

        if (id == 0) {
            return;
        }

        Article foundArticle = articleService.getArticle(id);
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

           articleService.modify(foundArticle.id, title, body);

            System.out.printf("%d번 게시물이 수정되었습니다.\n", foundArticle.id);
        }


    public void doDelete() {
        System.out.print("삭제할 게시물 번호를 입력하세요) ");
        int id = checkScNum();

        if (id == 0) {
            return;
        }

        Article foundArticle = articleService.getArticle(id);
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

        articleService.delete(foundArticle.id);
        System.out.printf("%d번 게시물이 삭제되었습니다.\n", foundArticle.id);
        }

    private boolean articleReplyAuthorityCheck() {
        System.out.println("1) 네/ 2) 아니오");
        System.out.printf("입력) ");
        String replyCheck = sc.nextLine();

        if (replyCheck.equals("1") || replyCheck.equals("네")) {
            if (session.isLogined() == false) {
                System.out.println("로그인 후 이용 가능합니다.");
                return false;
            }
        } else {
            return false;
        }

        return true;

    }

    public void showDetail() {
        System.out.print("게시물 번호를 입력하세요) ");
        int id = checkScNum();

        if (id == 0) {
            return;
        }

        Article foundarticle = articleService.getForPrintArticle(id);


            if (foundarticle == null) {
                //foundarticle이 null값이라면 실행
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

            foundarticle.increaseHit();
            Member member = memberService.getMember(foundarticle.memberId);

            System.out.printf("번호 : %d\n", foundarticle.id);
            System.out.printf("날짜 : %s\n", foundarticle.regDate);
            System.out.printf("작성자 : %s\n", member.name);
            System.out.printf("제목 : %s\n", foundarticle.title);
            System.out.printf("내용 : %s\n", foundarticle.body);
            System.out.printf("조회 : %d\n", foundarticle.hit);

            System.out.printf("== [%d번 게시물 댓글] ==\n",id);
            articleRepliesShowList(id);

            System.out.println("댓글을 작성 하시겠습니까?");
            boolean replyCheck = articleReplyAuthorityCheck();

            if ( replyCheck == false ) {
                return;
            }

            if ( replyCheck ) {

                    System.out.println("댓글을 입력 해주세요");
                    System.out.printf("입력) ");
                    String replyBody = sc.nextLine();
                    int memberId = session.getLoginedMember().getId();

                    articleService.replyWrite(id, memberId, replyBody);
                    System.out.println("댓글이 작성되었습니다.");

                    articleRepliesShowList(id);

            }

    }


        private void articleRepliesShowList(int articleId) {
            List<ArticleReply> forPrintArticleReplies = articleService.getForPrintArticleReplies(articleId);

            System.out.printf("%d번 게시물 댓글\n", articleId);
            System.out.println(" 번호 | 작성자 | 제목 ");      //만약 검색어를 입력하지 않고 그냥 검색어 목록만
            for (int i = forPrintArticleReplies.size() - 1; i >= 0; i--) {  //입력하면 모든 목록을 보여준다.
                ArticleReply reply = forPrintArticleReplies.get(i);
                Member replyMember = memberService.getMember(reply.memberId);

                System.out.printf(" %4d | %5s | %s \n", reply.id, replyMember.name, reply.body);
            }
        }

        public int checkScNum() {
            int id = 0;

            try {
                id = sc.nextInt();
                sc.nextLine();
            }

            catch ( InputMismatchException e) {
                System.out.println("잘못 입력하셨습니다.");
                sc.nextLine();
                return 0;
            }
            return  id;
        }
}

