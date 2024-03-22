package org.example.controller;

import org.example.DTO.Member;
import org.example.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller{
    private Scanner sc;

    private List<Member> members;
    private String cmd;
    private String actionMethodName;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doAction(String cmd, String actionMethodName) {
        this.actionMethodName = actionMethodName;
        this.cmd = cmd;

        switch ( actionMethodName) {
            case "join" :
                doJoin();
                break;
        }
    }
    public void doJoin() {


        int id = members.size() +1;
        String regDate = Util.getNowDateStr();

        String loginId = null;

        while ( true ) {
            System.out.println("Id를 입력해주세요");
            loginId = sc.nextLine();

            if( isJoinableLoginId(loginId)) {
                System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
                continue;
            }

            break;
        }

        String loginPassword = null;
        String loginPasswordConfirm = null;

        while ( true ) {
            System.out.println("비밀먼호를 입력해주세요");
            loginPassword = sc.nextLine();
            System.out.println("비밀먼호를 확인해주세요");
            loginPasswordConfirm = sc.nextLine();

            if (loginPassword.equals(loginPasswordConfirm) == false ) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 작성해주세요");
                continue;
            }

            break;

        }

        System.out.println("이름을 입력해주세요");
        String name = sc.nextLine();

//                if (id == null) {
//                    System.out.printf("아이디를 입력해주세요");
//                    continue;
//                }

        Member member = new Member(id, regDate, loginId, loginPassword, name);
        members.add(member);

        System.out.printf("%s 회원이 생성되었습니다. 환영합니다.\n", id);

    }
    private boolean isJoinableLoginId(String loginId) {
        int index = getMemberIndexByLoginId(loginId);

        if( index != -1) {
            return true;
        }
        return false;
    }
    private int getMemberIndexByLoginId(String loginId) {
        int i = 0;

        for (Member member : members) {
            if ( member.loginId.equals(loginId) ) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
