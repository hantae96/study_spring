package com.example.toy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateDTO {
    private Integer userId;
    private String userName;
    private String password;
    private String sex;
}
