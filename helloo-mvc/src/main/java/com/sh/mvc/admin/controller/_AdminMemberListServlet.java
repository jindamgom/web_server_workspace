package com.sh.mvc.admin.controller;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 1215
 * 페이징
 *
 * 1.content
 * - page : 현재 페이지
 * - limit : 한 페이지당 표시할 개체 수
 *
 *
 * 2.pagebar
 * - page : 현재 페이지
 * - limit : 한 페이지당 표시할 개체 수
 * - totalContent : 전체 개체수
 * - totalPage : 전체 페이지수
 * - pagebarSize : 페이지바의 숫자개수
 * - pageNo : 페이지 증감변수
 * - pagebarStart | pagebarEnd : 페이지 증감변수의 범위
 * - url : 요청 url
 *
 *
 *
 * </pre>
 */

//@WebServlet("/admin/memberList")
public class _AdminMemberListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //1.사용자 입력값 가져오기
        int page = 1; //기본 값 
        int limit = 10; //한 페이이지당 다룰 데이터수
        try
        {
            //값이 있으면 정수 변환, 값이 없으면 (null)이라 포맷에러 발생
            page  = Integer.parseInt(req.getParameter("page"));
        }
        catch(NumberFormatException ignore) {}
        //에러발생 시 별 다른 조치 취하지 않고 기본값 1 사용

        Map<String,Object> param = Map.of("page",page,"limit",limit);
        System.out.println(param);


        //2.업무로직
        //db에 저장된 회원 리스트 가져오기..findAll
        
        //1)content영역 : 전체 조회 쿼리 + RowBounds | Top-n 분석 쿼리
        MemberService memberService = new MemberService();
        List<Member> members = memberService.findAll(param);


        //2)pageBar 영역
        int totalCount = memberService.getToTalCount();
        String url = req.getRequestURI();
        String pagebar = HelloMvcUtils.getPagebar(page,limit,totalCount,url);
        req.setAttribute("pagebar",pagebar);





        //3.view 단 처리
        req.setAttribute("members",members);
        req.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(req,resp);

    }


}