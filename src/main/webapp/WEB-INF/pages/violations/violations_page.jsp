<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 10.05.2021
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>База данных штрафов</title>
</head>
<body>
<style>
    <%@include file='/res/violations_style.css' %>
</style>
<h2>Поиск штрафов</h2>
<div class="parent">
    <button type="button" name="find">Найти</button><br>
    <form action="/cars">
        <button>Открыть базу данных автомобилей</button>
    </form>
</div>
<table>
    <c:forEach var="violation" items="${violations}">
        <tr>
            <td>
                <div class ="flex-container-parent">
                    <div class ="flex-container">
                        <div class="flex-item"> ${violation.fineType} </div>
                        <div class="flex-item"> ${violation.status} </div>
                        <div class="flex-item"> ${violation.carNumber} </div>
                        <a href="/violations/${violation.id}/edit" class = "flex-item">edit</a>
                        <a href="/violations/${violation.id}/delete" class = "flex-item">delete</a>
                    </div>
                    <p>${violation.fineAmount}</p>
                    <p>${violation.address}</p>
                    <p>${violation.date}</p>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
