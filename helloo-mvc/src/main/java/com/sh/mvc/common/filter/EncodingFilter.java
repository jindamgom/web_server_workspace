package com.sh.mvc.common.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 초기화 파라미터
 * servlet/filter 등에서 "환경변수"처럼 사용 가능하다.
 * web.xml에서 지정하거나, @WebFilter안에서 지정하면된다.
 */

//initParams:배열
@WebFilter(value = "/*",initParams = {
        @WebInitParam(name="encoding",value="utf-8")
})
public class EncodingFilter extends HttpFilter
{
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding=filterConfig.getInitParameter("encoding");//utf-8값이 담김
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //request.setCharacterEncoding("utf-8");

        //이렇게도 사용가능하다, 만약 관리할게 여러개라면 배열로 선언해서 사용해도 될 듯..
        request.setCharacterEncoding(encoding);
        System.out.println("utf-8 적용완료");
        super.doFilter(request, response, chain);
    }
}
