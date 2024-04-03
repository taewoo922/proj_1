package org.example.controller;


import org.example.container.Container;
import org.example.DTO.Member;
import org.example.service.MemberService;
import java.util.Scanner;

public class MemberController extends Controller{
    private Scanner sc;

    private MemberService memberService;

    private Session session;

    public MemberController() {
        sc = Container.getScanner();
        memberService = Container.memberService;
        session = Container.getSession();

    }


    public void doAction(String cmd, String actionMethodName) {

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


    public void doJoin() {

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



        memberService.add(loginId, loginPassword, name);

        System.out.printf("[%s]님! 회원가입이 완료되었습니다. 환영합니다!\n", name);

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


        session.setLoginedMember(member);
        Member loginedMember = session.getLoginedMember();

        System.out.printf("로그인 성공 %s님 환영합니다\n", loginedMember.name);

    }

    private void doLogout() {

        session.setLoginedMember(null);
        System.out.println("로그아웃 되었습니다.");

    }

    private boolean isJoinableLoginId(String loginId) {
        Member member = memberService.getMemberByLoginId(loginId);


        if( member == null) {
            return true;
        }

        return false;
    }


}
