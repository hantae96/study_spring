package com.example.toy.mapper;

import com.example.toy.domain.Board;
import com.example.toy.domain.Comment;
import com.example.toy.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper{

    void save(Comment comment);

    List<Comment> findAll();

    List<Comment> findByBoardId(Integer bid);
//
//    void update(BoardDTO boardDTO);
//
//    void delete(Integer bid);
}
