package com.example.toy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordSaveDTO {
    private String title;
    private String content;
    private Integer userId;
}
