package com.sh.mvc.common;


import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//test 클래스는 원본 클래스 뒤에 Test를 붙여서 구분한다.
public class SqlSessionTemplateTest {


    @DisplayName("정상적인 SqlSession객체를 반환한다.")
    @Test
    public void test()
    {
        SqlSession sqlSession = getSqlSession();
        assertThat(sqlSession).isNotNull();
    }


}
