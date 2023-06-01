package com.example.toy.repository.search;

import com.example.toy.domain.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemoSearch {
    Page<Memo> search1(Pageable pageable);

    Page<Memo> searchAll(String[] types, String keyword,Pageable pageable);
}
