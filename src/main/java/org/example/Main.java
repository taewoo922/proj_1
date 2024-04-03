package org.example;


public class Main {

    public static void main(String[] args) {
        new App().start();
    }
}

//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String a = sc.nextLine();
//        if (a.contains("+")) {
//            String[] b = a.split("\\+");
//            System.out.println(Integer.parseInt(b[0])+ Integer.parseInt(b[1]));
//        }
//        else if (a.contains("-")) {
//            String[] b = a.split("\\-");
//            System.out.println(Integer.parseInt(b[0])-Integer.parseInt(b[1]));
//        }
//        else if (a.contains("*")) {
//            String[] b = a.split("\\*");
//            System.out.println(Integer.parseInt(b[0])*Integer.parseInt(b[1]));
//        }
//        else if (a.contains("/")) {
//            String[] b = a.split("\\/");
//            System.out.println(String.format("%.2f",(double)Integer.parseInt(b[0])/Integer.parseInt(b[1])));
//        }
//
//        sc.close();
//    }
//}

