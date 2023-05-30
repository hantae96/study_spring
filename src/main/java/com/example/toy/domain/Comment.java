package com.example.toy.domain;

import lombok.Data;

import javax.annotation.sql.DataSourceDefinition;

@Data
public class Comment {
    private Integer cid;
    private String comment;
    private Integer refBoardId;
    private String writer;
}
