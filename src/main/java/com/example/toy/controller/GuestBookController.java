package com.example.toy.controller;


import com.example.toy.common.BoardToViewBoardConverter;
import com.example.toy.domain.Board;
import com.example.toy.domain.Comment;
import com.example.toy.domain.Member;
import com.example.toy.dto.BoardDTO;
import com.example.toy.dto.BoardViewDTO;
import com.example.toy.service.BoardService;
import com.example.toy.service.CommentService;
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
    private final CommentService commentService;


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
    public String selectBoard(@PathVariable("id") Integer bid,
                               @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                               Model model){

        // 상세 글 정보  가져오기
        BoardDTO board = boardService.getBoardById(bid);
        BoardToViewBoardConverter converter = new BoardToViewBoardConverter(memberService);
        BoardViewDTO convertBoard = converter.convert(board);

        // 해당 댓글 가져오기
        List<Comment> comments = commentService.getCommentByBoardId(bid);

        // 상세 글 정보 추가
        model.addAttribute("board", convertBoard);
        model.addAttribute("loginMemberId", loginMember.getMemberId());
        model.addAttribute("writerMemberId", board.getWriterId());

        // 댓글 추가
        model.addAttribute("comments", comments);

        return "guestBook/viewBoard";
    }

    @GetMapping("guestBooks/update/{id}")
    public String updateBoard(@PathVariable("id") Integer bid,
                              @SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                              Model model){

        BoardDTO board = boardService.getBoardById(bid);

        BoardToViewBoardConverter converter = new BoardToViewBoardConverter(memberService);
        BoardViewDTO convertBoard = converter.convert(board);

        model.addAttribute("board", convertBoard);
        model.addAttribute("loginMemberId", loginMember.getMemberId());
        model.addAttribute("writerMemberId", board.getWriterId());

        return "guestBook/editBoard";
    }

    @GetMapping("/guestBooks/update")
    public String viewUpdateForm(@ModelAttribute BoardDTO boardDTO){
        boardService.updateBoard(boardDTO);
        return "redirect:/guestBooks";
    }

    @PostMapping("/guestBooks/update")
    public String updateForm(@ModelAttribute BoardDTO boardDTO){
        boardService.updateBoard(boardDTO);
        return "redirect:/guestBooks";
    }

    @PostMapping("/guestBooks/delete")
    public String deleteMember(@RequestParam Integer bid){
        boardService.deleteBoard(bid);
        return "redirect:/guestBooks";
    }



}
