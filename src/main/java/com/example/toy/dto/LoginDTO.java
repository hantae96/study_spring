package com.example.toy.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginDTO {
    private String memberId;
    private String password;
}
