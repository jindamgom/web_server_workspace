package com.sh.ajax.celeb.model.dao;


import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.celeb.model.entity.Type;
import com.sh.ajax.student.model.service.StudentService;
import org.apache.catalina.mbeans.ContextResourceLinkMBean;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.stream.Stream;

import static com.sh.ajax.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

//1226이번엔 dao테스트를 만들어서 테스트해보자!!
public class CelebDaoTest
{
    CelebDao celebDao;
    SqlSession session;

    //BeforeEach : 어노테이션을 명시한 메서드는 테스트 메서드 실행 전에 무조건 실행된다
    @BeforeEach
    public void setUp() //매번
    {
        this.celebDao = new CelebDao();
        this.session = getSqlSession();
    }

    @AfterEach
    public void tearDown()
    {
        this.session.rollback();//test라 실제 반영하지 않기 위해 롤백처리 해준다.
        this.session.close();//세션은 사용후 종료
    }

    @DisplayName("CelebDao,SqlSession은 null이 아니다.")
    @Test
    public void test0()
    {
        assertThat(celebDao).isNotNull();
        assertThat(session).isNotNull();
    }

    @DisplayName("Celeb 전체 조회")
    @Test
    public void test1()
    {
    //celeb 전체 조회
        //given
        //when
        List<Celeb> celebs = celebDao.findByAll(session);
        //then
        assertThat(celebs)
                .isNotNull()
                //요소 하나하나 테스트
                .allSatisfy((mycelebs) -> {
                    assertThat(mycelebs.getId()).isNotZero();
                    assertThat(mycelebs.getName()).isNotNull();
                    assertThat(mycelebs.getProfile()).isNotNull();
                    assertThat(mycelebs.getType()).isNotNull();
                });

    }

    @DisplayName("Celeb 존재하는 한 건 조회,pk인 id로 조회하기")
    @Test
    public void test2()
    {
        long id = 1;
        Celeb celebOne = celebDao.findById(session,id);
        assertThat(celebOne).isNotNull();

    }

    @Disabled
    @DisplayName("Celeb 존재하지 않는 한 건 조회")
    @Test
    public void test3()
    {
        //현재 1~11까지 있음
        long id = 100;
        Celeb celebOne = celebDao.findById(session,id);
        assertThat(celebOne).isNotNull();
    }

    //long id, String name, String profile, Type type;
    @DisplayName("Celeb 등록")
    @Test
    public void test4()
    {
        long id=77;
        String name = "농담곰";
        String profile = "농담곰.png";
        Celeb newbie = new Celeb();
        newbie.setId(id);
        newbie.setName(name);
        newbie.setProfile(profile);
        newbie.setType(Type.COMEDIAN);

        int result = celebDao.insertCeleb(session,newbie);
        assertThat(result).isGreaterThan(0);
        assertThat(newbie.getId()).isNotNull().isNotZero();
    }

    @DisplayName("Celeb 수정")
    @Test
    public void test5()
    {

    }

    @DisplayName("Celeb 삭제")
    @Test
    public void test6()
    {
        long id = 1;
        int result = celebDao.deleteCeleb(session,id);
        assertThat(result).isGreaterThan(0);
        //assertThat(newbie.getId()).isNotNull().isNotZero();
    }


    public static Stream<Long> celebIdProvider()
    {
        CelebDao celebDao = new CelebDao();
        SqlSession session = getSqlSession();
        List<Celeb> celebs = celebDao.findByAll(session);
        return celebs.stream() //Stream<Celeb>
                .filter((celeb)-> celeb.getId()<=5)
                .map((celeb->celeb.getId()));//celeb->long으로 변환 Stream<Long>
    }
}
