<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 06.05.2021
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <c:choose>
        <c:when test="${empty car.number}">
            <title>Add</title>
        </c:when>
        <c:otherwise>
            <title>Edit</title>
        </c:otherwise>
    </c:choose>
</head>
<body>
<style media="screen">
    body{
        margin-left: 10%;
    }
    .flex-content{
        display: flex;
        margin: 5px;
    }
    .item{
        margin: 0 5px;
        padding-right: 20px;
    }
</style>
<h2>Регистрация автомобиля</h2>
<c:url value="/cars/new" var="addUrl"/>
<form action="${addUrl}" name="car" method="POST">
<div class="flex-content">
    <p class = "item">Номер автомобиля</p>
    <input type="text" name = "number" placeholder="number" value = "${car.number}" class = "item" REQUIRED>
</div>
<div class="flex-content">
    <p class = "item">Модель</p>
    <input type="text" name="model" placeholder="model" value="${car.model}" class = "item" required>
</div>
<div class="flex-content">
    <p class = "item">Владелец</p>
    <input type="text" name="owner" value="${car.owner}" class = "item" required>
</div>
    <input type="submit" value="Зарегистрировать автомобиль">
</form>
</body>
</html>

