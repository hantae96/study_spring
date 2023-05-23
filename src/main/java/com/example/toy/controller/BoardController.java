package com.example.toy.controller;

import com.example.toy.domain.User;
import com.example.toy.dto.UpdateDTO;
import com.example.toy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class BoardController {

    private final UserService userService;

    @Autowired
    public BoardController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/card")
    public String updateCard(@ModelAttribute UpdateDTO updateDTO){
        userService.updateUserData(updateDTO);
        return "redirect:/board/view";
    }

    @GetMapping("/board/{index}")
    public String selectBoard(@PathVariable Integer index, Model model){
        User findUser = userService.findUserById(index);
        model.addAttribute("user",findUser);
        model.addAttribute("id", index);
        return "board/card";
    }
}
