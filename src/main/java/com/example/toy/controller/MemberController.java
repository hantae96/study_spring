package com.example.toy.controller;

import com.example.toy.domain.Member;
import com.example.toy.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("members")
    public String AllMembers(Model model){
        List<Member> allMembers = memberService.getAllMembers();
        model.addAttribute("members",allMembers);
        return "board/members";
    }

    @GetMapping("members/{id}")
    public String selectMember(@PathVariable("id") String memberId,Model model){

        Member findMember = memberService.getMemberById(memberId);
        model.addAttribute("member", findMember);
        return "board/editMember";
    }

    @PostMapping("/member/update")
    public String updateMember(@ModelAttribute Member member){
        memberService.updateMember(member);
        return "redirect:/members";
    }

    @PostMapping("/member/delete")
    public String deleteMember(@RequestParam String memberId){
        memberService.deleteMember(memberId);
        return "redirect:/members";
    }


}
