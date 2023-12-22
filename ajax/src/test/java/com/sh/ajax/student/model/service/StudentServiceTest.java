package com.sh.ajax.student.model.service;


import com.sh.ajax.student.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * 테스트 항목
 * -fixtrue 자체 테스트
 * -학생 이름 검색 테스트
 * -"이" 검색 시 이가 들어가는 학생 모두 조회
 *
 *
 *
 * @ParameterizedTest
 * @ValueSource
 */

public class StudentServiceTest 
{
    //fixture
    StudentService studentServce;

    //첫 테스트 - 해당 fixture 객체가 존재하는지 아닌지 확인
    //@BeforeEach
    @BeforeEach
    public void beforeEach()
    {
        this.studentServce = new StudentService();
    }

    @DisplayName("StudentService 객체는 null이 아니다.")
    @Test
    public void test1()
    {
        assertThat(studentServce).isNotNull();
    }

    //두번째 테스트
    @DisplayName("존재하는 학생이 정상적으로 조회된다..")
    @ParameterizedTest
    @ValueSource(strings ={"이","진","한"})
    public void test2(String name) {

        assertThat(name).isNotNull();
        List<Student> students = studentServce.findByName(name);
        System.out.println(students);
        assertThat(students)
                .isNotNull()
                .allSatisfy((student)->{
                    assertThat(student.getId()).isNotNull();
                    assertThat(student.getName()).isNotNull();
                });

    }
}
