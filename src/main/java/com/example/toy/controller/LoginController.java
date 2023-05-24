package com.example.toy.controller;


import com.example.toy.domain.Member;
import com.example.toy.dto.LoginDTO;
import com.example.toy.exception.ValidError;
import com.example.toy.service.MemberService;
import com.example.toy.validator.ValidationResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.validation.Validator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;


@Controller
@Slf4j
@AllArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 바인더없이 그냥 도메인에서 @DateTimeFormat사용하면됨
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(LocalDate.class,new CustomDateEditor(dateFormat,true));
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ValidError illegalExHandle(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        ObjectError globalError = bindingResult.getGlobalError();
        String defaultMessage = globalError.getDefaultMessage();
        return new ValidError("valid", defaultMessage);
    }


    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("login")
    public String viewLogin(){
        return "login/login";
    }



    @GetMapping("join")
    public String viewJoin(){
        return "login/form";
    }


    @PostMapping("login")
    public String login(@Valid @NotNull LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request) throws BindException {
            Member login = memberService.getMemberById(loginDTO.getMemberId());
            if (login == null || !login.getPassword().equals(loginDTO.getPassword())) {
                bindingResult.reject("login", "아이디 혹은 비밀번호를 확인하세요");
            }
//        }

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return "main";
    }

    @PostMapping("join")
    public String join(@ModelAttribute Member member){
        memberService.save(member);
        return "redirect:/main";
    }

}
