<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Ajax | xml</title>
    <style>
        table{

            border: 1px solid #000;
            border-collapse: collapse;
            margin :10px 0;
        }
        th{
            background: aquamarine;
            border:  1px solid #000;
            border-collapse: collapse;
        }
        td{
             border:  1px solid #000;
             border-collapse: collapse;
         }

    </style>
</head>
<body>
<h1>xml2(실습문제)</h1>
<button id="btn-product">상품 확인</button>
<table id="products">
    <thead>
    <th>상품ID</th>
    <th>SKU</th>
    <th>상품명</th>
    <th>URL</th>
    <th>가격</th>
    <th>판매가격</th>
    <th>썸네일URL</th>
    <th>검색키워드</th>
    <th>설명</th>
    <th>카테고리</th>
    <th>카테고리ID</th>
    <th>브랜드</th>
    <th>자식 SKU</th>
    <th>자식 가격</th>
    <th>컬러</th>
    <th>컬러 패밀리</th>
    <th>컬러 견본</th>
    <th>사이즈</th>
    <th>신발 사이즈</th>
    <th>바지 사이즈</th>
    <th>주기</th>
    <th>계절</th>
    <th>배지</th>
    <th>별점 평균</th>
    <th>별점 개수</th>
    <th>재고수</th>
    <th>생성일</th>
    </thead>
    <tbody></tbody>
</table>

<script
        src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script>
    const contextPath='${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/xml2.js"></script>
</body>
</html>