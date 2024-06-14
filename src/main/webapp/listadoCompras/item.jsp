<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${empty nroItem}">
    <c:set var="nroItem" value="${param.nroItem}"></c:set>
</c:if>

<li id="${nroItem}" class="list-group-item d-flex justify-content-between align-item-center">
    <span class="ms-2 align-self-center">${param.item}</span>
    <button type="button" class="btn btn-primary" onclick="remove(${nroItem})">Eliminar</button>
</li>
