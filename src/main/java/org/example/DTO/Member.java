package org.example.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Member extends Dto{
    public String loginId;
    public String loginPassword;
    public String name;

    public Member(String loginId, String loginPassword, String name) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.name = name;
    }

    public Member (Map<String, Object> row){
//        super(row);
        this.id = (int) row.get("id");
        this.loginId = (String) row.get("loginId");
        this.loginPassword = (String) row.get("loginPassword");
        this.name = (String) row.get("name");
    }
}
