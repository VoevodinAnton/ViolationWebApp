<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Cars</title>
</head>
<body>
<style>
    <%@include file='/res/cars_style.css' %>
    <%@include file='/res/nullstyle.css'%>
</style>
<div class = "cap">
    <h1>Портал учета нарушений ПДД</h1>
</div>
<div class="back">
<c:url var="back" value="/violations"/>
<a href="${back}">Вернуться в базу данных штрафов</a>
</div>
<h2 class="main-title">База данных автомобилей</h2>
<c:url value="/cars/new" var="add"/>
<div class = "parent">
    <form action="${add}" >
        <button>Зарегистрировать новый автомобиль</button>
    </form>
</div>
<table>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
            <div class = "flex-container">
                <div class = "flex-item">${car.number}</div>
                <div class = "flex-item">${car.model}</div>
                <c:url var="carViolations" value="/cars/${car.id}/violations"/>
                <form action="${carViolations}">
                    <input type="submit" value="Посмотреть штрафы на этот автомобиль" />
                </form>
                <c:url var="update" value="/cars/${car.id}/edit"/>
                <form action="${update}">
                    <input type="submit" value="Редактировать" />
                </form>
                <c:url var="delete" value="/cars/${car.id}"/>
                <form action="${delete}" method="Post">
                    <input name="_method" type="hidden" value="delete">
                    <input type="submit" value="Удалить" />
                </form>
            </div>
            <p>Владелец</p>
            <p>${car.owner}</p>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>