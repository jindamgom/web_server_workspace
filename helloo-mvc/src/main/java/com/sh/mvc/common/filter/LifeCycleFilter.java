package com.sh.mvc.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * filter의 생명주기~
 *
 * -서버 구동시 필터 객체를 생성함. 그 이후는 객체를 재사용(싱글턴 패턴)
 * 1.생성자 호출
 * 2.init 호출
 * 
 * 3.doFilter(전처리/후처리)
 * -Filter#doFilter(ServletRequest,servletResponse,FilterChain)이 먼저 호출
 * -HttpFilter#doFiiter(HttpServletRequest,HttpservletResponse,filterChain) 호출
 * 
 * 
 * 4.destroy 호출
 * </pre>
 */


//@WebFilter("/*")
public class LifeCycleFilter extends HttpFilter {

    public LifeCycleFilter()
    {
        System.out.println("LifeCycleFilter - 생성자");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        System.out.println("LifeCycleFilter - init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("LifeCycleFilter - doFilter ServletRequest ServletResponse chain");
        super.doFilter(request, response, chain);
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
         System.out.println("LifeCycleFilter - doFilter HttpServletRequest HttpServletResponse chain");
        super.doFilter(request, response, chain);
    }

    @Override
    public void destroy() {
        System.out.println("LifeCycleFilter - destroy");
        super.destroy();
    }
}
