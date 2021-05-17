<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 15.05.2021
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Регистрирование нарушения</title>
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
<h2>Регистрирование правонарушения на автомобиль ${car.number}</h2>
<c:url value="/cars/${car.id}/violations/new" var="var"/>
<form action="${var}" method="POST">
    <div class="flex-content">
        <p class = "item">Вид правонарушения</p>
        <form:select path = "violation.id_fine">
            <c:forEach var="fine" items="${fines}">
                <form:option value="${fine.id}">${fine.type}</form:option>
            </c:forEach>
        </form:select>
    </div>
    <div class="flex-content">
        <p class = "item">Место нарушения</p>
        <input type="text" name="address" placeholder="адрес" value="${violation.address}" class = "item" required>
    </div>
    <div class="flex-content">
        <p class = "item">Дата нарушения</p>
        <input type="text" name="date" placeholder="дата" value="${violation.date}" class = "item" required>
    </div>
    <div class="flex-content">
        <p class="item">Оплачен</p>
        <input type="checkbox" name="status" value="${violation.status}">
    </div>
    <input type="submit" value="Зарегистрировать правонарушение">
</form>
</body>
</html>