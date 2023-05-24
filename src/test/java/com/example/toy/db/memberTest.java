package com.example.toy.db;


import com.example.toy.domain.Member;
import com.example.toy.mapper.MemberMapper;
import com.example.toy.repository.MemberRepository;
import com.example.toy.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class memberTest {

    private final MemberMapper mapper;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    @Autowired
    public memberTest(MemberMapper mapper, MemberRepository memberRepository, MemberService memberService) {
        this.mapper = mapper;
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }



    @Test
    @DisplayName("회원 저장 테스트")
    public void save(){
        Member member = new Member();
        member.setMemberId("asepa");
        member.setPassword("1234");
        member.setMemberName("카리나");
        member.setDateOfBirth(LocalDate.now());

        mapper.save(member);
    }

    @Test
    @DisplayName("회원 정보 모두 가져오기")
    public void viewAll(){
        List<Member> all = memberRepository.findAll();
        for (Member member : all) {
            System.out.println("member = " + member.getMemberId());
            System.out.println("member = " + member.getMemberName());
        }
    }

    @Test
    @DisplayName("회원 아이디로 회원정보 가져오기")
    public void viewOne(){
        Member member = memberService.getMemberById("lager96");
        String password = member.getPassword();
        Assertions.assertThat(password).isEqualTo("lager96!");
    }

    @Test
    @DisplayName("회원 정보 수정하기")
    public void update(){
        Member member = new Member();
        member.setMemberId("asepa");
        member.setPassword("도깨비불123");
        member.setMemberName("카리나");
        member.setDateOfBirth(LocalDate.now());

        Integer update = memberRepository.update(member);
        Assertions.assertThat(update).isEqualTo(1);
    }

    @Test
    @DisplayName("회원 정보 삭제하기")
    public void delete(){
        Member member = new Member();
        member.setMemberId("삭제삭제");
        member.setPassword("도깨비불123");
        member.setMemberName("카리나");
        member.setDateOfBirth(LocalDate.now());
        memberRepository.save(member);

        memberRepository.delete("삭제삭제");

        Member 삭제삭제 = memberRepository.findOne("삭제삭제");
        Assertions.assertThat(삭제삭제).isNull();
    }
}
