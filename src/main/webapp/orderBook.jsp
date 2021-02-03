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
<form method="post" action="${pageContext.request.contextPath}/order?userId=${param.userId}&bookId=${param.bookId}">
    <div class="container">
        <h1>Order a book</h1>
        <p>Please fill in this form to order a book</p>
        <hr>

        <label for="firstName"><b>First name</b></label>
        <input type="text" placeholder="Enter first name" name="firstName" id="firstName" required>

        <label for="secondName"><b>Second name</b></label>
        <input type="text" placeholder="Enter second name" name="secondName" id="secondName" required>

        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Enter email" name="email" id="email">
        <br>
        <label for="phoneNumber"><b>Enter your phone number please to remember you to return book</b></label>
        <input type="tel" placeholder="Enter it without country code" name="phoneNumber" id="phoneNumber" required>

        <br>
        <label for="returnDate"><b>I will return this book to: </b></label>
        <input type="date" placeholder="Enter date" name="returnDate" id="returnDate" required>

        <br>
        <button type="submit" class="registerbtn">Order a book</button>
    </div>
</form>
</body>
</html>
