package com.example.toy.service;

import com.example.toy.domain.User;
import com.example.toy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TmpService {
    private final UserRepository userRepository;

    @Autowired
    public TmpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(String userName){
        userRepository.save(userName);
    }

    public User findById(Integer userId){
        return userRepository.findOne(userId);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
