package com.example.toy.repository;

import com.example.toy.domain.Memo;
import com.example.toy.repository.search.MemoSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemoRepository extends JpaRepository<Memo,Long>, MemoSearch {
    @Query(value = "select now()",nativeQuery = true)
    String getTime();
}
