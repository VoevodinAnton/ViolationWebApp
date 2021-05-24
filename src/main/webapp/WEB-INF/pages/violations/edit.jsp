<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 15.05.2021
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Редактирование нарушения</title>
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
        <c:url var="back" value="/violations"/>
        <a href="${back}">Вернуться в базу данных правонарушений</a>
    </div>
    <h2 class="main-title">Редактирование правонарушения</h2>
    <c:url value="/violations/${violation.id}" var="var"/>
    <form action="${var}" method="POST">
        <form:errors path="*" element="div"/>
        <div class="flex-content">
            <p class="item">Номер</p>
            <form:select path="violation.id_car">
                <c:forEach var="car" items="${cars}">
                    <form:option value="${car.id}">${car.number}</form:option>
                    <form:errors path="violation.id_car"/>
                </c:forEach>
            </form:select>
        </div>
        <div class="flex-content">
            <p class="item">Вид правонарушения</p>
            <form:select path="violation.id_fine">
                <c:forEach var="fine" items="${fines}">
                    <form:option value="${fine.id}">${fine.type}</form:option>
                    <form:errors path="violation.id_fine"/>
                </c:forEach>
            </form:select>
        </div>
        <div class="flex-content">
            <p class="item">Место нарушения</p>
            <input type="text" name="address" placeholder="адрес" value="${violation.address}" class="input-field">
            <form:errors path="violation.address"/>
        </div>
        <div class="flex-content">
            <p class="item">Дата нарушения</p>
            <input type="text" name="date" placeholder="ГГГГ-ММ-ДД" value="${violation.date}" class="input-field">
            <form:errors path="violation.date"/>
        </div>
        <div class="flex-content">
            <p class="item">Оплачен</p>
            <form:select path="violation.status">
                <form:option value="1">Да</form:option>
                <form:option value="0">Нет</form:option>
            </form:select>
        </div>
        <input type="submit" value="Редактировать правонарушение" class="input-button">
    </form>
</body>
</html>
