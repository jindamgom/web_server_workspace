<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2023-12-06
  Time: 오후 6:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문 결과 페이지</title>
    <style>
        span#mainMenu {
            color: navy;
            font-size: 2em;
        }

        span#sideMenu {
            color: indigo;
            font-size: 1.5em;
        }

        span#drinkMenu {
            color: yellowgreen;
        }

        span#price {
            color: maroon;
            font-size: 2.5em;
        }
    </style>
</head>
<body>
<h2>감사합니다.</h2>
<span id="mainMenu">${param.mainMenu}</span>,
<span id="sideMenu">${param.sideMenu}</span>,
<span id="drinkMenu">${param.drinkMenu}</span>을/를 주문하셨습니다.
<br /> 총 결제금액은
<span id="price">${totalPrice}원</span> 입니다.
<br>
<img src="https://i.namu.wiki/i/1EDvyVH0IZjtAA_d3N0fOgcQ0W-41UIPYYplbmBWG5bMEvC96171vKeMCLYa328tN5O9hqIb8JFqsRVetDHntw.webp"/>
</body>
</html>
