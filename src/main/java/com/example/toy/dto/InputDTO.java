package com.example.toy.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class InputDTO {
    private String userName;
    private String password;
    private String sex;
}
