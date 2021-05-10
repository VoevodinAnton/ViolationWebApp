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
<h2>Поиск штрафов</h2>
<table>
    <c:forEach var="violation" items="${violations}">
        <tr>
            <td>
                <div class ="flex-container-parent">
                    <div class ="flex-container">
                        <div class="flex-item"> ${violation.type_of_fine} </div>
                        <div class="flex-item"> ${violation.status} </div>
                        <div class="flex-item"> ${violation.number_of_car} </div>
                        <div class="flex-item"> Удалить </div>
                        <div class="flex-item"> Редактировать </div>
                    </div>
                    <p>500 рублей<br></p>
                    <p>Самара</p>
                    <p>21.03.2021</p>
                </div>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
