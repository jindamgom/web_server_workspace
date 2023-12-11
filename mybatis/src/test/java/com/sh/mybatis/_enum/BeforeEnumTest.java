package com.sh.mybatis._enum;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BeforeEnumTest {
    public static final String TYPE_MEMBER = "M";
    public static final String TPYE_ADMIN = "A";
    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";


    public class Member {
        String id;
        String type; //M,A
        String gender; //M,F

        public Member(String id, String type, String gender) {
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
        Member member = new Member("sinsa",TYPE_MEMBER,GENDER_FEMALE);
        assertThat(member.type).isEqualTo(TYPE_MEMBER);
        assertThat(member.gender).isEqualTo(GENDER_FEMALE);

        Member admin = new Member("damgom",TPYE_ADMIN,GENDER_MALE);
        assertThat(admin.type).isEqualTo(TPYE_ADMIN);
        assertThat(admin.gender).isEqualTo(GENDER_MALE);
    }
}
