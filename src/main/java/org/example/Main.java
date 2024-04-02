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
//        int[] lotto = new int[7];
//        for (int i = 0; i < 6; i++) {
//            lotto[i] = sc.nextInt();
//        }
//        int bonus = sc.nextInt();
//
//        int[] my = new int[6];
//        for (int i = 0; i < 6; i++) {
//            my[i] = sc.nextInt();
//        }
//
//        int count = 0;
//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 6; j++) {
//                if (lotto[i] == my[j]) {
//                    count++;
//                }
//            }
//        }
//
//        if (count == 6) {
//            System.out.println(1);
//        } else if (count == 5 && bonus == my[5]) {
//            System.out.println(2);
//        } else if (count == 5) {
//            System.out.println(3);
//        } else if (count == 4) {
//            System.out.println(4);
//        } else if (count == 3) {
//            System.out.println(5);
//        } else {
//            System.out.println(0);
//        }
//    }
//}
