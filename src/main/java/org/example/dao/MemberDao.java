package org.example.dao;

import java.util.ArrayList;
import java.util.List;

import org.example.DTO.Article;
import org.example.DTO.Member;

public class MemberDao extends Dao {
    public List<Member> members;

    public MemberDao() {

        members = new ArrayList<>();
    }
    public void add(Member member) {
        members.add(member);
        lastId++;
    }
}
