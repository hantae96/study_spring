package com.example.toy.dto;

// 방명록을 보여줄때, 기존 객체는 writer_id를 쓰기 때문에 이름을 가져오기 위한 DTO

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BoardViewDTO {

    private Integer bid;
    private String title;
    private String content;
    private String writerName;
}
