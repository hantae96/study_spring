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
        // 모든 board에서 찾아서 새로운 객채로 다시 맵핑해야됨

        List<BoardViewDTO> convertBoard =boards.stream().map(board -> converter.convert(board)).collect(Collectors.toList());
        model.addAttribute("boards", convertBoard);

        return "guestBook/board";
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

}
