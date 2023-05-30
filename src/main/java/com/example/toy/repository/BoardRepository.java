package com.example.toy.repository;

import com.example.toy.domain.Board;
import com.example.toy.domain.Member;
import com.example.toy.dto.BoardDTO;
import com.example.toy.dto.PageRequestDTO;
import com.example.toy.mapper.BoardMapper;
import com.example.toy.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BoardRepository {

    private final BoardMapper mapper;
    private final SqlSession sqlSession;
    public void save(Board board){
        sqlSession.insert("com.example.toy.mapper.BoardMapper.save", board);
    }

    public List<BoardDTO> findAll(){
        return sqlSession.selectList("com.example.toy.mapper.BoardMapper.findAll");
    }

    public BoardDTO findOne(Integer bid){
        return sqlSession.selectOne("com.example.toy.mapper.BoardMapper.findOne", bid);
    }

    public Integer update(BoardDTO boardDTO){
        return sqlSession.update("com.example.toy.mapper.BoardMapper.update", boardDTO);
    }

    public void delete(Integer bid){
        sqlSession.delete("com.example.toy.mapper.BoardMapper.delete", bid);
    }

    public List<BoardDTO> selectList(PageRequestDTO pageRequestDTO){
        return sqlSession.selectList("com.example.toy.mapper.BoardMapper.selectList", pageRequestDTO);
    }

    public Integer getCount(){
        return sqlSession.selectOne("com.example.toy.mapper.BoardMapper.getCount");
    }
}
