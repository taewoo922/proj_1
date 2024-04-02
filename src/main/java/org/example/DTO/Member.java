package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor

public class Member extends Dto{
    public String loginId;
    public String loginPassword;
    public String name;

    public Member (Map<String, Object> row){
        super(row);
        this.loginId = (String) row.get("loginId");
        this.loginPassword = (String) row.get("loginPassword");
        this.name = (String) row.get("name");
    }

}
