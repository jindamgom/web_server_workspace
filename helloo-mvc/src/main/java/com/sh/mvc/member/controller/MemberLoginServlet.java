package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * http 통신은 stateless하다.
 * -상태를 관리하지 않는다.
 * -매 요청은 독립적이다.
 * -요청/응답 후에는 연결이 끊긴다.
 * -사용자의 상태도 관리하지 못한다..[로그인 유지 불가]
 *
 *
 *  Session - cookie를 이용한 사용자 상태관리
 *  -session 정보를 server(tomcat)측에서 관리 - 로그인 사용자 정보
 *  -cookie 정보를 client(브라우저, 즉 chrome) 측에서 관리 - session id
 *  [기본 마지막 요청으로부터 30분 지속]
 *
 *  1.client 첫 접속시 session은 session id를 발급, 응답헤더에 추가한다.
 *  -응답헤더 set-cookie 확인.
 *
 *  2.set-cookie 응답을 받은 client는 브라우저에 cookie항목에 session id저장
 *  -application cookie항목에서 확인가능
 *
 *  3.다음 매 요청마다 client는 cookie 항목을 session id를 함께 전송한다.
 *  -요청헤더 cookie 확인
 *
 *  4.요청헤더의 cookie를 확인한 Server는 업무로직 수행시 해당 session객체를 사용하게 된다
 *  -session id가 유효하지 않다면 새로 발급해서 1번부터 반복한다..
 *  -최초 접속부터 30분간 유지되는 session! 1시간뒤엔 폐기되므로 다시 session객체를 생성함
 */

//하나의 서블릿에서 get과 post 방식 로직 처리를 한다.
//url을 효율적으로 사용할 수 있다는 이점이 있음.

@WebServlet("/member/memberLogin")
public class MemberLoginServlet extends HttpServlet {

    /**
     * 로그인 홈페이지로 안내 한다.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {





        RequestDispatcher requestDispatcher =  req.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp");
        requestDispatcher.forward(req,resp);
    }

    private MemberService memberService = new MemberService();
    /**
     * 실제 로그인 처리를 한다.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력값 인코딩 처리
        req.setCharacterEncoding("utf-8");
        
        
        //2.사용자 입력값 가져오기
        String id= req.getParameter("id"); //아이디 input tag - name 값인 id
        String password = req.getParameter("password"); //비밀번호 input tag - name 값인 passwords
        System.out.println(id + "/" + password);


        //3.업무로직 : 이번 요청에 처리할 작업 - 로그인(인증)
        //id와 pw로 내가 회원인것을 증명한다.->DB에서 읽어온 데이터(member객체) 비교한다.
        //로그인 성공 (id/pw 둘 다 일치)
        //로그인 실패 (존재하지 않는 id / pw가 틀린 경우)

        Member member = memberService.findById(id);
        HttpSession session =req.getSession();

        if(member!=null && password.equals(member.getPassword()))
        {
            //아이디가 null이 아니면서 비밀번호가 일치하는 경우 즉, 로그인 성공
            System.out.println("로그인성공");
            //이번요청이 끝나면 리퀘스트도 폐기되고 setAttribute 저장된 값도 폐기됨
            //그래서 home버튼 등으로 index페이지를 갱신하면 로그인이 풀려버리는(폐기되는)현상이 발생
            //pageContext - request - session - application 컨텍스트 객체중 login 처리에 적합한 것은 session
            //session객체는 사용자의 서버 첫 접속 ~ 세션 해제시 까지 유효함.

            session.setAttribute("loginMember",member);
            //req.setAttribute("loginMember",member);
        }
        else {
            //로그인 실패..
            System.out.println("로그인실패");
            session.setAttribute("msg","아이디가 존재하지 않거나, 비밀번호가 틀립니다.🙏🏻");
        }
        
        
        //4.view 단 처리 (forwarding) | redirect처리 (url변경시)
        //4-1.일단은 forwarding을 한다 (url은 변경되지 않는다..)
        //즉, 다른 화면단/처리를 하려면 redirect처리해야함
        
        //dml요청(post) 로그인 요청등은 반드시 redirect로 url변경처리해야함
        //로그인 직후 새로고침을 하면 또 로그인 요청이 들어감.
        //req.getRequestDispatcher("/index.jsp").forward(req,resp);
        //아래와 같이 수정해야한다.
        resp.sendRedirect(req.getContextPath());

    }
}
