<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Library</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="static/custom.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<form method="post" action="/library/first/adminmenu?id=${requestScope.id}">
    <p><c:out value="${requestScope.id}"/></p>
    <div class="container">
        <h2>Edit user: <c:out value="${requestScope.get(u)}"/></h2>
        <p>Please fill in this form to edit a user account</p>
        <hr>

        <label for="fine"><b>Fine</b></label>
        <input type="number" placeholder="Enter fine" name="fine" id="fine">

        <label for="role"><b>Role</b></label>
        <input type="text" placeholder="Enter role" name="role" id="role" pattern="^(USER)$|^(ADMIN)$|^(LIBRARIAN)$">
        <br>

        <label for="isBanned"><b>Ban a user?</b></label>
        <input type="checkbox" placeholder="yes" name="isBanned" id="isBanned" >
        <hr>

        <button type="submit" class="registerbtn">Done</button>
    </div>
    <div class="container signin">
        <p>Back to users list <a href="/library/first/menu">go!</a>.</p>
    </div>
</form>
</body>
</html>

