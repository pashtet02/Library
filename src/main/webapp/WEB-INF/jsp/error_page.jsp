<%--
  Created by IntelliJ IDEA.
  User: Pashtet
  Date: 03.02.2021
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>No such URL</h1>

<c:if test="${not empty errorMessage and empty exception and empty code}">
    <h3>Error message: ${errorMessage}</h3>
</c:if>

<%-- this way we print exception stack trace --%>
<c:if test="${not empty exception}">
    <hr/>
    <h3>Stack trace:</h3>
    <c:forEach var="stackTraceElement" items="${exception.stackTrace}">
        ${stackTraceElement}
    </c:forEach>
</c:if>


<a class="navbar-brand" href="/library">Go to the main page</a>
</body>
</html>
