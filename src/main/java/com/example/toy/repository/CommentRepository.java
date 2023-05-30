package com.example.toy.repository;

import com.example.toy.domain.Board;
import com.example.toy.domain.Comment;
import com.example.toy.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CommentRepository {

    private final CommentMapper mapper;
    private final SqlSession sqlSession;

    public void save(Comment comment){
        sqlSession.insert("com.example.toy.mapper.CommentMapper.save", comment);
    }

    public List<Comment> findAll(){
        return sqlSession.selectList("com.example.toy.mapper.CommentMapper.findAll");
    }
//
    public List<Comment> findByBoardId(Integer bid){
        return sqlSession.selectList("com.example.toy.mapper.CommentMapper.findByBoardId", bid);
    }
//
//    public Integer update(BoardDTO boardDTO){
//        return sqlSession.update("com.example.toy.mapper.BoardMapper.update", boardDTO);
//    }
//
//    public void delete(Integer bid){
//        sqlSession.delete("com.example.toy.mapper.BoardMapper.delete", bid);
//    }
}
