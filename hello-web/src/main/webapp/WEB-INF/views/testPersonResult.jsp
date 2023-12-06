<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	jsp 주석
	-단순 출력 EL
	-분기/반복처리 JSTL
	-자바코드 작성 scriptlet


     --%>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8"/>
			<title>개인 취향 검사 결과Servlet-JSP</title>
	</head>
	<body>
		<h1>개인 취향 검사 결과Servlet-JSP</h1>
		<p>반갑습니다. ${param.name}님!</p>
		<p>${param.color}색을 좋아하시는군요</p>
		<p>동물은 ${param.animal}을 좋아하시네요?</p>
		<p>그리고 중국집 음식중에는 
		<%--items 반복접근할 객체 (배열,list,set,map..) -EL로 작성할 것!
			var 반복문 안에서 요소를 담을 변수명 배열[0] ->var food [1]->var food..(이하 반복)
			namespace c: 위에 선언한 prefix
		 --%>
			<c:forEach items="${paramValues.food}" var="food">${food} </c:forEach>
			을 좋아하시는군요</p>
		
		<!-- 속성에 저장한 key값을 대입하면됨 , 서버에서 생성한 데이터. -->
		<h2>오늘의 추천 음식.. ${recommendation} 어떠신가요?</h2>
		
		<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDAyMTZfMTE2/MDAxNTgxODIxODY3MTE3.ZmzNywRgzFN7kSyIrvelQdaAYnIOl53GJPva6RjBUXkg.fwoar6gpBxHt0ZVqfdux8HeVuRuvnW2dersACS_149kg.JPEG.balloonhaha/%EB%B3%91%EC%95%84%EB%A6%AC1_shidsung.jpg?type=w800"/>
	</body>
</html>