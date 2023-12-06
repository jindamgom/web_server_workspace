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
 * 
 * Servlet 클래스
 * -웹 요청을 처리하기 위한 자바클래스
 * -javax.servlet.http.HttpServlet 클래스 상속(Tomcat라이브러리에 포함. servlet-api.jar)
 * -GET 방식 요청 - doGet 작성
 * -POST 방식 요청- doPost 작성
 * -request(사용자 요청관련 정보), response(응답처리를 위한 객체)객체 사용 
 *
 */
@WebServlet("/testPerson1.do")
public class TestPersonGetServlet extends HttpServlet{
	
	//HttpServletRequest req:요청
	//HttpServletResponse resp:응답
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//super.doGet(req, resp);
		//사용자 입력값(문자열) 확인
		String name = req.getParameter("name");//input태그의 name 속성 값
		String color = req.getParameter("color");//input태그의 color 속성 값
		String animal = req.getParameter("animal");//input태그의 animal 속성 값
		//값이 여러개인 경우엔 values를 사용하고 배열에 담는다.
		String[] food = req.getParameterValues("food");//input태그의 food 속성 값
		
		System.out.println(name);
		System.out.println(color);
		System.out.println(animal);
		System.out.println(Arrays.toString(food));
		
		//응답 html 작성
		//헤더 작성 - MIME 타입/인코딩 (응답으로 작성할 타입의 형식. 다양하게 지정가능)
		resp.setContentType("text/html; charset=utf-8");
		
		
		PrintWriter out = resp.getWriter();
		String body = """
				<!doctype html>
				<html>
					<head>
						<meta charset="utf-8"/>
						<title>개인 취향 검사 결과</title>
					</head>
					<body>
						<h1>개인 취향 검사 결과</h1>
						<p>반갑습니다. %s님!</p>
						<p>%s색을 좋아하시는군요</p>
						<p>동물은 %s을 좋아하시네요?</p>
						<p>그리고 중국집 음식중에는 %s을 좋아하시는군요</p>
					</body>
				</html>
				""".formatted(name,color,animal,Arrays.toString(food));
		System.out.println(body);//콘솔에 출력
		
		
		out.append(body);//응답메세지를 body에 출력한다..
	}

}
