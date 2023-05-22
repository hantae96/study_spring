package com.example.toy.db;

import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class dbtest {

    private final UserRepository repository;

    @Autowired
    public dbtest(UserRepository repository) {
        this.repository = repository;
    }

    @Test
    @DisplayName("저장 테스트")
    public void saveTest(){
        InputDTO inputDTO = new InputDTO();
        inputDTO.setUserName("수지");
        inputDTO.setSex("여");
        inputDTO.setPassword("1234");
        repository.save(inputDTO);
    }

    @Test
    @DisplayName("모두 보여주기")
    public void viewAll(){
        List<User> all = repository.findAll();
        for (User user : all) {
            System.out.println("user.getUserName() = " + user.getUserName());
        }
    }
}
