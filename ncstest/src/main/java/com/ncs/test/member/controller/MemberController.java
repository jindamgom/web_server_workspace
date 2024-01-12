package com.ncs.test.member.controller;

import com.ncs.test.member.model.service.MemberService;
import com.ncs.test.member.model.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/memberLogin")
public class MemberController extends HttpServlet
{
    MemberService memberService = new MemberService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost 길동이 로그인하러");

        /*
        * <label> ID : </label> <input type="text" name="memberId"> <br>
        <label>PWD : </label> <input type="password" name="memberPwd"> <br>
        * */
        HttpSession session =req.getSession();
        String memberId= req.getParameter("memberId"); //아이디
        String memberPwd= req.getParameter("memberPwd"); //비밀번호
        System.out.println("입력한 아이디값:"+memberId);
        System.out.println("입력한 패스워드값:"+memberPwd);
        Member member = new Member();
        member.setMemberId(memberId);
        member.setMemberPwd(memberPwd);

        member = memberService.loginMember(member);

        System.out.println("로그인결과~"+member);
        if(member!=null)
        {
            session.setAttribute("loginMember", member);
        }
        else {
            session.setAttribute("loginFail", "아이디나 비밀번호를 다시 확인해주세요.😑");
        }
        resp.sendRedirect(req.getContextPath()+"/");

    }
}