package com.example.toy.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Record {
    private Integer record_id;
    private String title;
    private String content;
    private Integer user_id;
}
