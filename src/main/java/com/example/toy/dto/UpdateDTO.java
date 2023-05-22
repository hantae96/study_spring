package com.example.toy.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UpdateDTO {
    private String name;
    private String password;
    private String sex;
}
