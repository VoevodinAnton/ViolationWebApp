<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 06.05.2021
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <c:choose>
        <c:when test="${empty car.number}">
            <title>Add</title>
        </c:when>
        <c:otherwise>
            <title>New car</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<style>
    <%@include file='/res/edit-new-style.css' %>
    <%@include file='/res/nullstyle.css'%>
</style>
<div class="cap">
    <h1>Портал учета нарушений ПДД</h1>
</div>
<div class="content">
    <div class="back">
        <c:url var="back" value="/cars"/>
        <a href="${back}">Вернуться в базу данных автомобилей</a>
    </div>
    <h2 class="main-title">Регистрация автомобиля</h2>
    <c:url value="/cars/new" var="addUrl"/>
    <form action="${addUrl}" name="car" method="POST">
        <form:errors path="*" element="div"/>
        <div class="flex-content">
            <p class="item">Номер автомобиля</p>
            <input type="text"  name="number" placeholder="A000AA777" value="${car.number}" class="input-field">
            <form:errors path="car.number"/>
        </div>
        <div class="flex-content">
            <p class="item">Модель</p>
            <input type="text" name="model" placeholder="Модель автомобиля" value="${car.model}" class="input-field">
            <form:errors path="car.model"/>
        </div>
        <div class="flex-content">
            <p class="item">Владелец</p>
            <input type="text" name="owner" value="${car.owner}" class="input-field">
            <form:errors path="car.owner"/>
        </div>
        <input type="submit" value="Зарегистрировать автомобиль" class="input-button">
    </form>
</div>
</body>
</html>

