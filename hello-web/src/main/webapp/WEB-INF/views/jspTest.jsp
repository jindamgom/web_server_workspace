<%@ page import="java.util.Currency" %>
<%@ page import="java.util.Locale" %><%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2023-12-07
  Time: 오후 6:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String won = Currency.getInstance(Locale.KOREA).getSymbol();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jsp | Test</title>
    <style>
        table {
            border: 1px solid #000;
            border-collapse: collapse;
            margin: 20px 10px;
        }

        th, td {
            border: 1px solid #000;
            padding: 5px;
        }
    </style>
</head>
<body>
<h1>Test</h1>

<%--이름 출력 --%>
<h3>names</h3>
<ul>
    <c:forEach items="${names}" var="personname" >
      <li>${personname}</li>
    </c:forEach>
</ul>

<%--테이블에 키보드 스펙 출력 --%>
<h3>items</h3>
<table>
    <thead>
    <tr>
    <%--헤더부분 No,이름,가격--%>
        <th>No</th>
        <th>이름</th>
        <th>가격</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.no}</td>
            <td>${item.name}</td>
<%--            <td><%= won%>${item.price}</td>--%>

            <td><%= won%><fmt:formatNumber value="${item.price}" pattern="#,###"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<%--Map<String, Object> map = Map.of(--%>
<%--"name", "홍길동",--%>
<%--"age", 33,--%>
<%--"married", true--%>
<%--);--%>
<h3>map</h3>
<table>
    <tbody>
    <tr>
        <th>이름</th>
        <td>
            <input type="text" value="${map.name}"/>
        </td>
    </tr>
    <tr>
        <th>나이</th>
        <td>
            <input type="number" value="${map.age}"/>
        </td>
    </tr>
    <tr>
        <th>결혼여부</th>
        <td>
            <c:if test="${map.married==true}" >
                <input type="checkbox" checked/>

            </c:if>
            <c:if test="${map.married==false}">
                <input type="checkbox" />
            </c:if>
        </td>
    </tr>
    </tbody>
</table>


<%--request.setAttribute("no1", 123.456);--%>
<%--request.setAttribute("no2", 3_000_000);--%>
<%--request.setAttribute("no3", .15);--%>


<h2>숫자</h2>
<ul>
    <li><fmt:formatNumber value="${no1}" pattern="###.##"/></li><%-- 123.46 --%>
    <li>${no1}</li><%-- 123.456 --%>
    <li><fmt:formatNumber value="${no1}" pattern="#.00000"/></li><%-- 123.45600 --%>
    <li><fmt:formatNumber value="${no2}" groupingUsed="true"/></li><%-- 3,000,000 --%>

<%--    <li><%= won%><fmt:formatNumber value="${no2}" groupingUsed="true"/></li>&lt;%&ndash; ₩3,000,000 &ndash;%&gt;--%>
    <li><fmt:formatNumber value="${no2}" type="currency" currencySymbol="\\"/></li><%-- ₩3,000,000 --%>
    <li><fmt:formatNumber value="${no3}" type="percent"/></li><%-- 15% --%>
</ul>

<%--request.setAttribute("date", Date.valueOf("2023-07-24")); // java.sql.Date--%>
<%--request.setAttribute("datetime", new java.util.Date());--%>
<h2>날짜/시각</h2>
<ul>
    <li><fmt:formatDate value="${date}" pattern="yyyy년MM월dd일"/></li><%-- 2023년07월24일 --%>
    <li><fmt:formatDate value="${datetime}" pattern="yyyy/MM/dd hh:mm:ss.SSS"/></li><%-- 2023/12/07 18:00:52.335 --%>
</ul>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</body>
</html>
<%--https://treasurebear.tistory.com/43--%>
<%--https://docs.oracle.com/javaee/5/jstl/1.1/docs/tlddocs/--%>