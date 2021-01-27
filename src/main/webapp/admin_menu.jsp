<%--
  Author: Pavel Ravvich.
  Date: 29/10/2017.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h1>Hello ADMIN!</h1>
<a href="<c:url value='/logout' />">Logout</a>
<br/>
<a href="<c:url value="/menu"/>">menu</a>
</body>
</html>
