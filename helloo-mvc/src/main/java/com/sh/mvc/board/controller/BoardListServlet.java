package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
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

/**
 * member list 처럼 board list도 작성해본다.
 * 1.board-mapper.xml 생성
 * 2.mybatis-config.xml의 mapper 속성에 <mapper resource="mapper/board/board-mapper.xml"/> 추가
 * 3.컨텐츠/페이지바 적용 전에 db - board 테이블에 있는 모든 값 출력하기.
 * 
 */

@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("BoardListServlet do get");
        //1.사용자 입력값 가져오기
        //기본적으로 page에 1값을 주는 이유 페이지단 처리[아래에]하다가 null이 발생할 수 있으므로.
        int page = 1;
        int limit = 15;
        try
        {
            //값이 있으면 정수 변환, 값이 없으면 (null)이라 포맷에러 발생
            page  = Integer.parseInt(req.getParameter("page"));
            System.out.println("11111111111111111111페이징:"+page);
        }
        catch(NumberFormatException ignore) {}
        //에러발생 시 별 다른 조치 취하지 않고 기본값 1 사용

        //변경불가 immutable객체 , 요소로(key,value) null값 줄 수 없음.
        Map<String,Object> param = Map.of("page",page,"limit",limit);

        //2.업무로직
        //1)content영역 : 전체 조회 쿼리 + RowBounds | Top-n 분석 쿼리
        BoardService boardService = new BoardService();
        List<BoardVo> boards = boardService.findAll(param);
        req.setAttribute("boards",boards);
        //System.out.println(boards); //첨부파일 갯수까지 확인


        //2)pageBar 영역
        int totalCount = boardService.getTotalCount();
        String url = req.getRequestURI();
        String pagebar = HelloMvcUtils.getPagebar(page,limit,totalCount,url);
        req.setAttribute("pagebar",pagebar);


       //System.out.println(boards);
        req.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(req,resp);
    }
}