package com.example.toy.service;

import com.example.toy.domain.Board;
import com.example.toy.domain.Member;
import com.example.toy.dto.BoardDTO;
import com.example.toy.dto.PageRequestDTO;
import com.example.toy.dto.PageResponseDTO;
import com.example.toy.repository.BoardRepository;
import com.example.toy.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(Board board){
        boardRepository.save(board);
    }

    public List<BoardDTO> getAllBoards(){
        return boardRepository.findAll();
    }

    public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO){
        List<BoardDTO> boardDTOList = boardRepository.selectList(pageRequestDTO);

        Integer count = boardDTOList.size();

        PageResponseDTO<BoardDTO> pageResponseDTO = PageResponseDTO.<BoardDTO>withAll().dtoList(boardDTOList)
                .total(count)
                .pageRequestDTO(pageRequestDTO)
                .build();

        log.info("pageResponse {}", pageResponseDTO.toString());
        return pageResponseDTO;
    }


    public BoardDTO getBoardById(Integer bid){
        return boardRepository.findOne(bid);
    }

    public void updateBoard(BoardDTO boardDTO){
        boardRepository.update(boardDTO);
    }

    public void deleteBoard(Integer bid){
        boardRepository.delete(bid);
    }


}
