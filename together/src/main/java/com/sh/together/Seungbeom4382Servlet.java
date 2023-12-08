package com.sh.together;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Seungbeom4382")
public class Seungbeom4382Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().print("seungbeom4382 🤩🤩🤩");
        resp.getWriter().print("<br>내용 추가 ~~~ ~~~ ~~~ 😎😎😎");
    }
}