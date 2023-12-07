<%--

  JSP
  -Java + HTML
  -모든 JSP는 Servlet으로 변환되어 service된다
  -먼저 자바로 변환된 후 html처리
  -jsp의 모든 java코드는 서버단에서 먼저 처리 된 후 그 결과만 html형식으로 클라이언트에 전달됨

  구성요소
  1.java
    - 지시어(설정) <% page %> , <% include %> , <% taglib %>
    - 스크립팅원소 <% %>,<%= %>, <%! %>
  2.EL
  3.JSTL

  내장객체(jsp 자바영역에서 선언없이 사용할 수 있는 객체)
  1.context객체 (서버 운영에 필요한 정보를 가지고 각기 다른 생명주기를 가진 객체)
    -pageContext : PageContext (jsp처리 기간동안만 사용,생명주기 제일 짧다)
    -request : HttpServletRequset (사용자 요청 정보를 가진 객체, 요청~응답)
     ㄴ 매 요청마다 새로운 request객체가 생성됨
    -session : HttpSession (사용자 첫 접속~접속 해제)
    -application : Servletcontext (서버 시작~ 서버 종료)
  2.response : HttpServletResponse 응답관련 처리를 위한 객체
  3.out : PrintWriter 응답 출력 스트림



--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //자바영역
    //한줄 주석
    /*
     여러줄 주석
     */
    System.out.println("Basic.jsp 출력");
    int sum=0;
    for(int i=0; i<=100; i++)
    {
        sum += i;
    }
    System.out.println(sum);

    //밀리초 구하기
    long millis = System.currentTimeMillis();
    System.out.println(millis); // 1491968593191


    //el변수를 사용하려면 context 객체에 속성으로 대입해야 한다.
    pageContext.setAttribute("sum",sum);
    pageContext.setAttribute("millis",millis);
%>
<html>
<head>
    <title>JSP | Basics</title>
</head>
<body>
    <h1>Basics</h1>
    <h2>1~100까지의 합</h2>
    <%--tab으로 자동완성--%>
    <p id="sumFromServer"><%= sum%>입니다.</p>
    <p id="sumFromServer">${sum}</p>
    <p id="sumFromClient"></p>

    <h2>현재 시각(밀리초)</h2>
    <p id="nowFromServer"><%= millis%> 밀리초 입니다.</p>
    <p id="nowFromServer">${millis}</p>
    <p id="nowFromClient"></p>

    <%-- jsp주석 :servlet 변환 과정 중 제거--%>
    <!-- html 주석 : client까지 전달.. ctrl+u로 확인하면 html주석만 보임-->
    <script>
        let sum=0;
        for(let i=1; i<=100; i++)
            sum += i;
        document.querySelector("#sumFromClient").innerHTML=sum;

        const millis = Date.now();
        document.querySelector("#nowFromClient").innerHTML=Date.now();

    </script>
</body>
</html>
