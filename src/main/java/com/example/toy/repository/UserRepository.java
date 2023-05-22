package com.example.toy.repository;

import com.example.toy.domain.User;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    String getNow();

    void save(String userName);

    void deleteAll();

    User findOne(Integer userId);

    List<User> findAll();
}
