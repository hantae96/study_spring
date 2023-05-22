package com.example.toy.controller;


import com.example.toy.domain.User;
import com.example.toy.service.TmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("tmp")
public class TmpController {

    private final TmpService tmpService;

    @Autowired
    public TmpController(TmpService tmpService) {
        this.tmpService = tmpService;
    }

    @GetMapping("/input")
    public String viewTmp(){
        return "form/input";
    }

    @PostMapping("/input")
    public String submitTmp(@RequestParam String userName , RedirectAttributes redirectAttributes){
        tmpService.register(userName);
        redirectAttributes.addAttribute("userName", userName);
        return "redirect:/tmp/view";
    }

    @GetMapping("/view")
    public String userBoard(@RequestParam String userName,Model model){
//        model.addAttribute("userName",userName);
        List<User> userList = tmpService.findAll();
        model.addAttribute("userList",userList);
        return "view";
    }




}
