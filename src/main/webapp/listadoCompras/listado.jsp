<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Lista de Compras</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./style/style.css">
    <script type="text/javascript" src="./js/compras.js"></script>
</head>

<body>
<div class="container mt-3">
    <h3>Lista de Compras</h3>
    <div id="iError" class="d-none alert alert-danger"></div>

    <form id="iForm" action="javascript:void(null)" method="post" onsubmit="save(event)">
        <div class="d-flex flex-row">
            <input type="text" id="iData" name="item" class="form-control me-3" placeholder="Añadir artículo" required autofocus>
            <button type="submit" class="btn btn-warning">Añadir</button>
        </div>
    </form>

    <ul id="iList" class="list-group mt-3">
        <c:forEach var="it" items="${items}" varStatus="status">
            <jsp:include page="item.jsp">
                <jsp:param name="nroItem" value="${it.nroItem}"/>
                <jsp:param name="item" value="${it.descItem}"/>
            </jsp:include>
        </c:forEach>
    </ul>
</div>
</body>

</html>