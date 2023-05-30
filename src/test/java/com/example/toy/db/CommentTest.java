package com.example.toy.db;

import com.example.toy.domain.Comment;
import com.example.toy.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentTest {

    private final CommentRepository repository;

    @Autowired
    public CommentTest(CommentRepository repository) {
        this.repository = repository;
    }

    @Test
    public void save(){
        Comment comment = new Comment();
        comment.setComment("댓글댓글");
        comment.setRefBoardId(10);
        comment.setWriter("lager96");
        repository.save(comment);
    }
    
    @Test
    public void findByBid(){
        Integer bid = 10;
        List<Comment> comments = repository.findByBoardId(10);
        for (Comment comment : comments) {
            System.out.println("comment.getComment() = " + comment.getComment());
        }
    }

}
