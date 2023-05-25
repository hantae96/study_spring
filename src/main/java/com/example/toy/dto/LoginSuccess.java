package com.example.toy.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LoginSuccess {
    private String code;
    private String memberId;

    public LoginSuccess(String code, String memberId) {
        this.code = code;
        this.memberId = memberId;
    }
}
