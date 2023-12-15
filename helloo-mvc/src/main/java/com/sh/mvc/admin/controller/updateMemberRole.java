package com.sh.mvc.admin.controller;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/updateMemberRole")
public class updateMemberRole extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do post로 권한을 변경한다.....");

        String id = req.getParameter("id");
        String _role = req.getParameter("role");
        Role role = Role.valueOf(_role);

        Member member = new Member();
        member.setId(id);
        member.setRole(role);
        System.out.println(id+"/"+role);

        MemberService memberService = new MemberService();

        int result = memberService.updateMemberRole(member);

        //리다이렉트 후에 사용자 피드백
        req.getSession().setAttribute("msg","회원 권한을 성공적으로 업데이트 했습니다.");

        //리다이렉트
        resp.sendRedirect(req.getContextPath()+"/admin/memberList");

    }
}