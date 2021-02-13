<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add book</title>
    <link href="/webapp/static/catalog.css" rel="stylesheet"/>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">

    <form action="<c:url value="/controller?command=addBook"/>" method="post">

        <h1>Add a book</h1>
        <p>Please fill in this form to add a book to catalog</p>
        <hr>

        <label for="title"><b>Title</b></label>
        <input class="form-control" type="text" placeholder="Enter title" name="title" id="title" required>

        <label for="author"><b>Author</b></label>
        <input class="form-control" type="text" placeholder="Enter author" name="author" id="author" required>

        <label for="ISBN"><b>ISBN</b></label>
        <input class="form-control" type="number" placeholder="Enter ISBN" name="ISBN" id="ISBN" required>
        <br>
        <label for="publisher"><b>Publisher</b></label>
        <input class="form-control" type="text" placeholder="Enter publisher" name="publisher" id="publisher" required>

        <label for="publishingDate">Publishing Date: </label>
        <input class="form-control" type="date" placeholder="Enter publishing date" name="publishingDate" id="publishingDate" required><br>

        <label for="exampleFormControlTextarea1">Add description</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" maxlength="512" name="description" rows="3" required></textarea><br>

        <label for="exampleFormControlSelect1">Book language</label>
        <select name="language" class="form-control" id="exampleFormControlSelect1">
            <option value="ukrainian">Ukrainian</option>
            <option value="english">English</option>
        </select>

        <label for="number"><b>Number</b></label>
        <input class="form-control" type="number" placeholder="Enter number of books" name="number" id="number" required>
        <hr>

        <button type="submit" class="btn btn-primary">Next</button>

    </form>
</div>
</body>
</html>
