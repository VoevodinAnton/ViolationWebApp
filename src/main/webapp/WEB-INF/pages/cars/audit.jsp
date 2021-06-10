<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>История изменений</title>
    <style>
        <%@include file='/res/cars_style.css' %>
        <%@include file='/res/nullstyle.css'%>
    </style>
</head>
<body>
<div class="cap">
    <h1>Портал учета нарушений ПДД</h1>
</div>
<div class="back">
    <c:url var="back" value="/cars"/>
    <a href="${back}">Вернуться в базу автомобилей</a>
</div>
    <h2 class="main-title">История изменений автомобиля ${car.number}</h2>
<div style="margin-left: 5%; margin-right: 5%">
        <c:forEach var="auditRow" items="${auditList}">
            <div class = "foreachItem">
            <div class = "flex-container">
                <p style="margin-right: 5%"><b>${auditRow.typeOfEdit}</b></p>
                <p>${auditRow.dateEdit}</p>
            </div>
            <c:choose>
                <c:when test="${!auditRow.typeOfEdit.equals('Добавлен')}">
                    <c:choose>
                        <c:when test="${auditRow.oldNumber != auditRow.newNumber}">
                            <p>${auditRow.oldNumber} -- ${auditRow.newNumber}</p>
                        </c:when>
                    </c:choose>
                    <c:choose>
                        <c:when test="${auditRow.oldOwner != auditRow.newOwner}">
                            <p>${auditRow.oldOwner} -- ${auditRow.newOwner}</p>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
            </div>
            <br>
        </c:forEach>
</div>
</body>
</html>
