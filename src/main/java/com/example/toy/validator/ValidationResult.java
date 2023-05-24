package com.example.toy.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ValidationResult {
    private List<FieldErrorDetail> errors;

    public static ValidationResult create(Errors errors){
        List<FieldErrorDetail> details = errors.getFieldErrors().stream().map(error->FieldErrorDetail.create(error)).collect(Collectors.toList());
        return new ValidationResult(details);
    }
}
