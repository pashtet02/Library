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
        <hr>
        <input type="hidden" name="command" value="orderBook" />
        <input type="hidden" name="userId" value="${param.userId}" />
        <input type="hidden" name="bookId" value="${param.bookId}" />
        <label for="firstName"><strong>First name</strong></label>
        <input class="form-control" type="text" placeholder="${sessionScope.user.firstName}" name="firstName" id="firstName">

        <label for="secondName"><strong>Second name</strong></label>
        <input class="form-control" type="text" placeholder="${sessionScope.user.secondName}" name="secondName" id="secondName">

        <label for="email"><strong>Email</strong></label>
        <input  class="form-control" type="email" placeholder="${sessionScope.user.mail}" name="email" id="email">
        <br>
        <label for="comment"><strong>Comment: </strong></label>
        <textarea class="form-control" placeholder="My comment" name="comment" id="comment" maxlength="250" rows="3" required></textarea><br>

        <br>
        <button type="submit" class="btn btn-primary">Order a book</button>
    </div>
</form>
</div>
</body>
</html>
