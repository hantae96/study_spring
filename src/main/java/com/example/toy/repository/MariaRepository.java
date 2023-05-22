package com.example.toy.repository;
import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.dto.UpdateDTO;
import java.util.List;

public interface MariaRepository {

    void save(InputDTO inputDTO);

    User update(Integer userId, UpdateDTO updateDTO);

    void delete(Integer userId);

    User findById(Integer userId);

    List<User> findAll();

}
