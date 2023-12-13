package com.sh.mvc.common.filter;

import com.sh.mvc.member.model.entity.Member;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * annotation은 속성을 가질 수 있다.
 * -대표속성 하나는 value alias를 사용 할 수 있고, 생략도 가능하다..
 * -@WebFilter 어노테이션은 urlPatterns,value 동일..
 */
@WebFilter(urlPatterns = {
        //"/member/memberDetail",
        "/member/memberUpdate",
        "/member/memberDelete"

})
public class AuthenticationFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //인증여부검사
        HttpSession session  = request.getSession();
        Member loginMember = (Member) request.getSession().getAttribute("LoginMember");
        if(loginMember==null)
        {
            session.setAttribute("msg","로그인 후 사용 가능합니다.");
            //그리고 인덱스페이지로 강제이동
            response.sendRedirect(request.getContextPath()+"/");
            return;//조기리턴 이후 코드 실행되지않는다
        }
        //tip:redirect나 forward 이후에는 실행코드는 없어야 한다.


        super.doFilter(request, response, chain);
    }
}
