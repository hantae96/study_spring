package com.example.toy.repository;

import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.dto.UpdateDTO;
import com.example.toy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements MariaRepository{


    private final UserMapper mapper;
    @Autowired
    public UserRepository(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void save(InputDTO inputDTO) {
        mapper.save(inputDTO);
    }

    @Override
    public User update(Integer userId, UpdateDTO updateDTO) {
        return null;
    }

    @Override
    public void delete(Integer userId) {

    }

    @Override
    public User findById(Integer userId) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }
}
