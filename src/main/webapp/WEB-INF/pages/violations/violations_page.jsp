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
    <%@include file='/res/nullstyle.css'%>
</style>
<div class="cap">
    <h1>Портал учета нарушений ПДД</h1>
</div>
<div class="flex-container">
    <h2 class="main-title" style="margin-right: 80%">Поиск штрафов</h2>
    <form action="#" style="margin-top: 1%">
        <input type="submit" value="Импорт из файла" class="input-button"/>
    </form>
</div>
<c:url value="/violations/search" var="search"/>
<form action="${search}" method="GET">
    <div class="search">
        <h3>По номеру автомобиля</h3>
        <input type="search" name="number" class="number">
        <h3>По виду правонарушения</h3>
        <select name="type">
            <option value=" ">Выберите тип штрафа</option>
            <c:forEach var="fine" items="${fines}">
                <option value="${fine.type}">${fine.type}</option>
            </c:forEach>
        </select>
        <h3>По статусу</h3>
        <input type="radio" name="status" value="1">Только оплаченные<br>
        <input type="radio" name="status" value="0">Только неоплаченные<br>
        <input type="radio" name="status" value=" " checked>Любые<br>
        <div class="parent" style="margin-left: 0; width: 95%">
            <input type="submit" value="Найти" class="input-button" style="width:100%; border-radius: 10px;background-color: #19196c">
        </div>
    </div>
</form>

<div class="parent">
    <form action="/cars">
        <button>Открыть базу данных автомобилей</button>
    </form>
</div>
<table>
    <c:forEach var="violation" items="${violations}">
        <tr>
            <td>
                <div class="flex-container-parent">
                    <div class="flex-container">
                        <div class="flex-item" style="width: 250px"><b>${violation.fineType}</b></div>
                        <div class="flex-item">${violation.status == 0? "Не оплачен": "Оплачен"}</div>
                        <div class="flex-item"> ${violation.carNumber} </div>
                        <c:url var="update" value="/violations/${violation.id}/edit"/>
                        <form action="${update}">
                            <input type="submit" value="Редактировать" class="input-button"/>
                        </form>
                            <a href="#delete-violation-dialog-${violation.id}" class="input-button">Удалить</a>
                            <div id="delete-violation-dialog-${violation.id}" class="dark-window">
                                <div class = "popup-window">
                                    <p>Вы точно хотите удалить штраф</p>
                                    <p><b>${violation.fineType}</b></p>
                                    <p>на автомобиль <b>${violation.carNumber}</b>?</p>
                                    <br>
                                    <div class = "flex-container">
                                        <div class="flex-item">
                                            <c:url var="delete" value="/violations/${violation.id}"/>
                                            <form action="${delete}" method="Post">
                                            <input name="_method" type="hidden" value="delete"/>
                                            <input type="submit" value="Да" class="input-button"/>
                                            </form>
                                        </div>
                                        <div class="flex-item">
                                            <form action="#">
                                                <input type="submit" value="Нет" class="input-button"/>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
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
