package com.example.toy.mapper;

import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.dto.UpdateDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    void save(InputDTO inputDTO);
    List<User> findAll();
    User findById(Integer userId);

    void update(UpdateDTO updateDTO);

    void delete(Integer userId);
}
