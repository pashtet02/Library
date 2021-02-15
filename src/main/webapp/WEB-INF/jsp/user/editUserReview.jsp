<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add book</title>
    <link href="<c:url value="/static/catalog.css"/>" rel="stylesheet"/>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">

    <form action="<c:url value="/controller?command=editReview"/>" method="post">

        <h1>Edit a book: "${requestScope.book.title}"</h1>
        <p>Please fill in this form to edit this book</p>
        <hr>
        <input type="hidden" name="id" value="${requestScope.review.id}">
        <label for="title"><strong>Mark</strong></label>
        <select name="mark" class="form-control" id="exampleFormControlSelect1">
            <option selected value="1">1</option>
            <option value="${requestScope.review.mark}">${requestScope.review.mark}</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
        </select>
        <input class="form-control" type="text" placeholder="${requestScope.review.mark}" name="title" id="title">

        <label for="author"><strong>Comment</strong></label>
        <textarea class="form-control" placeholder="${requestScope.review.mark}" rows="3" name="author" id="author"></textarea>
        <hr>

        <button type="submit" class="btn btn-primary">Edit</button>
    </form>
</div>
</body>
</html>
