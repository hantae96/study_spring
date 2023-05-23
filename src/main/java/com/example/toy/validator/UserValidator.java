package com.example.toy.validator;

import com.example.toy.dto.RecordWriteDTO;
import com.example.toy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {

    private final UserService userService;
    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RecordWriteDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RecordWriteDTO writeDTO = (RecordWriteDTO) target;

        String name = writeDTO.getUserName();
        if(!checkUserName(name)){
            errors.rejectValue("userName","none",new Object[]{name},null);
        }
    }

    private boolean checkUserName(String userName) {
        // 결과셋에 값이 딱 하나만 있으면 중복이 없고, DB에 값이 존재하는 거니까
        return userService.findByName(userName) != null;
    }
}
