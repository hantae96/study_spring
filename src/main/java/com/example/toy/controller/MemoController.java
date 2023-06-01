package com.example.toy.controller;


import com.example.toy.dto.MemoDTO;
import com.example.toy.dto.PageRequestDTO;
import com.example.toy.dto.PageResponseDTO;
import com.example.toy.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {
    private final MemoService memoService;

    @GetMapping("/list")
    private String list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<MemoDTO> responseDTO = memoService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "memo/list";
    }

    @GetMapping("/register")
    public void registerGET(){
    }

    @PostMapping("/register")
    public String registerPost(@Valid MemoDTO memoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        Long mno = memoService.register(memoDTO);
        redirectAttributes.addFlashAttribute("result", mno);
        return "redirect:/memo/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Long mno, PageRequestDTO pageRequestDTO, Model model){
        MemoDTO memoDTO = memoService.readOne(mno);
        model.addAttribute("dto", memoDTO);
    }

    @PostMapping("/modify")
    public String modfiy(PageRequestDTO pageRequestDTO,
                         @Valid MemoDTO memoDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("mno", memoDTO.getMno());
        }

        memoService.modify(memoDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("mno", memoDTO.getMno());
        return "redirect:/memo/read";
    }
}
