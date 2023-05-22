package com.example.toy;

import com.example.toy.domain.User;
import com.example.toy.repository.UserRepository;
import com.example.toy.service.TmpService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DbSaveTest {

    private final UserRepository repository;
    private final TmpService tmpService;

    @Autowired
    public DbSaveTest(UserRepository repository, com.example.toy.service.TmpService tmpService) {
        this.repository = repository;
        this.tmpService = tmpService;
    }

//    @BeforeEach
    public void clear(){
        repository.deleteAll();
    }

    @Test
    public void save(){
        tmpService.register("지젤");
    }

    @Test
    public void printAll(){
        List<User> all = tmpService.findAll();
        for (User user : all) {
            System.out.println(user.getUserId() + " " + user.getUserName());
        }
    }

//    @AfterEach
    public void findById(){
        Integer userId = 15;
        User findUser = tmpService.findById(userId);
    }


}
