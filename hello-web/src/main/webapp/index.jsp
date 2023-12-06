<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
	<meta charset="utf-8">
	<title>Hello Web</title>
</head>
<body>
	<h1>Hello World!</h1>
	<p>반갑습니다:)</p>
	
	<h2>Servlet</h2>
	<ul>
		<li>
			<!-- 
			서버 절대주소 요청시 /hello-web이라는 context-path(앱이름)를 반드시 포함해야 한다.
			톰캣 하위에 여러 프로젝트가 있기 때문에 절대주소로 앱 명을 명시해야함. 
			src/main/webapp을 web root로 삼음.
			-->
			<a href="/hello-web/servlet/01_testPerson.html">GET 취향검사</a>
		</li>
		<li>
			<a href="/hello-web/servlet/02_testPerson.html">POST 취향검사</a>
		</li>
		<li>
			<a href="/hello-web/servlet/03_testPerson.html">Servlet-JSP취향검사</a>
		</li>
		<li>
			<a href="/hello-web/lifeCycle.do">Sevlet 생명주기</a>
		</li>
		<li>
			<a href="/hello-web/abc.do">Redirect</a>
		</li>
	</ul>
	<h2>JSP</h2>
</body>
</html>
