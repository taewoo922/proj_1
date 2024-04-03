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
//        double k = sc.nextDouble();
//        double w = sc.nextDouble();
//
//        double s;
//        double b;
//
//        if ( k < 150 ) {
//            s = k-100;
//        }
//        else if (k >= 150 && k < 160) {
//            s = (k-150)/2 +50;
//        }
//        else {
//            s = (k-100) * 0.9;
//        }
//
//        b = (w - s) * (100 / s);
//
//        if (b <= 10.0) {
//            System.out.println("정상");
//        } else if (b > 10.0 && b <= 20.0) {
//            System.out.println("과체중");
//        } else {
//            System.out.println("비만");
//        }
//
//        sc.close();
//    }
//}
//
