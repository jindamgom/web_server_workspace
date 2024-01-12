package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.exception.BoardException;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.common.HelloMvcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * XSS 공격
 * -Cross-site Scripting 공격
 * -"악성 스크립트를 웹페이지에 삽입"해서 클라이언트 개인정보를 탈취하는 공격법
 * -script태그로 구성된 내용을 필터링 없이 화면에 출력할 때 취약할 수 있다...
 *
 * 해결법
 * -Escpae Html처리를 통해서 사용자입력값이 html요소로 작동하지 못하도록 막기.
 */

@WebServlet("/board/BoardDetail")
public class BoardDetailServlet extends HttpServlet {

    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.사용자 입력값 처리
        try {
            long id = Long.parseLong(req.getParameter("id"));
            System.out.println("현재 게시글 번호:"+id);

            //2.업무로직
            //조회수 관련 처리
            Cookie[] cookies = req.getCookies();
            List<String> boardCookieValues = getBoardCookieValues(cookies);
            boolean hasRead = boardCookieValues.contains(String.valueOf(id));//현재 게시글 읽음 여부.
            System.out.println("현재 게시글 읽었니?:" + hasRead); //true:이미 읽음 //false:처음 읽음

            //조회
            BoardVo board = boardService.findById(id, hasRead); //id와 조회여부까지 확인한다.

            //xss공격대비 escapehtml처리
            String safeHtml = HelloMvcUtils.escapeHtml(board.getContent());
            //개행문자(\n) -> <br>
            board.setContent(HelloMvcUtils.convertLineFeedToBr(safeHtml));
            req.setAttribute("board", board);

            System.out.println("===============이 아래로 해당 게시글의 달린 댓글 내용들===============");
            System.out.println(board.getComments());


            //응답 쿠키 생성. 즉, 처음 읽은 글이라면 생성하자.
            if (!hasRead) {
                //안읽은 녀석이니까.
                boardCookieValues.add(String.valueOf(id));//현재 게시글 id 추가!

                //조회수 관련 처리
                String value = String.join("/", boardCookieValues);
                Cookie cookie = new Cookie("board", value);
                //유효기간을 정할 수 있다[초단위로]
                cookie.setMaxAge(365 * 24 * 60 * 60); //1년. , 만약 음수면 session 종료시 삭제 / 0이면 즉시삭제
                cookie.setPath(req.getContextPath() + "/board/BoardDetail"); //지정한 경로일 때만 클라이언트->서버로 쿠키전송
                resp.addCookie(cookie);//쿠키는 여러번 호출 할 수 있음.
            }

            //3.forward
            req.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(req, resp);
        } catch (Exception e)
        {
            throw new BoardException("게시글 상세보기 오류",e);
        }

        //예외 전환해서 던지기 : 사용자 친화적 메시지, 원인예외 wrapping
        //catch 하단에 최상위 예외 Exception e로 변경 후
        //throw new BoardException("게시글 상세보기 오류",e);

    }


    private List<String> getBoardCookieValues(Cookie[] cookies)
    {
        List<String> boardCookieValues = new ArrayList<>();
        if(cookies !=null)
        {
            for(Cookie cookie:cookies)
            {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println("쿠키 이름:"+name +" /쿠키 값:"+value); //쿠키 이름:board/쿠키 값:92
                if("board".equals(name))
                {
                    String[] temp = value.split("/");
                    for(String _id: temp)
                    {
                        boardCookieValues.add(_id);
                    }
                }
            }
        }
        return boardCookieValues;
    }
}