package com.example.toy.controller;

import com.example.toy.domain.Member;
import com.example.toy.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MainController {

    @GetMapping("main")
    public String main(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember, Model model){
        if(loginMember == null){
            return "redirect:/";
        }
        model.addAttribute("loginMemberName", loginMember.getMemberName());
        return "main";
    }
}
