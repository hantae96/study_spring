package com.example.toy.exception;

import lombok.Data;

@Data
public class ValidError {
    private String code;
    private String defaultMassage;

    public ValidError(String code, String defaultMassage) {
        this.code = code;
        this.defaultMassage = defaultMassage;
    }
}
