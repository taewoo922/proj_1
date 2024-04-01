package org.example.service;

import org.example.container.Container;
import org.example.DTO.Member;
import org.example.dao.MemberDao;

public class MemberService {
    private MemberDao memberDao;

    public MemberService() {
        memberDao = Container.memberDao;
    }

    public int add(String loginId, String loginPassword, String name) {
        Member member = new Member(loginId, loginPassword, name);
        return memberDao.add(member);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public String getMemberNameById(int memberId) {
        return null;
    }
}
