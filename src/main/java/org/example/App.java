package org.example;

import org.example.container.Container;
import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.ExportController;
import org.example.controller.MemberController;
import org.example.db.DBConnection;

public class App {
    public App() {
        DBConnection.DB_NAME = "sbs_proj";
        DBConnection.DB_USER = "sbsst";
        DBConnection.DB_PASSWORD = "sbs123414";
        DBConnection.DB_PORT = 3306;

        Container.getDBConnection().connect();

        //현재 게시판을 1번 게시판으로 선택
        Container.getSession().setCurrentBoard(Container.articleService.getBoard(2));
    }


    public void start() {


        System.out.println("== 프로그램 시작 ==");
        System.out.println("== 명령어 모음 ==");
        System.out.println("1. 회원가입 : 회원 가입");
        System.out.println("2. 로그인/로그아웃 : 회원 로그인/회원 로그아웃");
        System.out.println("3. 현재 게시판 확인 : 게시물 현재게시판");
        System.out.println("4. 게시판 변경: 게시물 게시판변경");
        System.out.println("5. 게시물 리스트 : 게시물 목록");
        System.out.println("6. 게시물 상세 : 게시물 상세 [게시물번호]");
        System.out.println("7. 게시물 작성(로그인 후 이용가능) : 게시물 작성");
        System.out.println("5. 게시물 수정/삭제(로그인 후 이용가능) : 게시물 수정/삭제 [게시물번호]");



        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();
        ExportController exportController = new ExportController();


        while (true) {
            System.out.printf("명령어) ");
            String cmd = Container.getScanner().nextLine();   //사용자의 키보드 입력을 받는애,
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
            else if ( controllerName.equals("export")) {
                controller = exportController;
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
                    if ( Container.getSession().isLogined() == false ) {
                        System.out.println("로그인 후 이용해주세요");
                        continue;
                    }
                    break;
            }

            switch (actionName) {
                case "회원/로그인":
                case "회원/가입":
                    if ( Container.getSession().isLogined()) {
                        System.out.println("로그아웃 후 이용해주세요");
                        continue;
                    }
                    break;
            }

            controller.doAction(cmd, actionMethodName);

        }

        Container.getDBConnection().close();
        Container.getScanner().close();

        System.out.println("== 프로그램 끝 ==");
    }
}


