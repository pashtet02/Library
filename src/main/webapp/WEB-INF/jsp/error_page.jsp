<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Oops...</h1>

<c:if test="${not empty requestScope.errorMessage and empty exception and empty code}">
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

<c:choose>
    <c:when test="${sessionScope.user.username == null}">
        <a class="navbar-brand" href="<c:url value="/login.jsp"/>">login please</a>
    </c:when>
    <c:otherwise>
        <a class="navbar-brand" href="<c:url value="/"/>">Go to the main page</a>
    </c:otherwise>
</c:choose>
</body>
</html>
