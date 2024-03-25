package org.example.controller;
import org.example.DTO.Member;

public abstract class Controller {
    public static Member loginedMember;
    public static boolean isLogined() {

        return loginedMember != null;
    }
    public abstract void doAction(String cmd, String actionMethodName);
    public abstract void makeTestData();

}
