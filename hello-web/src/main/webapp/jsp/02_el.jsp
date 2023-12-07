<%--

    el 내장객체
    -context 객체 속성 (생략 가능, 생략시 page-request,session,application순으로 조회해서 찾음)
         -pageScope
         -requestScope
         -sessionScope
         -applicationScope
    -사용자 입력값
         -param
         -paramValues(배열)

    -header 정보
         -header
         -headerValues
    -쿠키 cookie
    -PageContext 객체 직접 접근
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP | EL</title>
</head>
<body>
    <h1>EL</h1>
    <h2>context 내장객체 속성</h2>
    <ul>
        <!-- requestScope는 생략이 가능하다.. 대신 생략하면 name변수를 찾을수없다 하는데 에러는 안난다..-->
        <!-- 각 스코프에 동일한 네이밍의 변수가 있는 경우엔 앞에 scope명시-->
        <!-- 하비 배열 0,1,2 인덱스까지만 존재하는데 아래같이 작성해도 오류가 발생하지 않음.[빈칸출력]-->
        <li>이름 : ${requestScope.name}</li>
        <li>숫자 : ${requestScope.num}</li>
        <li>취미 : ${requestScope.hobbies}
                <ul>
                    <li>${hobbies[0]}</li>
                    <li>${hobbies[1]}</li>
                    <li>${hobbies[2]}</li>
                    <li>${hobbies[3]}</li>
                    <li>${hobbies[4]}</li>
                </ul>
        </li>
        <li>책/가격 : ${requestScope.booksMap}
            <ul>
                <li>${booksMap.zipgagosipda}</li>
                <li>${booksMap['나미야 잡화점']}</li>
            </ul>

        </li>
    </ul>


    <h2>사용자 입력값 처리</h2>
    <ul>
        <li>name  : ${param.name}</li>
        <li>option1  : ${paramValues.option[0]}</li>
        <li>option2  : ${paramValues.option[1]}</li>
    </ul>

    <h2>헤더값</h2>
    <ul>
        <li>User-Agent : ${header['User-Agent']}</li>
    </ul>
</body>
</html>
