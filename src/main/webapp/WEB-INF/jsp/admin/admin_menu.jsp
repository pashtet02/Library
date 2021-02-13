<%--
  Author: Pavel Ravvich.
  Date: 29/10/2017.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="../../jspf/directive/header.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<h1>Hello Librarian, ${sessionScope.user.username}!</h1>
<a href="<c:url value='/logout' />">Logout</a>
<br/>
<a href="<c:url value="/controller?command=librarianMenu"/>">orders list</a>
<a href="<c:url value="/controller?command=addBook"/>">add book</a>
</body>
</html>
