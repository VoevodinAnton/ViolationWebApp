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

<h2>Cars</h2>
<table>
    <tr>
        <th>id</th>
        <th>number</th>
        <th>model</th>
        <th>owner</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>${car.id}</td>
            <td>${car.number}</td>
            <td>${car.model}</td>
            <td>${car.owner}</td>
            <td>
                <a href="/cars/${car.id}/edit">edit</a>
                <a href="/cars/${car.id}/delete/">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/cars/new" var="add"/>
<a href="${add}">Add new film</a>
</body>
</html>