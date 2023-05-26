package com.example.toy.service;

import com.example.toy.domain.Board;
import com.example.toy.domain.Comment;
import com.example.toy.dto.BoardDTO;
import com.example.toy.repository.BoardRepository;
import com.example.toy.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> getAllBoards(){
        return commentRepository.findAll();
    }

    public List<Comment> getCommentByBoardId(Integer bid){
        return commentRepository.findByBoardId(bid);
    }
//
//    public void updateBoard(BoardDTO boardDTO){
//        boardRepository.update(boardDTO);
//    }
//
//    public void deleteBoard(Integer bid){
//        boardRepository.delete(bid);
//    }


}
