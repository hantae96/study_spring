package com.example.toy.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class BoardDTO {

    private Integer bid;
    private String title;
    private String content;
    private String writerId;
}
