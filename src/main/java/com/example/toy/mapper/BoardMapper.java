package com.example.toy.mapper;

import com.example.toy.domain.Board;
import com.example.toy.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    void save(Board board);

    List<BoardDTO> findAll();

    Board findOne(Integer bid);

    void update(BoardDTO boardDTO);

//    void delete(String memberId);
}
