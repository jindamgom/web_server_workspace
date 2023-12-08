package com.sh.mybatis;


import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit 테스트 구성요소
 * 1. fixture method : 매 테스트 전후작업
 *    -모든 테스트는 독립적으로 진행된다(매번 새로운 테스트 객체 생성 후 진행) new AppTest().test1() , new AppTest().test2()..
 *    -@BeforeAll,@AfterAll (static)
 *    -@BeforeEach, @AfterEach
 *
 * 2. 단정문(assertion)
 *    -코드 실행결과는 이 것이다! 라고 단정.
 *    -assertThat(....)
 *    -assertNotNull(....)
 *    -...
 *    
 * 3. TestRunner : 테스트 주체
 *
 *
 */
public class AppTest {
    App app = new App();

    //fixture 메소드..
    @BeforeAll
    public static void beforeAll()//static
    {
        System.out.println("beforeAll");
    }

    @AfterAll
    public static void afterAll()//static
    {
        System.out.println("afterAll");
    }

    @BeforeEach
    public void beforeEach()//non-static
    {
        System.out.println("beforeEach");
        this.app=new App(); //새 객체 생성
    }

    @AfterEach
    public void afterEach()//non-static
    {
        System.out.println("afterEach");
        //자원 해제
    }



    @DisplayName("App#sum메소드 - 두수의 합을 반환하는지..테스트")
    @Test
    public void test(){
        System.out.println("test1");
        //fail("구현 예정..."); //무조건 실패
        int result = app.sum(10,20);
        assertThat(result).isEqualTo(30);//결과값은 30이여야한다고 정함.

        result = app.sum(30,5);
        assertThat(result).isEqualTo(35);
        
    }
    
    //test 접근제어자는 default 이상이여야 함(private x)
    @DisplayName("App#ramdom은 1~100사이의 정수를 반환하는 지 테스트")
    @Test
    public void test2()
    {
        System.out.println("test2");
        //1과 같거나 크고~ 100과 같거나 작아야 한다
        int n = app.random();
        assertThat(n)
                .isGreaterThanOrEqualTo(1)
                .isLessThanOrEqualTo(100);
    }
}
