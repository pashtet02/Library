<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp"/>
</head>

<body>
<jsp:include page="navbar.jsp"/>
<h1>Hello USER!</h1>

<a href="<c:url value="/logout"/>">Logout</a>
<br/>
<a href="<c:url value="/usermenu"/>">usermenu</a>
</body>
</html>
