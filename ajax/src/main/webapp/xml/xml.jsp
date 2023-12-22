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
        th,td{
            border:  1px solid #000;
            border-collapse: collapse;
        }

    </style>
</head>
<body>
    <h1>xml</h1>
    <button id="btn1">확인</button>
    <table id="books">
        <thead>
            <th>주제</th>
            <th>제목</th>
            <th>저자</th>
        </thead>
        <tbody>


        </tbody>

    </table>

    <script
            src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
            crossorigin="anonymous"></script>
    <script>
        const contextPath='${pageContext.request.contextPath}';
    </script>
    <script src="${pageContext.request.contextPath}/js/xml.js"></script>
</body>
</html>