package com.sh.mvc.common.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre></pre>
 * Filter
 * -servlet 전후 처리를 담당하는 클래스..
 * -공통 코드를 관리 : 인코딩처리, 인증/인가처리, 응답파일 압축 등.....
 *
 *
 *
 * -filter class를 만드는 방법 두가지
 *  1.javax.servlet.Filter 인터페이스를 구현
 *   -doFilter(ServletRequest.ServletResponse,FilterChain) 오버라이드
 *   
 *  2.javax.servlet.http.HttpFilter클래스를 상속
 *   -doFilter(HttpServletRequest, HttpServletResponse,FilterChain) 오버라이드
 *   -ServletRequest , ServletResponse 부모타입을 상속한 HttpServletRequest, HttpServletResponse
 *   -down-casting 할 필요 없이 즉시 사용 가능 - 편리
 */

@WebFilter("/*") //모든 uri 필터링하기.
public class LogFilter extends HttpFilter implements Filter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리(요청 직후)
        String uri = request.getRequestURI();
        String method = request.getMethod();
//        System.out.println("전===========================================");
//        System.out.printf("%s %s\n",method,uri);
//        System.out.println("전-------------------------------------------");

        //filterChain:필터묶음 , 여러 필터를 그룹핑하여 관리.
        // - 다음 필터가 있는 경우 해당 filter#doFilter 호출
        // - 마지막 필터인 경우 Servlet을 호출.
        chain.doFilter(request,response);
        //super.doFilter(request,response,chain); //chain.doFilter(request,response)

        //후처리 (응답 직전)
//        System.out.println("후-------------------------------------------");
//        System.out.println(response.getStatus());
//
//        System.out.println("후-------------------------------------------");
//        System.out.println("");
    }
}
