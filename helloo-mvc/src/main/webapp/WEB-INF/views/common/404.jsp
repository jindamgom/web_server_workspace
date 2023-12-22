<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2023-12-22
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
  헤더/푸터도 제거
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         isErrorPage="true" %>
<%--
    page 지시어의 isErrorPage=true
    현재 페이지에서 던져진 예외객체 exception 사용이 가능하다.(스크립틀릿 사용)
    에러코드로 넘어오는 경우는 exception 객체가 null이다..

--%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Hello MVC | Not Found👻</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="${pageContext.request.contextPath}/js/tailwind.config.js"></script>
</head>
<body>
<div class="flex min-h-full flex-col items-center px-6 py-12">
    <h1 class="text-[300px]">👻</h1>
    <p class="text-red-700">해당 페이지를 찾을 수 없습니다.</p>
    <p><a href="${pageContext.request.contextPath}" class="hover:underline text-blue-700">메인페이지로 돌아가기</a></p>
</div>
</body>
</html>


