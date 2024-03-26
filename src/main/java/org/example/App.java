package org.example;

import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;



import java.util.Scanner;

public class App {


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

            if ( cmdBits.length == 1) {
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }

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

            String actionName = controllerName + "/" + actionMethodName;

            switch (actionName) {
                case "게시물/작성":
                case "게시물/삭제":
                case "게시물/수정":
                case "회원/로그아웃":
                    if ( Controller.isLogined() == false ) {
                        System.out.println("로그인 후 이용해주세요");
                        continue;
                    }
                    break;
            }

            switch (actionName) {
                case "회원/로그인":
                case "회원/가입":
                    if ( Controller.isLogined()) {
                        System.out.println("로그아웃 후 이용해주세요");
                        continue;
                    }
                    break;
            }

            controller.doAction(cmd, actionMethodName);

        }

        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }
}


