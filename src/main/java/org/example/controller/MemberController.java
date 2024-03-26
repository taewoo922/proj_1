package org.example.controller;


import org.example.container.Container;
import org.example.DTO.Member;
import org.example.service.MemberService;
import org.example.util.Util;

import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller{
    private Scanner sc;

    private List<Member> members;
    private String cmd;
    private String actionMethodName;

    private MemberService memberService;


    public MemberController(Scanner sc) {
        this.sc = sc;
        memberService = Container.memberService;

    }


    public void doAction(String cmd, String actionMethodName) {
        this.cmd = cmd;
        this.actionMethodName = actionMethodName;


        switch ( actionMethodName) {
            case "가입" :
                doJoin();
                break;
            case "로그인" :
                doLogin();
                break;
            case "로그아웃" :
                doLogout();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }
    }

    public void makeTestData() {
        System.out.println("테스트를 위한 회원 데이터를 생성합니다");

        memberService.add(new Member(Container.memberDao.getNewId(),Util.getNowDateStr(), "admin", "admin", "관리자"));
        memberService.add(new Member(Container.memberDao.getNewId(),Util.getNowDateStr(), "user1", "user1", "영희"));
        memberService.add(new Member(Container.memberDao.getNewId(),Util.getNowDateStr(), "user2", "user2", "니영"));


    }

    public void doJoin() {


        int id = Container.memberDao.getNewId();
        String regDate = Util.getNowDateStr();

        String loginId = null;

        while ( true ) {
            System.out.printf("Id를 입력해주세요 : ");
            loginId = sc.nextLine();

            if( isJoinableLoginId(loginId) == false) {
                System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
                continue;
            }

            break;
        }

        String loginPassword = null;
        String loginPasswordConfirm = null;

        while ( true ) {
            System.out.printf("비밀먼호를 입력해주세요 : ");
            loginPassword = sc.nextLine();
            System.out.printf("비밀먼호를 확인해주세요 : ");
            loginPasswordConfirm = sc.nextLine();

            if (loginPassword.equals(loginPasswordConfirm) == false ) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 작성해주세요");
                continue;
            }

            break;

        }

        System.out.printf("이름을 입력해주세요");
        String name = sc.nextLine();


        Member member = new Member(id, regDate, loginId, loginPassword, name);
        memberService.add(member);

        System.out.printf("%s 회원이 생성되었습니다. 환영합니다.\n", id);

    }

    public void doLogin() {
        System.out.printf("Id를 입력해주세요:\n");
        String loginId = sc.nextLine();
        System.out.printf("비밀먼호를 입력해주세요 : \n");
        String loginPassword = sc.nextLine();

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            System.out.println("해당회원은 존재하지 않습니다.");
            return;
        }

        if (member.loginPassword.equals(loginPassword) == false) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        loginedMember = member;
        System.out.printf("로그인 성공 %s님 환영합니다\n", loginedMember.name);

    }

    private void doLogout() {

        loginedMember = null;
        System.out.println("로그아웃 되었습니다.");

    }

    private boolean isJoinableLoginId(String loginId) {
        int index = memberService.getMemberIndexByLoginId(loginId);

        if( index == -1) {
            return true;
        }

        return false;
    }


}
