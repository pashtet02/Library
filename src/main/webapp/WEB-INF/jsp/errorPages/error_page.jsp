<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE>
<html lang="en">
<head>
    <jsp:include page="../../jspf/directive/header.jsp"/>
    <title>Error</title>
</head>
<body>
<h1></h1>
<main class="flex-shrink-0">
    <div class="container">
        <jsp:include page="../../jspf/directive/navbar.jsp"/>

        <h1 class="mt-5">Oops...</h1>
        <p class="lead">An unexpected error occured:</p>
        <c:if test="${not empty requestScope.errorMessage and empty exception and empty code}">
        <h3>Error message: ${requestScope.errorMessage}</h3>
        </c:if>


        <c:choose>
            <c:when test="${sessionScope.user.username == null}">
                <a class="navbar-brand" href="<c:url value="/login.jsp"/>">login please</a>
            </c:when>
            <c:otherwise>
                <a class="navbar-brand" href="<c:url value="/"/>">Go to the main page</a>
            </c:otherwise>
        </c:choose>
    </div>
</main>
</body>
</html>
