package com.sh.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class abcServlet
 */
public class abcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("/abc.do 요청~");
		
		//리다이렉트
		//클라이언트에게 location으로 재요청을 지시한다.
		//기존의 "/hello-web/xyz.do"를 수정한다.
		//하드코딩 지양-
		response.sendRedirect(request.getContextPath()+"/xyz.do");
	
	}

	

}
