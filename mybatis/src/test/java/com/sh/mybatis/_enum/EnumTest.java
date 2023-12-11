package com.sh.mybatis._enum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EnumTest {

    public enum Type
    {
        M,A;
    }
    public enum Gender
    {
        M,F;
    }

    public class Member {
        String id;
        Type type; //M,A
        Gender gender; //M,F

        public Member(String id, Type type, Gender gender) {
            this.id = id;
            this.type = type;
            this.gender = gender;
        }
    }


    //상수 변수를 사용하면 전혀 다른 의미로 사용된 변수인데도
    //변수의 값이 같으면 유효성검증에서 이상이 없다 판단
    //
    @Test
    public void test1()
    {
//        Member member = new Member("sinsa",Gender.M,Gender.F); //컴파일오류
        Member member = new Member("sinsa",Type.M,Gender.F);
        assertThat(member.type).isEqualTo(Type.M);
        assertThat(member.gender).isEqualTo(Gender.F);

        Member admin = new Member("damgom",Type.A,Gender.M);
        assertThat(admin.type).isEqualTo(Type.A);
        assertThat(admin.gender).isEqualTo(Gender.M);
    }


    @DisplayName("Enum객체는 하나만 만들어서 공유된다..")
    @Test
    public void test2(){
        Type M1 = Type.M;
        Type M2 = Type.M;
        assertThat(M1).isEqualTo(M2);
        assertThat(M1==M2).isTrue();
    }
    
    @DisplayName("Enum을 String으로 변환")
    @Test
    public void test3()
    {
        Gender F = Gender.F;
        String f = F.name(); //"F"
        assertThat(f)
                .isEqualTo("F")
                .isEqualTo(Gender.F.name());
    }

    @DisplayName("String을 Enum으로 변환")
    @Test
    public void test4()
    {
        Gender M = Gender.valueOf("M");
        assertThat(M)
                .isEqualTo(Gender.M);
    }
}
