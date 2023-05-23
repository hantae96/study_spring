package com.example.toy.db;

import com.example.toy.domain.Record;

import com.example.toy.dto.RecordSaveDTO;
import com.example.toy.mapper.RecordMapper;
import com.example.toy.mapper.UserMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class recordTest {

    private final RecordMapper recordMapper;
    private final UserMapper userMapper;

    @Autowired
    public recordTest(RecordMapper recordMapper, UserMapper userMapper) {
        this.recordMapper = recordMapper;
        this.userMapper = userMapper;
    }

    @Test
    @DisplayName("일기 저장 테스트")
    public void save(){
        RecordSaveDTO dto = new RecordSaveDTO();
        dto.setTitle("테스트 일기장 제목");
        dto.setContent("내용입니다!");
        dto.setUserId(61);

        recordMapper.save(dto);
    }
    
    @Test
    public void printAll(){
        List<Record> records = recordMapper.findAll();
        for (Record record : records) {
            System.out.println("record.getUserId() = " + record.getUser_id());
        }
    }

    @Test
    public void find(){
        Record record = recordMapper.findById(61);
        System.out.println(record.getContent());
    }
}
