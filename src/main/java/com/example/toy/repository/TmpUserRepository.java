package com.example.toy.repository;

import com.example.toy.domain.User;
import com.example.toy.mapper.TmpMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TmpUserRepository implements UserRepository {

    private final TmpMapper mapper;

    @Autowired
    SqlSession sqlSession;
    @Autowired
    public TmpUserRepository(TmpMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String getNow() {
        return mapper.getNow();
    }

    @Override
    public void save(String userName) {
        mapper.save(userName);
    }

    @Override
    public void deleteAll() {
        mapper.deleteAll();
    }

    @Override
    public User findOne(Integer userId) {
        return mapper.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }


}