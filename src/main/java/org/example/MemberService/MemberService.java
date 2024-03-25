package org.example.MemberService;

import org.example.Container;
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
}
