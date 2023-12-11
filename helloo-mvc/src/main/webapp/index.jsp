<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--header부분을 load한다.--%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%--매 페이지 마다 달라지는 main 부분--%>
            <div class="h-[80vh] flex justify-center items-center">
                <div class="w-[500px] h-[500px] grid grid-cols-2 md:grid-cols-3">
                        <p class="text-center text-8xl">🍓</p>
                        <p class="text-center text-8xl">🍒</p>
                        <p class="text-center text-8xl">🍎</p>
                        <p class="text-center text-8xl">🍋</p>
                        <p class="text-center text-8xl">🍍</p>
                        <p class="text-center text-8xl">🍌</p>
                        <p class="text-center text-8xl">🍐</p>
                        <p class="text-center text-8xl">🍈</p>
                        <p class="text-center text-8xl hidden md:block">🍏</p>
                </div>
            </div>
<%--footer 부분을 load한다.--%>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
