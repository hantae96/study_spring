package com.example.toy.repository;

import com.example.toy.domain.Member;
import com.example.toy.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MemberRepository{

    private final MemberMapper mapper;
    private final SqlSession sqlSession;
    public void save(Member member){
//        mapper.save(member);
        sqlSession.insert("com.example.toy.mapper.MemberMapper.save", member);
    }

    public List<Member> findAll(){
        return sqlSession.selectList("findAll");
    }

    public Member findOne(String memberId){
        return sqlSession.selectOne("findOne", memberId);
    }

    public Integer update(Member member){
        return sqlSession.update("update", member);
    }

    public void delete(String memberId){
        sqlSession.delete("delete", memberId);
    }
}
