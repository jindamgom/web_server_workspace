package com.sh.web.servlet;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Servlet 생명 주기
 * -HttpServlet 상속
 * -Tomcat에 의해서 싱글턴(프로그램당 객체를 하나만 만들어서 재사용하는 패턴) 객체로 관리된다.
 * 
 * 1.최초 요청 시 servlet 객체 생성
 * 2.@PostConstruct 메소드 호출
 * 3.설정메소드 init 호출 (초기화?)
 * 
 * 4.실제 요청이 들어오면 service 호출
 * 5.전송방식 별로 doGet,doPost 등이 호출
 * 
 * 6.destroy 메소드 호출
 * 7.@PreDestroy 메소드 호출
 * 8.자원 반납
 */
@WebServlet("/lifeCycle.do")
public class lifeCycleServlet extends HttpServlet{

	
	public lifeCycleServlet()
	{
		System.out.println("1.lifeCycle 생성자 호출 "+this);
	}
	
	@PostConstruct
	public void PostConstruct()
	{
		System.out.println("2.PostConstruct");
		//객체 생성된 후에 db에서 값을 읽어와 출력한다는 등의 작업을 여기서 할 수 있음
	}
	
	@Override
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
		System.out.println("3.init");
		}
	
	
	//한번 만들어지고 4~5번을 반복하며 재사용됨
	
	@Override
	public void service(ServletRequest req, ServletResponse res) 
			throws ServletException, IOException {
		System.out.println("4.service "+this);
		super.service(req, res);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("5.doGet "+this);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("6.destroy");
	}
	
	@PreDestroy
	private void PreDestroy() {
		System.out.println("7.@PreDestroy");

	}
	
}


