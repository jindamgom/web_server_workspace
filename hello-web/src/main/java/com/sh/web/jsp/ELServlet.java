package com.sh.web.jsp;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet("/el.do")
public class ELServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = "홍길동";
        int num=123;
        List<String> hobbies = Arrays.asList("독서","영화감상","잠자기");
        Map<String,Integer> booksMap = Map.of
                ("나미야 잡화점",15_000,"졸려",2_000,"zipgagosipda",10_000);

        //context 객체 대입해야 EL에서 사용 가능함, 안그럼 선언된 변수가 아니라고 오류
        req.setAttribute("name",name);
        req.setAttribute("num",num);
        req.setAttribute("hobbies",hobbies);
        req.setAttribute("booksMap",booksMap);

        HttpSession session = req.getSession();
        session.setAttribute("name",name);



        //ctrl alt v
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/02_el.jsp");
        requestDispatcher.forward(req,resp);
    }
}
