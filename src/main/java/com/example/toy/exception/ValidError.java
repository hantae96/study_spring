package com.example.toy.exception;


import com.example.toy.validator.FieldErrorDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Data
public class ValidError {
    private String code;
    private String defaultMassage;

    public ValidError(String code, String defaultMassage) {
        this.code = code;
        this.defaultMassage = defaultMassage;
    }
}
