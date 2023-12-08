<%--
    JSTL
    -JSP Standard Tag Library
    -JSP에서 사용가능한 액션태그의 한 종류..
       -표준액션태그<jsp:xxx>
       -커스텀액션태그<c:xxx> , <fmt:xxx>, $<fn:xxx>
        -jstl.jar 의존 추가후 taglib 등록 후 사용
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>JSP | JSTL</title>
</head>
<body>
    <h1>JSTL</h1>
    <h2>core</h2>
    <%-- 분기 처리 if --%>
    <c:if test="${num%2==0}">
        <p title="${num}">짝수입니다.^^</p>
    </c:if>
    <c:if test="${num%2!=0}">
        <p title="${num}">홀수입니다.^^</p>
    </c:if>


    <%-- 분기처리 choose  --%>
    <c:choose>
        <c:when test="${num>=1 and num<50}">
            <p>달고나 당첨</p>
        </c:when>
        <c:when test="${num>=50 and num<75}">
            <p>스타벅스 아메리카노 당첨</p>
        </c:when>
        <c:otherwise>
            <p>꽝..다음기회에...😂</p>
        </c:otherwise>
    </c:choose>

    <%-- 반복처리 foreach 단순반복/리스트,배열 순회 --%>
    <%-- 1.단순반복 증감변수 n의 범위 설정. begin~end (step)--%>
    <c:forEach begin="1" end="6" var ="n">
        <h${n}>Hello World ${n}</h${n}>
    </c:forEach>

    <%--
        2.list, 배열등 반복처리
    --%>
    <ul>
        <c:forEach items="${hobbies}" var="hobby">
            <li>${hobby}</li>
        </c:forEach>
    </ul>

    <table>
        <thead>
        <tr>
            <th>no</th>
            <th>취미</th>
        </tr>
        </thead>
        <tbody>
                <%--
                    varStatus : 반복상태를 관리하는 객체 이름 지정
                    (이미 존재하는 것이고, 이름만 내가 정해주는거)
                    vs.index : 0-based index
                    vs.count : 1-based index
                    vs.first : 첫번째 요소 여부 (첫번째면 true,아님 false)
                    vs.last : 마지막 요소 여부
                 --%>
            <c:forEach items="${hobbies}" var="h" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>
                        ${vs.first?'🥇':''}
                            ${vs.last?'🥉':''}

                            ${h}
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
        <p>
        <c:forEach items="${hobbies}" var="hobby" varStatus="vss">
            ${hobby}
            ${vss.last?'':','}
        </c:forEach>
    </p>
    </div>


    <h2>fmt</h2>
    <ul>
        <li>
            <fmt:formatNumber value="${no1}" pattern="#,###"/>
        </li>
        <li>

            <%-- 특정 자리수 까지 반올림처리..   --%>
<%--            # 해당자리수가 없으면 공란처리,
                0 해당자리수가 없으면 0처리--%>
            <fmt:formatNumber value="${no2}" pattern="#,#"/>
        </li>
        <li>
            <fmt:formatDate value="${today}" pattern="yy/MM/dd(E) hh:mm:ss"/>
        </li>
    </ul>

<%--    nongDamGom--%>
    <h2>functions</h2>
    <ul>
        <li>${name}</li>
        <li>${fn:toUpperCase(name)}</li>
        <li>${fn:toLowerCase(name)}</li>
        <li>${fn:startsWith(name,'dam')}</li>
        <li>${fn:indexOf(name,'g')}</li>
    </ul>


    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
