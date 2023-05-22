package com.example.toy.mapper;


import com.example.toy.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface TmpMapper {
    String getNow();
    void save(@Param("userName") String userName);

    void deleteAll();

    User findById(@Param("id") Integer userId);

    List<User> findAll();
}
