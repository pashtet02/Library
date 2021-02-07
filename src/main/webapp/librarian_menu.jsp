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
<h1>Hello Librarian!</h1>
<a href="<c:url value='/controller?command=logout' />">Logout</a>
<br/>
<a href="<c:url value="/controller?command=listOrders"/>">menu</a>
</body>
</html>
