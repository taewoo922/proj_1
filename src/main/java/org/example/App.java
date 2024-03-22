package org.example;
import org.example.DTO.Member;
import org.example.DTO.Article;
import org.example.controller.ArticleController;
import org.example.controller.Controller;
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
        memberController.makeTestData();

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

            String[] cmdBits = cmd.split(" "); //article write
            String controllerName = cmdBits[0];//article/member
            String actionMethodName = cmdBits[1]; //write/ list

            Controller controller = null;

            if( controllerName.equals("게시물") ) {
                controller = articleController;
            }
            else if ( controllerName.equals("회원")) {
                controller = memberController;
            }
            else {
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            controller.doAction(cmd, actionMethodName);
        }

            sc.close();
            System.out.println("== 프로그램 끝 ==");
    }
}


