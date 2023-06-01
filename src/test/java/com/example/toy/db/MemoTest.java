package com.example.toy.db;


import com.example.toy.domain.Memo;
import com.example.toy.dto.MemoDTO;
import com.example.toy.dto.PageRequestDTO;
import com.example.toy.dto.PageResponseDTO;
import com.example.toy.repository.MemoRepository;
import com.example.toy.service.MemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoTest {

    @Autowired
    private MemoRepository memoRepository;
    @Autowired
    private MemoService memoService;

    @Test
    public void insert(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Memo memo = Memo.builder()
                    .title("제목")
                    .content("내용")
                    .writer("작성자")
                    .build();

            Memo result = memoRepository.save(memo);
        });
    }

    @Test
    public void select(){
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        Memo memo = result.orElseThrow();
        System.out.println(memo);
    }

    @Test
    public void update(){
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        Memo memo = result.orElseThrow();

        memo.change("바뀐 제목","바뀐 내용");
        memoRepository.save(memo);
        System.out.println(memo);
    }

    @Test
    public void paging(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
        System.out.println(result.getSize());
        System.out.println(result.getNumber());

        List<Memo> content = result.getContent();
    }

    @Test
    public void Search1(){
        Pageable pageable = PageRequest.of(1,10,Sort.by("mno").descending());
        memoRepository.search1(pageable);
    }

    @Test
    public void testSearchAll(){
        String[] types = {"t", "c", "w"};
        String keyword = "제목";

        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.searchAll(types, keyword, pageable);

        System.out.println(result.getContent());
    }


    @Test
    public void register(){
        System.out.println(memoService.getClass().getName());

        MemoDTO memo = MemoDTO.builder().title("제목 등록").content("내용 등록").writer("hantae").build();

        Long mno = memoService.register(memo);
    }

    @Test
    public void modify(){
        MemoDTO build = MemoDTO.builder().mno(101L).title("업데이트 됨 !").content("업데이트!!").build();
        memoService.modify(build);
    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().type("tcw").keyword("업데이트").page(1).size(10).build();
        PageResponseDTO<MemoDTO> responseDTO = memoService.list(pageRequestDTO);
        System.out.println(responseDTO);
    }
}
