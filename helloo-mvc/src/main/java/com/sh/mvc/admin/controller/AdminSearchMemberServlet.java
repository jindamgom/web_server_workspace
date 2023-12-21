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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@WebServlet("/admin/searchMember")
//public class AdminSearchMemberServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        /**
//         * mybatis에서는 식별자(컬럼명,테이블명 등)를 동적으로 작성할 수 있다. ${식별자}
//         * PreparedStatement에는 없는 기능..
//         *
//         * id 검색시
//         * select * from member where id like ?
//         *
//         * 이름 검색시
//         * select * from member where name like ?
//         *
//         * 이메일 검색시
//         * select * from member where email like ?
//         *
//         */
//
//        //1.사용자 입력값 가져오기
//        String searchType = req.getParameter("search-type");
//        String searchKeyword = req.getParameter("search-keyword");
//        Map<String, Object> param = new HashMap<>();
//        param.put("searchType",searchType);
//        param.put("searchKeyword",searchKeyword);
//        System.out.println(param); //{searchType=id, searchKeyword=gom}
//
//        //2.업무로직
//        MemberService memberService = new MemberService();
//        List<Member> members  = memberService.searchMember(param);
//        req.setAttribute("members", members);
//        //3.view 단 처리
//        //req.setAttribute("members",members);
//        req.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(req,resp);
//
//    }
//
//
//}

@WebServlet("/admin/searchMember")
public class AdminSearchMemberServlet extends HttpServlet {
    private MemberService memberService = new MemberService();

    /**
     * mybatis에서는 식별자(컬럼명, 테이블명)를 동적으로 작성할수 있다. ${식별자}
     * - (PreparedStatement에는 없음)
     *
     * select * from member where id like ?
     * select * from member where name like ?
     * select * from member where email like ?
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 사용자입력값 가져오기
        int page = 1; //기본 값
        int limit = 10; //한 페이지당 다룰 데이터수
        try
        {
            //값이 있으면 정수 변환, 값이 없으면 (null)이라 포맷에러 발생
            page  = Integer.parseInt(req.getParameter("page"));
        }
        catch(NumberFormatException ignore) {}
        //에러발생 시 별 다른 조치 취하지 않고 기본값 1 사용

        String searchType = req.getParameter("search-type");
        String searchKeyword = req.getParameter("search-keyword");
        
        Map<String, Object> param = new HashMap<>();
        param.put("searchType", searchType);
        param.put("searchKeyword", searchKeyword);
        param.put("page",page);
        param.put("limit",limit);
        System.out.println(param);

        // 2. 업무로직
        // content영역
        List<Member> members = memberService.searchMember(param);
        System.out.println(members);
        req.setAttribute("members", members);

    
        //pagebar 영역
        //멤버서치서블릿이라 검색조건에 맞는 총 회원수를 넘겨야함.

        int totalCount = memberService.getToTalCount(param);
        String url = req.getRequestURI()+"?search-type="+searchType+
                "&search-keyword="+searchKeyword;

        String pagebar = HelloMvcUtils.getPagebar(page,limit,totalCount,url);
        req.setAttribute("pagebar",pagebar);

        // 3. view단처리
        req.getRequestDispatcher("/WEB-INF/views/admin/memberList.jsp").forward(req, resp);
    }
}