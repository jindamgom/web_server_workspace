package com.sh.mvc.member.model.service;
import com.sh.mvc.member.model.entity.Gender;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

//1212 명시적으로 테스트들의 순서 지정하는 방법
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberServiceTest {

    MemberService memberService; //test 대상(fixture)

    @BeforeEach
    public void beforeEach() {
        this.memberService = new MemberService();
    }

    //첫 테스트는 해당 객체가 존재 하는지 아닌지 부터 확인하는 것.

    /**
     * myBatis는 ResultSet의 데이터를 vo클래스개체로 자동변환한다.
     * 컬럼명과 필드 명이 일치 해야한다.(언더스코어->카멜케이싱) mapUnderscoreToCamelCase
     * varchar2 / char <-> String
     * number          <-> int/double
     * date            <-> java.util.Date(기본값), java.time.localDate
     * number(0 or 1)  <-> boolean
     * varchar2 / char <-> Enum#name
     */
    @DisplayName("MemberService 객체는 null이 아니다.")
    @Test
    public void test1() {
        assertThat(memberService).isNotNull();

    }

    //두번째 테스트
    @DisplayName("존재하는 회원이 정상적으로 조회된다..")
    @Test
    public void test2() {

        Member member = memberService.findById("abcde");
        System.out.println(member);
        //notnull속성 컬럼인 것들은 이렇게 조회해본다..
        assertThat(member.getId()).isNotNull();
        assertThat(member.getPassword()).isNotNull();
        assertThat(member.getName()).isNotNull();
        assertThat(member.getRole()).isNotNull();

    }

    //1211

    @DisplayName("존재하지 않는 회원은 Null이 반환되어야 한다.")
    @Test
    public void test3() {
        //imNotUser이라는 존재하지 않는 회원 id로 조회를 시도한다.
        Member member = memberService.findById("imNotUser");
        assertThat(member).isNull();
    }


    @DisplayName("회원 전체 조회")
    @Test
    public void test4() {
        List<Member> members = memberService.findAll();
        //반환타입은 리스트이다.
        assertThat(members)
                .isNotNull()
                .isNotEmpty();
        //Consumer 타입 람다식:매개변수가 하나 있고, 리턴타입은 없음.
        members.forEach((member) ->
        {
            System.out.println(member);
            assertThat(member.getId()).isNotNull();
            assertThat(member.getPassword()).isNotNull();
            assertThat(member.getName()).isNotNull();
            assertThat(member.getRole()).isNotNull();
        });
    }

    @DisplayName("회원 이름 검색")
    @Test
    public void test5() {
        String keyword = "무개";
        List<Member> members = memberService.findByName(keyword);
        assertThat(members)
                .isNotNull()
                .isNotEmpty();
        members.forEach((member) ->
                assertThat(member.getName()).contains(keyword));
    }

    //성별 검색
    @DisplayName("성별 검색")
    @Test
    public void test6() {
        String gender = "F";
        List<Member> members = memberService.findByGender(gender);
        assertThat(members)
                .isNotNull()
                .isNotEmpty();
        members.forEach((member) ->
                //gender enum 객체의 실제 값인 name을 꺼내온다..String
                //assertThat(member.getGender().name()).isEqualTo(gender));
                //혹은 이렇게 enum타입비교 String->enum

                assertThat(member.getGender()).isEqualTo(Gender.valueOf(gender)));
    }
    @Disabled
    @Order(1)
    @DisplayName("회원 가입")
    @Test
    public void test7() {

        String id = "honggd";
        String name = "홍길동";
        String password = "1234";

        Member member =
                new Member(id, password, name, Role.U, Gender.M,
                        LocalDate.of(1999, 4, 1),
                        "hhh@naver.com", "01012341234", Arrays.asList("낮잠자기", "밥먹기"),
                        0, null);

        int result = memberService.insertMember(member);
        assertThat(result).isEqualTo(1);//가입성공시 1:성공

        Member member2 = memberService.findById(id);//아이디확인
        assertThat(member2).isNotNull();//회원가입성공확인
        assertThat(member2.getId()).isEqualTo(id);
        assertThat(member2.getPassword()).isEqualTo(password);
        assertThat(member2.getName()).isEqualTo(name);

    }
    @Disabled
    @Order(2)
    @DisplayName("회원 가입 시 오류 체크")
    @Test
    public void test8() {

        Member member =
                new Member("nongdamgom", null, "농담곰", Role.U, Gender.M,
                        LocalDate.of(1999, 4, 1),
                        "hhh@naver.com", "01012341234", Arrays.asList("낮잠자기", "밥먹기"),
                        0, null);

        Throwable th = catchThrowable(()->
        {
            int result = memberService.insertMember(member);
        });

        //오류가 던져졌는지 확인
        assertThat(th).isInstanceOf(Exception.class);

    }
    @Disabled
    @Order(3)
    @DisplayName("회원 정보 수정")
    @Test
    public void test9()
    {
        //given 주어진 상황 작성
        String id = "honggd";
        //일단, honggd란 id를 가진 멤버를 조회한다.
        Member member = memberService.findById(id);
        
        
        //when 업무로직 작성
        String newName = member.getName() +"길동"; //홍길동홍길동

        Gender newGender = null;
        LocalDate newBirthday = LocalDate.of(2000,1,30);
        String newEmail = "newHonggd@daum.net";
        String newPhone = "01099991111";

        member.setGender(newGender);
        member.setBirthday(newBirthday);
        member.setEmail(newEmail);
        member.setPhone(newPhone);

        member.setName(newName);//새 이름으로 set해줌
        int result = memberService.updateMember(member);
        assertThat(result).isGreaterThan(0);


        //then 검증코드
        //다시 멤버 조회
        Member member2 = memberService.findById(id);
        assertThat(member2.getName()).isEqualTo(newName);
        assertThat(member2.getGender()).isEqualTo(newGender);
        assertThat(member2.getBirthday()).isEqualTo(newBirthday);
        assertThat(member2.getEmail()).isEqualTo(newEmail);
        assertThat(member2.getPhone()).isEqualTo(newPhone);
    }

    @Disabled
    @Order(4)
    @DisplayName("회원 비밀번호 수정하기")
    @Test
    public void test10()
    {
        String id = "honggd";
        //일단, honggd란 id를 가진 멤버를 조회한다...
        Member member = memberService.findById(id);

        //새 패스워드를 해당 멤버 객체에 set한다.
        String newPassword = "9999";
        member.setPassword(newPassword);

        int result = memberService.updateMember(member);
        assertThat(result).isGreaterThan(0);

        //다시 멤버 조회를 해서 newPassword와 getPassword가 일치하는지 확인.
        Member member2 = memberService.findById(id);
        assertThat(member2.getPassword()).isEqualTo(newPassword);
    }

    @Disabled
    @Order(5)
    @DisplayName("회원 권한 수정")
    @Test
    public void test11()
    {
        String id = "honggd";
        //일단, honggd란 id를 가진 멤버를 조회한다...
        Member member = memberService.findById(id);

        //새 권한
        Role newRole = Role.A;
        member.setRole(newRole);

        int result = memberService.updateMember(member);
        assertThat(result).isGreaterThan(0);

        //다시 멤버 조회를 해서 newPassword와 getPassword가 일치하는지 확인.
        Member member2 = memberService.findById(id);
        assertThat(member2.getRole()).isEqualTo(newRole);
        //String끼리 비교할땐 아래
        assertThat(member2.getRole().name()).isEqualTo(newRole.name());
    }


    @Disabled
    @Order(6)
    @DisplayName("회원 삭제")
    @Test
    public void test12()
    {
        //Delete from member where id="honggd";
        String id = "honggd";
        Member member = memberService.findById(id);
        assertThat(member).isNotNull();

        int result = memberService.deleteMember(id);
        assertThat(result).isGreaterThan(0);
        
        //삭제 후 조회
//        Member member2 = memberService.findById(id);
//        assertThat(result).isLessThan(0);

    }

}