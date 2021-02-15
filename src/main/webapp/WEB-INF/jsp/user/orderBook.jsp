<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add book</title>
    <link href="../../../static/custom.css" rel="stylesheet"/>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">
<form method="post" action="<c:url value="/controller"/>">
    <div class="container">
        <h1>Order a book</h1>
        <p>Please fill in this form to order a book</p>
        <p>You can change your name in <a href="<c:url value="/controller?command=viewSettings"/>">your profile</a> </p>
        <hr>
        <input type="hidden" name="command" value="orderBook" />
        <input type="hidden" name="userId" value="${param.userId}" />
        <input type="hidden" name="bookId" value="${param.bookId}" />
        <p><strong>First name: ${sessionScope.user.firstName}</strong></p>
        <hr>
        <%--<input class="form-control" type="text" placeholder="${sessionScope.user.firstName}" name="firstName" id="firstName">--%>

        <p><strong>Second name: ${sessionScope.user.secondName}</strong></p>
        <hr>
        <%--<input autocomplete="false" class="form-control" type="text" placeholder="${sessionScope.user.secondName}" name="secondName" id="secondName">--%>

        <p><strong>Email: ${sessionScope.user.mail}</strong></p>
        <hr>
        <label for="comment"><strong>Comment: </strong></label>
        <textarea class="form-control" placeholder="My comment" name="comment" id="comment" maxlength="250" rows="3" required></textarea><br>

        <br>
        <button type="submit" class="btn btn-primary">Order a book</button>
    </div>
</form>
</div>
</body>
</html>
