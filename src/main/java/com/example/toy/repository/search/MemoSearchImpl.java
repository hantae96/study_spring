package com.example.toy.repository.search;

import com.example.toy.domain.Memo;
import com.example.toy.domain.QMemo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class MemoSearchImpl extends QuerydslRepositorySupport implements MemoSearch {

    public MemoSearchImpl() {
        super(Memo.class);
    }

    @Override
    public Page<Memo> search1(Pageable pageable) {
        // 자바 코드를 JPQL로 만들기 위한 객체 생성
        QMemo memo = QMemo.memo;
        // from memo
        JPQLQuery<Memo> query = from(memo);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(memo.title.contains("11"));
        booleanBuilder.or(memo.content.contains("11"));

        // from memo where title LIKE "%1%";

        query.where(booleanBuilder);
        query.where(memo.mno.gt(0L));

        this.getQuerydsl().applyPagination(pageable,query);

        // 커밋
        List<Memo> list = query.fetch();
        long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Memo> searchAll(String[] types, String keyword, Pageable pageable) {
        QMemo memo = QMemo.memo;
        JPQLQuery<Memo> query = from(memo);

        if((types != null && types.length > 0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String type : types){
                switch (type){
                    case "t":
                        booleanBuilder.or(memo.title.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(memo.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(memo.writer.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
        }

        query.where(memo.mno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);
        
        
        List<Memo> list = query.fetch();
        long count = query.fetchCount();


        return new PageImpl<>(list,pageable,count);
    }
}
