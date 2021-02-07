<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Add book</title>
    <link href="static/custom.css" rel="stylesheet"/>
</head>
<body>
<form method="post" action="<c:url value="/controller"/>">
    <div class="container">
        <h1>Order a book</h1>
        <p>Please fill in this form to order a book</p>
        <hr>
        <input type="hidden" name="command" value="orderBook" />
        <input type="hidden" name="userId" value="${param.userId}" />
        <input type="hidden" name="bookId" value="${param.bookId}" />
        <label for="firstName"><b>First name</b></label>
        <input type="text" placeholder="Enter first name" name="firstName" id="firstName" required>

        <label for="secondName"><b>Second name</b></label>
        <input type="text" placeholder="Enter second name" name="secondName" id="secondName" required>

        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Enter email" name="email" id="email">
        <br>
        <label for="comment"><b>Comment: </b></label>
        <input type="text" placeholder="My comment" name="comment" id="comment" required>

        <br>
        <button type="submit" class="registerbtn">Order a book</button>
    </div>
</form>
</body>
</html>
