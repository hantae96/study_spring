package com.example.toy.mapper;

import com.example.toy.domain.Record;
import com.example.toy.domain.User;
import com.example.toy.dto.InputDTO;
import com.example.toy.dto.RecordSaveDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    void save(RecordSaveDTO recordSaveDTO);
    List<Record> findAll();
    Record findById(Integer userId);
}
