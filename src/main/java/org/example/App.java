package org.example;

import org.example.DTO.Article;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private List<Article> articles; // public static void main(String[] args)이 static이기 때문에 List<Article> articles에도 static을 붙여준다.

    App() {

        articles = new ArrayList<>();
    }

    public void start() {


        System.out.println("== 프로그램 시작 ==");

        makeTestData();

        Scanner sc = new Scanner(System.in);


        while(true) {
            System.out.printf("명령어) ");
            String cmd = sc.nextLine();   //사용자의 키보드 입력을 받는애,
            cmd = cmd.trim();


            if ( cmd.length() == 0) {
                continue;
            }

            if (cmd.equals("exit")) {
                break;
            }

            if (cmd.equals("게시물 작성")) {
                int id = articles.size() + 1; //articles.size() 데이터를 몇개 넣었는지 반환해준다.
                String regDate = Util.getNowDateStr();
                System.out.println("제목 : ");
                String title = sc.nextLine();
                System.out.println("내용 : ");
                String body = sc.nextLine();


                Article article = new Article(id, regDate, title, body);
                articles. add(article);

                System.out.printf("%d번 게시물이 생성되었습니다.\n", id);

            }

            else if (cmd.equals("게시물 목록")) {
                if ( articles.size() == 0) {
                    System.out.println("게시물이 없습니다.");
                    continue;
                }

                System.out.println(" 번호 | 조회 | 제목 | 내용 ");
                for (int i = articles.size() -1; i >= 0; i--) {
                    Article article = articles.get(i);

                    System.out.printf(" %4d | %4d | %4s | %4s \n", article.id, article.hit, article.title, article.body, article.hit);
                }
            }

            else if (cmd.startsWith("게시물 보기 ")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                Article foundarticle = getArticleById(id);

                if (foundarticle == null) {
                    //foundarticle이 null값이라면 실행
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }

                foundarticle.increaseHit();

                System.out.printf("번호 : %d\n", foundarticle.id);
                System.out.printf("날짜 : %s\n", foundarticle.regDate);
                System.out.printf("제목 : %s\n", foundarticle.title);
                System.out.printf("내용 : %s\n", foundarticle.body);
                System.out.printf("조회 : %d\n", foundarticle.hit);





            }

            else if (cmd.startsWith("게시물 수정 ")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                Article foundarticle = getArticleById(id);

                if (foundarticle == null) {
                    //foundarticle이 null값이라면 실행
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }


                System.out.println("제목 : ");
                String title = sc.nextLine();
                System.out.println("내용 : ");
                String body = sc.nextLine();

                foundarticle.title = title;
                foundarticle.body = body;

                System.out.printf("%d번 게시물이 수정되었습니다.\n", id);




            }


            else if (cmd.startsWith("게시물 삭제 ")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);


                int foundIndex = getArticleIndexById(id);

                if ( foundIndex == -1) {
                    //foundarticle이 null값이라면 실행
                    System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                    continue;
                }



                articles.remove(foundIndex);
                System.out.printf("%d번 게시물이 삭제되었습니다.\n",id );

            }

            else {
                System.out.printf("%s(은)는 존재하지 않는 명령어 입니다\n", cmd);
            }
        }

        sc.close();
        System.out.println("== 프로그램 끝 ==");
    }

    private int getArticleIndexById(int id) {
        int i = 0;
        for (Article article : articles) {
            if ( article.id == id) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private Article getArticleById(int id) {
        int index = getArticleIndexById(id);

        if( index != -1) {
            return articles.get(index);
        }
        return null;
    }

    private void makeTestData() {
        System.out.println("테스트를 위한 게시물 데이터를 생성합니다");

        articles.add(new Article(1, Util.getNowDateStr(), "제목 1", "내용 1", 13));
        articles.add(new Article(2, Util.getNowDateStr(), "제목 2", "내용 2", 15));
        articles.add(new Article(3, Util.getNowDateStr(), "제목 3", "내용 3", 100));
    }
}


