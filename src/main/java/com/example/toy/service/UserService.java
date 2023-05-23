package com.example.toy.service;

import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.dto.UpdateDTO;
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

    public void register(InputDTO inputDTO){
        repository.save(inputDTO);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }
    public User findUserById(Integer userId){
        return repository.findById(userId);
    }

    public Integer findByName(String userName){
        return repository.findByName(userName);
    }


    public void updateUserData(UpdateDTO updateDTO){
        repository.update(updateDTO);
    }

    public void deleteUserData(Integer userId){
        repository.delete(userId);
    }
}
