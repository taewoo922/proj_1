package org.example.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member extends Dto{
    public String loginId;
    public String loginPassword;
    public String name;

    public Member(int id, String regDate, String loginId, String loginPassword, String name) {
        this.id = id;
        this.regDate = regDate;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
        this.name = name;
    }
}
