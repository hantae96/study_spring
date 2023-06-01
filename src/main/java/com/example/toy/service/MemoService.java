package com.example.toy.service;

import com.example.toy.domain.Memo;
import com.example.toy.dto.MemoDTO;
import com.example.toy.dto.PageRequestDTO;
import com.example.toy.dto.PageResponseDTO;
import com.example.toy.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoService {
    private final ModelMapper modelMapper;
    private final MemoRepository memoRepository;

    public Long register(MemoDTO memoDTO){
        Memo memo = modelMapper.map(memoDTO, Memo.class);
        Long mno = memoRepository.save(memo).getMno();

        return mno;
    }

    public MemoDTO readOne(Long mno){
        Optional<Memo> result = memoRepository.findById(mno);
        Memo memo = result.orElseThrow();
        MemoDTO memoDTO = modelMapper.map(memo, MemoDTO.class);
        return memoDTO;
    }

    public void modify(MemoDTO memoDTO){
        Optional<Memo> result = memoRepository.findById(memoDTO.getMno());
        Memo memo = result.orElseThrow();

        memo.change(memoDTO.getTitle(), memo.getContent());
        memoRepository.save(memo);
    }

    public void remove(Long mno){
        memoRepository.deleteById(mno);
    }

    public PageResponseDTO<MemoDTO> list(PageRequestDTO pageRequestDTO){
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("mno");
        Page<Memo> result = memoRepository.searchAll(types, keyword, pageable);

        List<MemoDTO> dtoList = result.getContent().stream().map(memo -> modelMapper.map(memo, MemoDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<MemoDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalPages())
                .build();
    }
}
