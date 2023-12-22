package com.sh.mvc.member.controller;

import com.sh.mvc.common.HelloMvcUtils;
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

        //Referer(사용자가 머물렀던 페이지)를 세션에 저장한다.
        String referer = req.getHeader("Referer");
        System.out.println("referer:"+referer);

        //1222 추가 - 현재 페이지가 로그인 페이지가 아닐때만 저장.
        //로그인에 실패했을 경우, 다시 로그인 페이지로 돌아오는데 그럴 경우 referer에 로그인 페이지가 저장되어
        //사용자가 머물렀던 페이지가 로그인 페이지로 갱신되어버림. 그것을 막고자 로그인페이지가 아닌 경우만 referer에 저장.
        //"http://localhost:8080/mvc/member/memberLogin"
        if (!referer.contains("member/memberLogin"))
            req.getSession().setAttribute("next",referer);

        RequestDispatcher requestDispatcher =  req.getRequestDispatcher("/WEB-INF/views/member/memberLogin.jsp");
        requestDispatcher.forward(req,resp);
    }

    /**
     * 1213 실습과제
     *      요구사항은 다음과 같습니다.
     *
     * 1. 로그인시 아이디 저장 체크박스가 체크되어 있다면, WebStorage에 `saveId=아이디` 형식으로 저장합니다.
     * 2. 로그인시 아이디 저장 체크박스가 체크해제되어 있다면, WebStorage의 `saveId`를 제거합니다.
     * 3. 로그인페이지 `GET /mvc/member/memberLogin` 요청시 WebStorage에 저장된 `saveId` 값이 있다면,
     *    `#id` input태그에 값을 기록하고, `#saveId`는 체크해제합니다.
     */

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
        //단순 입력 비밀번호값->암호화작업 이후 이렇게 수정
        String password = HelloMvcUtils.getEncryptedPassword(req.getParameter("password"),id); 

        //아이디 저장 체크박스
        String idSavedChecked = req.getParameter("saveId");

        //String형으로 받아올 시  : on or null
        System.out.println("로그인시 받아온 값......"+id + "/" + password+"/"+idSavedChecked);
        if(idSavedChecked != null)
        {
            System.out.println("아이디 저장에 체크하였습니다");
            
        }
        else {
            System.out.println("아이디 저장에 체크하지 않았습니다....");
        }

        //3.업무로직 : 이번 요청에 처리할 작업 - 로그인(인증)
        //id와 pw로 내가 회원인것을 증명한다.->DB에서 읽어온 데이터(member객체) 비교한다.
        //로그인 성공 (id/pw 둘 다 일치)
        //로그인 실패 (존재하지 않는 id / pw가 틀린 경우)

        Member member = memberService.findById(id);
        //세션을 생성or 가져오는 코드이며 getSession() 괄호안 생략인 경우 기본값 true
        //true:세션존재하지않을시 생성하고, 존재한다면 존재하는 세션을 반환한다.
        //false:세션이 존재하면 세션 반환한하고, 없다면 새로생성하지않고 null반환
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
            String location = req.getContextPath()+"/";
            String next = (String)req.getSession().getAttribute("next");
            if(next !=null) {
                location = next;
                req.getSession().removeAttribute("next");//location에 옮겨담고 삭제
            }
            resp.sendRedirect(location);
        }
        else {
            //로그인 실패..
            System.out.println("로그인실패");
            session.setAttribute("msg","아이디가 존재하지 않거나, 비밀번호가 틀립니다.🙏🏻");
            resp.sendRedirect(req.getContextPath()+"/member/memberLogin"); //get방식 [리다이렉트도 get방식이다]
        }
        
        
        //4.view 단 처리 (forwarding) | redirect처리 (url변경시)
        //4-1.일단은 forwarding을 한다 (url은 변경되지 않는다..)
        //즉, 다른 화면단/처리를 하려면 redirect처리해야함
        
        //dml요청(post) 로그인 요청등은 반드시 redirect로 url변경처리해야함
        //로그인 직후 새로고침을 하면 또 로그인 요청이 들어감.
        //req.getRequestDispatcher("/index.jsp").forward(req,resp);
        //아래와 같이 수정해야한다.
        //resp.sendRedirect(req.getContextPath()+"/");
        //뒤에 슬래시 하나 더 붙이는 이유
        // /mvc ->/mvc/에서 리다이렉트가 한번 더 발생해서 그것을 줄이고자 ..


    }
}
