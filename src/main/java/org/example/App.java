package org.example;
import org.example.DTO.Member;
import org.example.DTO.Article;
import org.example.controller.ArticleController;
import org.example.controller.MemberController;
import org.example.util.Util;

//import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {

    private List<Article> articles; // public static void main(String[] args)이 static이기 때문에 List<Article> articles에도 static을 붙여준다.

    //    private List<Member> members;
    App() {
        articles = new ArrayList<>();
    }

    public void start() {


        System.out.println("== 프로그램 시작 ==");


        Scanner sc = new Scanner(System.in);

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);
        articleController.makeTestData();

        while (true) {
            System.out.printf("명령어) ");
            String cmd = sc.nextLine();   //사용자의 키보드 입력을 받는애,
            cmd = cmd.trim();


            if (cmd.length() == 0) {
                continue;
            }

            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("회원가입")) {
                memberController.doJoin();
            } else if (cmd.equals("게시물 작성")) {
                articleController.doWrite();
            } else if (cmd.startsWith("게시물 목록")) {
                articleController.showList(cmd);
            } else if (cmd.startsWith("게시물 상세 ")) {
                articleController.showDetail(cmd);
            } else if (cmd.startsWith("게시물 수정 ")) {
                articleController.doModify(cmd);
            } else if (cmd.startsWith("게시물 삭제 ")) {
                articleController.doDelete(cmd);
            } else {
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다.\n", cmd);
            }
        }
            sc.close();
            System.out.println("== 프로그램 끝 ==");
    }
}


