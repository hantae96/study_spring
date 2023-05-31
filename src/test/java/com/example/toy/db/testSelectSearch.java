package com.example.toy.db;


import com.example.toy.dto.BoardDTO;
import com.example.toy.dto.PageRequestDTO;
import com.example.toy.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class testSelectSearch {

    private final BoardRepository boardRepository;
    @Autowired
    public testSelectSearch(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Test
    public void search(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).types(new String[]{"t", "w"}).keyword("AAAA").build();
        List<BoardDTO> boardDTOList = boardRepository.selectList(pageRequestDTO);
        boardDTOList.forEach(dto -> System.out.println(dto));
    }
}
