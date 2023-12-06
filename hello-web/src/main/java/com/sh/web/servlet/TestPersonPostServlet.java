package com.sh.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 클라이언트 : /hello-web/testPerson2.do
 * 서버: /hello-web에 해당하는 프로젝트를 찾고, /testPerson2.do에 해당하는 servlet을 찾아서 연결
 * @WebServlet("/testPerson2.do") : context path는 작성하지 않는다..
 *
 */
@WebServlet("/testPerson2.do")
public class TestPersonPostServlet extends HttpServlet{

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
		
		//응답 html작성해보기
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter(); //출력대상이 응답메시지인 문자기반 출력스트림
		
		//유저가 무엇을 입력/선택하냐에 따라 동적으로 다른 결과를 출력해주는 동적 html
		String body = """
				<!doctype html>
				<html>
					<head>
						<meta charset="utf-8"/>
						<title>개인 취향 검사 결과post</title>
					</head>
					<body>
						<h1>개인 취향 검사 결과post</h1>
						<p>반갑습니다. %s님!</p>
						<p>%s색을 좋아하시는군요</p>
						<p>동물은 %s을 좋아하시네요?</p>
						<p>그리고 중국집 음식중에는 %s을 좋아하시는군요</p>
						<img src="https://homecuisine.co.kr/files/attach/images/142/737/002/969e9f7dc60d42510c5c0353a58ba701.JPG"/>
					</body>
				</html>
				""".formatted(name,color,animal,Arrays.toString(foods));
		System.out.println(body);//콘솔에 출력
		out.append(body);//응답메세지를 body에 출력한다..
		
	}
}
