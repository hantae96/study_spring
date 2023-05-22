package com.example.toy.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String sex;
}
