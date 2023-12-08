package com.sh.mybatis.member.model.service;


import com.sh.mybatis.member.model.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    MemberService memberService; //test 대상(fixture)

    @BeforeEach
    public void beforeEach()
    {
        this.memberService = new MemberService();
    }

    //첫 테스트는 해당 객체가 존재 하는지 아닌지 부터 확인하는 것.
    @DisplayName("MemberService 객체는 null이 아니다.")
    @Test
    public void test1(){
        assertThat(memberService).isNotNull();

    }

    //두번째 테스트
    @DisplayName("존재하는 회원이 정상적으로 조회된다..")
    @Test
    public void test2()
    {

        Member member = memberService.findById("abcde");
        System.out.println(member);
        //notnull속성 컬럼인 것들은 이렇게 조회해본다..
        assertThat(member.getId()).isNotNull();
        assertThat(member.getPassword()).isNotNull();
        assertThat(member.getName()).isNotNull();
        assertThat(member.getRole()).isNotNull();

    }

}
