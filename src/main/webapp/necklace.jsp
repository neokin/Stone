<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Necklaces</title>
    <link href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" rel="stylesheet">
</head>
<body>
<table id="necklace" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Type</th>
        <th>Name</th>
        <th>Weight</th>
        <th>Cost</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${necklace}" var="stone">
        <tr>
            <td>${stone.type}</td>
            <td>${stone.name}</td>
            <td>${stone.weight}</td>
            <td>${stone.cost}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <input type="radio" name="find" value="cost" checked="checked"> Cost<br>
    <input type="radio" name="find" value="weight"> Weight
</div>
<div>
    <label>Мин:
        <input id='min' placeholder="Введите мин значение">
    </label>
    <label>Макс:
        <input id='max' placeholder="Введите макс значение">
    </label>
</div>
<button id="search">Search at diapason</button>
<script src="<c:url value="/resources/js/lib/jquery-1.12.4.js"/>"></script>
<script src="<c:url value="/resources/js/lib/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/resources/js/necklace.js"/>"></script>
</body>
</html>
