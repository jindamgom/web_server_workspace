<%--
  Created by IntelliJ IDEA.
  User: user1
  Date: 2023-12-26
  Time: 오후 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax | Json</title>
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
        table img{
            width:200px;
        }

    </style>
</head>
<body>
<button id="btn-celeb">Celeb 확인</button>
<table id="celebs">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>profile</th>
        <th>type</th>
        <th>수정</th>
        <th>삭제</th>
    <tr>
    </thead>
    <tbody>

    </tbody>
</table>

<form action="celebSearchForm">
    <fieldset>
        <legend>Celeb id 조회</legend>
        <input type="text" name="id" id="id" placeholder="아이디를 입력하세요..">
        <button id="btn-celeb-search" type="button">검색</button>
    </fieldset>
</form>
<table id="celeb">
    <tr>
        <th>id</th>
        <td class="celeb-id"></td>
    </tr>
    <tr>
        <th>name</th>
        <td class="celeb-name"></td>
    </tr>
    <tr>
        <th>profile</th>
        <td class="celeb-profile"></td>
    </tr>
    <tr>
        <th>type</th>
        <td class="celeb-type"></td>
    </tr>

</table>


<form name="celebRegisterFrm">
    <fieldset>
        <legend>Celeb 등록폼</legend>
        <table>
            <tbody>
            <tr>
                <th>
                    <label for="celeb-register-name">Name</label>
                </th>
                <td>
                    <input type="text" name="name" id="celeb-register-name" />
                </td>
            </tr>
            <tr>
                <th>
                    <label for="celeb-register-type">Type</label>
                </th>
                <td>
                    <select name="type" id="celeb-register-type">
                        <option value="ACTOR">ACTOR</option>
                        <option value="SINGER">SINGER</option>
                        <option value="MODEL">MODEL</option>
                        <option value="COMEDIAN">COMEDIAN</option>
                        <option value="ENTERTAINER">ENTERTAINER</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="celeb-register-profile">Profile</label>
                </th>
                <td>
                    <input type="file" name="profile" id="celeb-register-profile" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button>등록</button>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</form>

<%--hint : data-set --%>
<form name="celebUpdateFrm">
    <fieldset>
        <legend>Celeb 수정폼</legend>
        <table>
            <tbody>
            <tr>
                <th>
                    <label for="celeb-update-name">Name</label>
                </th>
                <td>
                    <input type="text" name="name" id="celeb-update-name" />
                </td>
                <td rowspan="3">
                    <img src="" alt="등록된이미지">
                    <br>
                    <label for="delProfile">삭제</label>
                    <input type="checkbox" name="delProfile" id="delProfile">
                </td>
            </tr>
            <tr>
                <th>
                    <label for="celeb-update-type">Type</label>
                </th>
                <td>
                    <select name="type" id="celeb-update-type">
                        <option value="ACTOR">ACTOR</option>
                        <option value="SINGER">SINGER</option>
                        <option value="MODEL">MODEL</option>
                        <option value="COMEDIAN">COMEDIAN</option>
                        <option value="ENTERTAINER">ENTERTAINER</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>
                    <label for="celeb-register-profile">Profile</label>
                </th>
                <td>
                    <input type="file" name="profile" id="celeb-update-profile" />
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <button>등록</button>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</form>


<script
        src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
        crossorigin="anonymous"></script>
<script>
    const contextPath='${pageContext.request.contextPath}';
</script>
<script src="${pageContext.request.contextPath}/js/json.js"></script>
</body>
</html>