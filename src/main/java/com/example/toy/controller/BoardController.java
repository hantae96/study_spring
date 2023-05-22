package com.example.toy.controller;

import com.example.toy.domain.User;
import com.example.toy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    private final UserService userService;

    @Autowired
    public BoardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/board/{index}")
    public String selectBoard(@PathVariable Integer index, Model model){
        User findUser = userService.findUserById(index);
        model.addAttribute("user",findUser);
        return "board/card";
    }
}
