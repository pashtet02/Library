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
    <title>Success</title>
</head>
<body>
<h1>Success!!!</h1>

<c:if test="${requestScope.message != null}">
    <h3>${message}</h3>
</c:if>
<c:if test="${requestScope.fileMessage != null}">
    <h3>${fileMassage}</h3>
</c:if>
<a class="navbar-brand" href="<c:url value="/"/>">Go to the main page</a>
</body>
</html>
