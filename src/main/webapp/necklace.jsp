<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Necklaces</title>
    <link href="<c:url value="/resources/css/jquery.dataTables.min.css"/>" rel="stylesheet">
    <style>
        #stones, #necklace {
            padding: 5px;
            text-align: center;

        }

        .hide {
            display: none
        }
    </style>
</head>
<body>
<table id="stones" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th class="hide">Id</th>
        <th>Type</th>
        <th>Name</th>
        <th>Weight</th>
        <th>Cost</th>
        <th>Add</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${stones}" var="stone">
        <tr id="row-stone-id-${stone.id}">
            <td class="hide">${stone.id}</td>
            <td>${stone.type}</td>
            <td>${stone.name}</td>
            <td>${stone.weight}</td>
            <td>${stone.cost}</td>
            <td><button class="necklace-add" data-stone-id="${stone.id}">Add to necklace</button> </td>

        </tr>

    </c:forEach>
    </tbody>
</table>

<fieldset>
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
<button id="search">Search at diapason</button><br>
</fieldset>


<div style="text-align: center;"><b>Отобранные камни для ожерелья </b>${necklace.name}</div>
<div class="hide" id="necklace-id">${necklace.id}</div>
<table id="necklace" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Type</th>
        <th>Name</th>
        <th>Weight</th>
        <th>Cost</th>
        <th>Delete</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${necklace.stonesList}" var="stone">
        <tr>
            <td>${stone.type}</td>
            <td>${stone.name}</td>
            <td>${stone.weight}</td>
            <td>${stone.cost}</td>
            <td><button class="necklace-delete" data-stone-id="${stone.id}">Delete from necklace</button> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<bh />
<div>
    <div>
      <%--  <label id = "cost">Цена ожерелья: </label> ${necklace.getCost()}--%>
          <label id = "cost">Цена ожерелья: ${necklace.getCost()} </label>
    </div>
    <div>
        <label id = "weight">Вес ожерелья: ${necklace.getWeight()} </label>
    </div>
</div>
<script src="<c:url value="/resources/js/lib/jquery.min.js"/>"></script>


<script src="<c:url value="/resources/js/lib/jquery-1.12.4.js"/>"></script>
<script src="<c:url value="/resources/js/lib/jquery.dataTables.min.js"/>"></script>

<script src="<c:url value="/resources/js/necklace.js"/>"></script>

</body>
</html>
