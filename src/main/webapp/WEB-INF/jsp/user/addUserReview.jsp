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

    <form action="<c:url value="/controller?command=addReview"/>" method="post">

        <h1>Add a review to a book ${requestScope.book.title}</h1>
        <p>Please fill in this form to add a review to catalog</p>
        <hr>
        <input type="hidden" name="bookId" value="${requestScope.book.id}">
        <div class="form-group">
            <label for="exampleFormControlSelect2">Choose your mark</label>
            <select class="form-control" id="exampleFormControlSelect2" name="mark" required>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>

        <label for="userComment"><strong>Comment: </strong></label>
        <textarea class="form-control" placeholder="My comment" name="userComment" id="userComment" maxlength="512" rows="5" required></textarea><br>
        <hr>
        <button type="submit" class="btn btn-primary">Add a review</button>

    </form>
</div>
</body>
</html>
