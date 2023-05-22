package com.example.toy.service;

import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.repository.MariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final MariaRepository repository;

    @Autowired
    public UserService(MariaRepository repository) {
        this.repository = repository;
    }

    // 유저 등록
    public void register(InputDTO inputDTO){
        repository.save(inputDTO);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }
    // 유저 정보 수정
    // 유저 삭제
    // 유저 찾기 제공
}
