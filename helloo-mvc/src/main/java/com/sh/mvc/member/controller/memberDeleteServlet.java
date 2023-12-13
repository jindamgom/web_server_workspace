package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/memberDelete")
public class memberDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        System.out.println("회원탈퇴 / memberDeleteServlet...");

        //1.인코딩 설정
        req.setCharacterEncoding("utf-8");

        //2.현재 session - loginMember의 id값확인하기
        HttpSession session = req.getSession();
        Member loginMember = (Member)req.getSession().getAttribute("loginMember"); //return type:object라 앞에 member 형변환
        String id = loginMember.getId(); //이런식으로도 ..가져올 수 있다!
        System.out.println("현재 session - loginMember의 id: "+ id);


        //3.업무로직(탈퇴처리)
        MemberService memberService = new MemberService();
        int result = memberService.deleteMember(id);


        System.out.println("회원탈퇴 결과 : "+result);

        //3.5업무로직-세션해제
        //create 매개변수 true(기본값) : 세션이 있으면 세션반환, 없으면 새로 생성해서 반환
        //false : 세션이 있으면 세션반환 ,없으면 null 반환
        if(session !=null)
            session.invalidate();

        //탈퇴 메세지를 주고싶다면 메시지용 세션을 만들어주고..
        session = req.getSession();
        session.setAttribute("msg",id+"회원님의 탈퇴가 완료되었습니다.😥");


        //4.index 페이지로 이동(url 변경)
        resp.sendRedirect(req.getContextPath()+"/");
    }
}