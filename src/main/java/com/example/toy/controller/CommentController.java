package com.example.toy.controller;

import com.example.toy.domain.Comment;
import com.example.toy.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("comment")
    public String addComment(@ModelAttribute Comment comment){
        commentService.save(comment);
        Integer boardId = comment.getRefBoardId();
        return "redirect:/guestBooks/update/" + boardId;
    }

    @ResponseBody
    @GetMapping("comment")
    public List<Comment> viewComment(@RequestParam Integer bid){
        List<Comment> comments = commentService.getCommentByBoardId(bid);
        log.info("ajax로 들어온 get 요청");
        return comments;
    }
}
