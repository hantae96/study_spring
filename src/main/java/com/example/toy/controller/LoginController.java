package com.example.toy.controller;


import com.example.toy.domain.Member;
import com.example.toy.dto.LoginDTO;
import com.example.toy.dto.LoginSuccess;
import com.example.toy.exception.ValidError;
import com.example.toy.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


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
        return new ValidError("invalid", defaultMessage);
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


    @ResponseBody
    @PostMapping(value = "login")
    public LoginSuccess login(@Valid @ModelAttribute @NotNull LoginDTO loginDTO, BindingResult bindingResult, HttpServletRequest request) throws BindException {
        log.info("요청 정보 {} ",loginDTO.getMemberId());
        log.info("요청 정보 {} ",loginDTO.getPassword());
        Member login = memberService.getMemberById(loginDTO.getMemberId());
            if (login == null || !login.getPassword().equals(loginDTO.getPassword())) {
                bindingResult.reject("login", "아이디 혹은 비밀번호를 확인하세요");
            }
//        }

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        LoginSuccess loginSuccess = new LoginSuccess("valid", loginDTO.getMemberId());
        log.info("다시 브라우저에게 성공 요청을 보냄 {}",loginSuccess.getCode());
        return loginSuccess;
    }

    @PostMapping("join")
    public String join(@ModelAttribute Member member){
        memberService.save(member);
        return "redirect:/main";
    }

}
