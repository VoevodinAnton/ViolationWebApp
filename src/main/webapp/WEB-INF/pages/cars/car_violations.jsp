<%--
  Created by IntelliJ IDEA.
  User: Антон
  Date: 16.05.2021
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Страница информации об автомобиле </title>
</head>
<body>
<div class = "flex-container">
    <p>Номер</p>
    <div class = "flex-item">${car.number}</div>
    <p>Модель</p>
    <div class = "flex-item">${car.model}</div>
    <p>Владелец </p>
    <div class = "flex-item">${car.owner}</div>
</div>


<table>
    <c:forEach var="violation" items="${carViolations}">
        <tr>
            <td>
                <div class="flex-container">
                    <div class="flex-item">${violation.date}</div>
                    <div class="flex-item">${violation.status}</div>
                    <c:url var="update" value="/cars/${car.id}/violations/${violation.id}/edit"/>
                    <form action="${update}">
                        <input type="submit" value="Редактировать"/>
                    </form>
                    <c:url var="delete" value="/cars/${car.id}/violations/${violation.id}"/>
                    <form action="${delete}" method="Post">
                        <input name="_method" type="hidden" value="delete">
                        <input type="submit" value="Удалить"/>
                    </form>
                </div>
                <p>Адрес</p>
                <p>${violation.address}</p>
            </td>
        </tr>
    </c:forEach>
</table>
<c:url value="/cars/${car.id}/violations/new" var="add"/>
<div class="parent">
    <form action="${add}">
        <button>Зарегистрировать штраф на этот автомобиль</button>
    </form>
</div>
</body>
</html>
