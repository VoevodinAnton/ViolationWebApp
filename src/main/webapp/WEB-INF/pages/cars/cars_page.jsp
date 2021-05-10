<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 06.05.2021
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<style>
    <%@include file='/res/cars_style.css' %>
</style>
<h2>База данных автомобилей</h2>
<table>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
            <div class = "flex-container">
                <div class = "flex-item">${car.number}</div>
                <div class = "flex-item">${car.model}</div>
                <a href="/cars/${car.id}/edit">edit</a>
                <a href="/cars/${car.id}/delete/">delete</a>
            </div>
            <p>Владелец</p>
            <p>${car.owner}</p>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/cars/new" var="add"/>
<a href="${add}">Add new film</a>
</body>
</html>