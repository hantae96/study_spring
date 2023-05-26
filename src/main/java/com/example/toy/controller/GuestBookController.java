package com.example.toy.controller;


import com.example.toy.common.BoardToViewBoardConverter;
import com.example.toy.domain.Board;
import com.example.toy.domain.Member;
import com.example.toy.dto.BoardDTO;
import com.example.toy.dto.BoardViewDTO;
import com.example.toy.service.BoardService;
import com.example.toy.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Controller
@AllArgsConstructor
public class GuestBookController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("guestBooks")
    public String GuestBook(Model model){
        List<BoardDTO> boards = boardService.getAllBoards();
        BoardToViewBoardConverter converter = new BoardToViewBoardConverter(memberService);
        List<BoardViewDTO> convertBoard =boards.stream().map(board -> converter.convert(board)).collect(Collectors.toList());

        model.addAttribute("boards", convertBoard);

        return "guestBook/board";
    }

    @GetMapping("guestBook")
    public String getBook(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,Model model){
        model.addAttribute("writerName", loginMember.getMemberName());
        model.addAttribute("writerId", loginMember.getMemberId());
        return "guestBook/addBoard";
    }
    @PostMapping("guestBook")
    public String addBook(@ModelAttribute Board board){
        boardService.save(board);
        return "redirect:/guestBooks";
    }

    @GetMapping("guestBooks/{id}")
    public String selectMember(@PathVariable("id") Integer bid,
                               @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                               Model model){

        BoardDTO board = boardService.getBoardById(bid);

        BoardToViewBoardConverter converter = new BoardToViewBoardConverter(memberService);
        BoardViewDTO convertBoard = converter.convert(board);
        model.addAttribute("board", convertBoard);
        return "guestBook/editBoard";
    }

    @PostMapping("/guestBooks/update")
    public String updateMember(@ModelAttribute BoardDTO boardDTO){
        boardService.updateBoard(boardDTO);
        return "redirect:/guestBooks";
    }

    @PostMapping("/guestBooks/delete")
    public String deleteMember(@RequestParam Integer bid){
        boardService.deleteBoard(bid);
        return "redirect:/guestBooks";
    }



}
