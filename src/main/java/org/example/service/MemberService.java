package org.example.service;

import org.example.container.Container;
import org.example.DTO.Member;
import org.example.dao.MemberDao;

public class MemberService {
    private MemberDao memberDao;

    public MemberService() {
        memberDao = Container.memberDao;
    }

    public void add(Member member) {

        memberDao.add(member);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }

    public int getMemberIndexByLoginId(String loginId) {
        return memberDao.getMemberIndexByLoginId(loginId);
    }

    public String getMemberNameById(int memberId) {
        return memberDao.getMemberNameById(memberId);
    }
}
