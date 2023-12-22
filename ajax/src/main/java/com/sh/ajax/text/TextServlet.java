package com.sh.ajax.text;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/text")
public class TextServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자입력값
        String name = req.getParameter("name");
        int num = Integer.parseInt(req.getParameter("num"));

        //2.업무로직
        if((int)(Math.random()*2)==0)
            throw new RuntimeException("에러가 발생했습니다..");

        //3.비동기 식 응답 직접 출력
        resp.setContentType("text/plain; charset=utf08");
        PrintWriter out = resp.getWriter();
        out.println("텍스트보냅니다");
        out.println("이름:"+name);
        out.println("숫자:"+num);

    }
}