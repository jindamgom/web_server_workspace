<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.sh.mvc.member.model.entity.Role" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello MVC</title>
    <link rel="presessionect" href="https://fonts.googleapis.com">

    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Hello MVC</title>
        <link rel="presessionect" href="https://fonts.googleapis.com">
        <link rel="presessionect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Cute+Font&family=Gaegu:wght@300;400;700&family=Gowun+Dodum&family=Sunflower:wght@300;500;700&display=swap"
              rel="stylesheet">
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="${pageContext.request.contextPath}/js/tailwind.config.js"></script>
        <script
                src="https://code.jquery.com/jquery-3.7.1.js"
                integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
                crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />



        <script>
            const contextPath='${pageContext.request.contextPath}';
            <c:if test="${msg != null}">
            alert(`${msg}`);
                // 1213 메시지에 여러줄 입력이 가능하도록 "에서 백틱`으로 변경하였다.
                <%--session에 저장된 msg 속성값을 지워서 1회만 출력하게 한다 지우지 않으면 새로고침시 계속 나옴--%>
                <%-- session.removeAttribute("msg"); --%>
            <c:remove var="msg" scope="session"/>
            </c:if>
        </script>

        <c:if test="${loginMember!=null}">
            <script src="${pageContext.request.contextPath}/js/ws/ws.js"></script>
        </c:if>

    </head>
    <body>
    <div class="3xl:container">
        <header>
            <nav class="bg-white border-pink-500">
                <div class="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl p-4">

                    <a href="${pageContext.request.contextPath}" class="flex items-center space-x-3 rtl:space-x-reverse">
                        <img src="${pageContext.request.contextPath}/images/logo.svg" class="h-8" alt="Hello MVC Logo"/>
                        <span class="self-center text-2xl font-semibold whitespace-nowrap">Hello MVC</span>
                    </a>
                    <div class="flex items-center space-x-6 rtl:space-x-reverse">
                        <%--  ${pageContext.request.contextPath} 앱경로        --%>
                        <c:if test="${sessionScope.loginMember == null}">
                        <a href="${pageContext.request.contextPath}/member/memberLogin" class="text-md text-gray-600 hover:underline">로그인</a>
                        <a href="${pageContext.request.contextPath}/member/memberRegister" class="text-md text-gray-500 hover:underline">회원가입</a>
                        </c:if>

<%--                            서블릿에서 로그인에 성공시 attribute에 저장한 loginMember의 값을 가져와 id필드값을 띄워준다.--%>
                            <c:if test="${sessionScope.loginMember != null}">
                            <%--알림 관련--%>
                                <div class="relative group">
                                    <span id="notification" class="fa-solid fa-bell text-slate-600"></span>
                                    <ul id ="notification-container"
                                        class="absolute border border-3 rounded-lg hidden group-hover:block sm:-translate-x-20 w-48 text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg">
                                        <c: if test="${notifications!=null}">
                                            <c:forEach items="${notifications}" var="noti">
                                                <li class="w-full px-4 py-2 border-b border-gray-200 rounded-t-lg">
                                                    <a href="#" class="hover:underline text-blue-500">${noti.content}</a>
                                                </li>
                                            </c:forEach>
                                        </c:>
                                        <c: if test="${notifications==null}">
                                            <li class="w-full px-4 py-2 border-b border-gray-200 rounded-t-lg">
                                                <a href="#" class="hover:underline text-blue-500">알림없음</a>
                                            </li>
                                        </c:>

                                    </ul>
                                </div>
                                <a href="${pageContext.request.contextPath}/member/memberDetail" class="text-md text-gray-600 hover:underline">${loginMember.name}님, 안녕하세요?</a>
                                <a href="${pageContext.request.contextPath}/member/memberLogout" class="text-md text-gray-500 hover:underline">로그아웃</a>
                            </c:if>
                    </div>
                </div>
            </nav>
            <nav class="bg-pink-100">
                <div class="max-w-screen-xl px-4 py-3 mx-auto">
                    <div class="flex items-center">
                        <ul class="flex flex-row font-medium mt-0 space-x-8 rtl:space-x-reverse text-sm">
                            <li>
                                <a href="${pageContext.request.contextPath}" class="text-gray-900 hover:underline" aria-current="page">Home</a>
                            </li>
                            <li>
<%--                                <a href="#" class="text-gray-900 hover:underline">About</a>--%>
                                    <a class="text-gray-900 hover:underline">About</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/board/boardList" class="text-gray-900 hover:underline">Board</a>
                            </li>
                            <li>
                                <a href="${pageContext.request.contextPath}/photo/photoList" class="text-gray-900 hover:underline">Photo</a>
                            </li>
                            <%-- 관리자로 로그인 했을 때만 노출하기 --%>
<%--                            <c:if test="${loginMember.role == 'A'}">--%>
                            <c:if test="${loginMember.role == Role.A}">
                            <li>
                                <a href="${pageContext.request.contextPath}/admin/memberList" class="text-gray-900 hover:underline">Admin-Member</a>
                            </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <main class="w-full min-h-[80vh]">


<%--            https://xzio.tistory.com/1290 페이지 템플릿 --%>
