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
<style>
    <%@include file='/res/car_violations_style.css' %>
    <%@include file='/res/nullstyle.css'%>
</style>
<div class = "cap">
    <h1>Портал учета нарушений ПДД</h1>
</div>
<div class="car-description">
    <div class="component">
        <p class="_header">Автомобиль</p>
    </div>
    <div class="component">
        <div class="flex-content">
            <p class="item">${car.model}</p>
            <p class="item">${car.number}</p>
        </div>
    </div>
    <div class="component">
        <p class="_header">Владелец</p>
    </div>
    <div class="component">
        <p>${car.owner}</p>
    </div>
</div>

<table>
    <c:forEach var="violation" items="${carViolations}">
        <tr>
            <td>
                <div class="component">
                    <div class="flex-container">
                        <div class="flex-item">${violation.fineType}</div>
                        <div class="flex-item">${violation.fineAmount}</div>
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
                </div>
                <div class="component">${violation.date}</div>
                <p class="component">${violation.address}</p>
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
