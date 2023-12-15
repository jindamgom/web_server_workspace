package com.sh.mvc.common.filter;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 관리자 권한 필터
 * -authorization 권한 : 인증받은 사용자가 이 서비스를 이용할 수 있는지 체크..
 * -검사할 url : /admin/*
 * -인증 확인 및 로그인된 사용자의 권한을 검사.. Role.A인지 아닌지..
 */

@WebFilter("/admin/*")
public class AuthorizationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //로그인을 했는지?
        //로그인을 했다면 관리자 권한인지 확인한다..
        HttpSession session = request.getSession();
        //getAttribute는 object를 반환하기때문에 Member클래스로 다운캐스팅해준다..
        Member loginMember = (Member)session.getAttribute("loginMember");
        if(loginMember == null||loginMember.getRole()!=Role.A)
        {
            session.setAttribute("msg", "관리자만 사용 가능합니다.");
            response.sendRedirect(request.getContextPath() + "/");
            return; // redirect/forward 이후 실행코드는 없어야 한다.
        }
        super.doFilter(request, response, chain);

    }
}


