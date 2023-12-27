<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<celebs>
    <c:forEach items="${celebs}" var="celeb">
    <celeb>
        <id>${celeb.id}</id>
        <name>${celeb.name}</name>
        <profile>${celeb.profile}</profile>
        <type>${celeb.type}</type>
    </celeb>
    </c:forEach>
</celebs>