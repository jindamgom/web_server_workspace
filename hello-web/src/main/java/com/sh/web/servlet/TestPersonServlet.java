package com.sh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/***
 * Servlet - 업무로직 처리(해당 url에서 해야할 일) , jsp로 forwarding
 * JSP - html작성 (기존 java+html코드를 이제 분리한다)
 *
 */
@WebServlet("/testPerson3.do")
public class TestPersonServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청 메시지에 대한 인코딩처리해야함.
		req.setCharacterEncoding("utf-8");
		
		//dopost오버라이드
		//사용자 입력값 처리
		String name = req.getParameter("name");
		String color = req.getParameter("color");
		String animal = req.getParameter("animal");
		String[] foods = req.getParameterValues("food");
		
		//응답 html 작성
		System.out.println(name);
		System.out.println(color);
		System.out.println(animal);
		System.out.println(Arrays.toString(foods));
		
		//업무로직. 
		//사용자의 취향을 고려하여 추천 키워드를 제공한다.. 라는 기능 추가해보기
		
		//선택한 컬러에 따라서 분기처리 되고 반환값이 recommendation에 대입
		//swtich statement jdk17 이상
		//recommendation변수는 위의 name,color와 달리 사용자 입력값이 아닌 서버에서 생성한 값
		String recommendation = 
				switch(color)
				{
				case "빨강"->"엽떡";
				case "노랑"->"호박죽";
				case "초록"->"녹즙";
				case "파랑"->"파워에이드";
				default -> "아무거나먹어";
		};
		System.out.println(recommendation);
		//jsp 전달하기 - request 객체에 속성으로 저장
		req.setAttribute("recommendation", recommendation);
		
		
		//jsp포워딩 (RequsetDispatcher) = send
		//jsp 경로작성
		//파일경로는 절대경로로.. src/main/webapp(웹 루트) 이하부터 작성..
		RequestDispatcher reqDispatcher = 
				req.getRequestDispatcher("/WEB-INF/views/testPersonResult.jsp");
		reqDispatcher.forward(req,resp);
		
		
		
	}
}
