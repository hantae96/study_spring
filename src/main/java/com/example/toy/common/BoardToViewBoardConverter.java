package com.example.toy.common;

import com.example.toy.dto.BoardDTO;
import com.example.toy.dto.BoardViewDTO;
import com.example.toy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class BoardToViewBoardConverter implements Converter<BoardDTO, BoardViewDTO> {

    @Autowired
    private final MemberService memberService;

    public BoardToViewBoardConverter(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public BoardViewDTO convert(BoardDTO boardDTO) {

        String findMemberName = memberService.getMemberById(boardDTO.getWriterId()).getMemberName();
        BoardViewDTO boardViewDTO = BoardViewDTO.builder().title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .writerName(findMemberName)
                .bid(boardDTO.getBid())
                .build();

        return boardViewDTO;
    }

}
