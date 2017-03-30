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
    <tfoot>
    <tr>
        <th>Type</th>
        <th>Name</th>
        <th>Weight</th>
        <th>Cost</th>
    </tr>
    </tfoot>
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
<a href="test" target="_blank">Test Button</a>
<script src="<c:url value="/resources/js/lib/jquery-1.12.4.js"/>"></script>
<script src="<c:url value="/resources/js/lib/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="/resources/js/necklace.js"/>"></script>
</body>
</html>
