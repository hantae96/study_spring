package com.example.toy.repository;
import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.dto.UpdateDTO;
import java.util.List;

public interface MariaRepository {

    void save(InputDTO inputDTO);

    void update(UpdateDTO updateDTO);

    void delete(Integer userId);

    User findById(Integer userId);

    List<User> findAll();

    Integer findByName(String userName);
}
