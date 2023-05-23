package com.example.toy.controller;


import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/board/view")
    public String board(@ModelAttribute InputDTO inputDTO,Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "board/view";
    }

    @GetMapping("/user")
    public String viewForm(){
        return "form/inputUserData";
    }

    @PostMapping("/user")
    // 파라미터가 많을때 DTO로 한방에 묶어서 한다
    public String registForm(@ModelAttribute InputDTO inputDTO, RedirectAttributes redirectAttributes){
        userService.register(inputDTO);
        // 리다이렉트는 객체 안에 값을 넣을때 String으로 변환해서 넣는다 -> url에 쿼리파라미터 형식으로 넣기 때문에
        // 하지만 객체는 못넣는다 객체는 addFlashAttribute를 사용해서 넣어야된다.
        redirectAttributes.addFlashAttribute("inputDTO",inputDTO);
        return "redirect:/view";
    }

}
