package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);

        int lastArticleId = 0;

        List<Article> articles = new ArrayList<>();



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
                int id = lastArticleId + 1;
                String regDate = Util.getNowDateStr();
                lastArticleId = id;
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

                    System.out.printf(" %4d | %4d | %4s | %4s \n", article.id, article.hit, article.title, article.body);
                }
            }

            else if (cmd.startsWith("게시물 보기 ")){
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);
                Article foundarticle = null;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.id == id) {
                        foundarticle = article;
                        //article.id와 id가 동일하다면 foundarticle에 article을 저장
                        break;
                    }
                }
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
                Article foundarticle = null;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.id == id) {
                        foundarticle = article;
                        //article.id와 id가 동일하다면 foundarticle에 article을 저장
                        break;
                    }
                }
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


                int foundIndex = -1;

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);
                    if (article.id == id) {
                        foundIndex = i;
                        //article.id와 id가 동일하다면 foundarticle에 article을 저장
                        break;
                    }
                }
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
}

class Article {
    int id;
    String regDate;
    String title;
    String body;

    int hit;



    public Article(int id, String regDate, String title, String body) {
        this.id = id;
        this.regDate = regDate;
        this.title = title;
        this.body = body;
        this.hit = 0;
    }

    public void increaseHit() {
        hit++;
    }
}


