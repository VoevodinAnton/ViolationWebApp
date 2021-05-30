<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<div class="cap">
    <h1>Портал учета нарушений ПДД</h1>
</div>
<div class="back">
    <c:url var="back" value="/violations"/>
    <a href="${back}">Вернуться в базу данных штрафов</a>
</div>
<h2 class="main-title">База данных автомобилей</h2>
<c:url value="/cars/new" var="add"/>
<div class="parent">
    <form action="${add}">
        <button>Зарегистрировать новый автомобиль</button>
    </form>
</div>
<table>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <div class="flex-container">
                    <div class="flex-item">${car.number}</div>
                    <div class="flex-item">${car.model}</div>
                    <c:url var="carViolations" value="/cars/${car.id}/violations"/>
                    <form action="${carViolations}">
                        <input type="submit" value="Посмотреть штрафы на этот автомобиль" class="input-button"/>
                    </form>
                    <c:url var="update" value="/cars/${car.id}/edit"/>
                    <form action="${update}">
                        <input type="submit" value="Редактировать" class="input-button"/>
                    </form>
                    <a href="#delete-car-dialog-${car.id}" class="input-button">Удалить</a>
                    <div id="delete-car-dialog-${car.id}" class="dark-window">
                        <div class = "popup-window">
                            <p>Вы точно хотите удалить автомобиль</p>
                            <p><b>${car.number}</b>?</p>
                            <br>
                            <div class = "flex-container">
                                <div class="flex-item">
                                    <c:url var="delete" value="/cars/${car.id}"/>
                                    <form action="${delete}" method="Post">
                                        <input name="_method" type="hidden" value="delete"/>
                                        <input type="submit" value="Да" class="input-button"/>
                                    </form>
                                </div>
                                <div class="flex-item">
                                    <a href = "#" class="input-button" style="padding-top: -1%">Нет</a>
                                </div>
                            </div>
                        </div>
                    </div>
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