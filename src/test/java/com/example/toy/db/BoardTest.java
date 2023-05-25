package com.example.toy.db;


import com.example.toy.domain.Board;
import com.example.toy.domain.Member;
import com.example.toy.dto.BoardDTO;
import com.example.toy.mapper.MemberMapper;
import com.example.toy.repository.BoardRepository;
import com.example.toy.repository.MemberRepository;
import com.example.toy.service.MemberService;
import javafx.scene.media.MediaMarkerEvent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class BoardTest {

    private final BoardRepository repository;

    @Autowired
    public BoardTest(BoardRepository repository) {
        this.repository = repository;
    }

    @Test
    public void saveTest(){
        Board board = new Board();
        board.setTitle("방명록 테스트");
        board.setContent("글 내용입니다.");
        board.setWriterId("lager96");

        repository.save(board);
    }

    @Test
    public void viewAll(){
        List<BoardDTO> all = repository.findAll();
        for (BoardDTO board : all) {
            System.out.println("board.getTitle() = " + board.getBid());
            System.out.println("board.getTitle() = " + board.getTitle());
            System.out.println("board.getTitle() = " + board.getContent());
            System.out.println("board.getTitle() = " + board.getWriterId());

        }
    }

    @Test
    public void viewOne(){
        Integer bid = 3;
         String find = repository.findOne(bid).getTitle();
        Assertions.assertThat(find).isEqualTo("방명록 테스트");
    }

    @Test
    public void update(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBid(4);
        boardDTO.setTitle("수정된 글제목");
        boardDTO.setContent("수정된 글본문");
        boardDTO.setWriterId("lager96");
        repository.update(boardDTO);

        BoardDTO findDTO = repository.findOne(4);

        Assertions.assertThat(findDTO).isEqualTo(boardDTO);
    }

}
