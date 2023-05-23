package com.example.toy.repository;

import com.example.toy.domain.Record;
import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.dto.RecordSaveDTO;
import com.example.toy.dto.UpdateDTO;
import com.example.toy.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordRepository{

    private final RecordMapper recordMapper;

    @Autowired
    public RecordRepository(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }


    public void save(RecordSaveDTO dto) {
        recordMapper.save(dto);
    }


    public void update(UpdateDTO updateDTO) {

    }


    public void delete(Integer userId) {

    }


    public User findById(Integer userId) {
        return null;
    }


    public List<Record> findAll(){
        return recordMapper.findAll();
    }
}
