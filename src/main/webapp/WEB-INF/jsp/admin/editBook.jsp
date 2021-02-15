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

    <form action="<c:url value="/controller?command=editBook"/>" method="post">

        <h1>Edit a book: "${requestScope.book.title}"</h1>
        <p>Please fill in this form to edit this book</p>
        <hr>
        <input type="hidden" name="id" value="${requestScope.book.id}">
        <label for="title"><strong>Title</strong></label>
        <input class="form-control" type="text" placeholder="${requestScope.book.title}" name="title" id="title">

        <label for="author"><strong>Author</strong></label>
        <input class="form-control" type="text" placeholder="${requestScope.book.author}" name="author" id="author">

        <label for="ISBN"><strong>ISBN</strong></label>
        <input class="form-control" type="number" placeholder="${requestScope.book.ISBN}" name="ISBN" id="ISBN" >
        <br>
        <label for="publisher"><strong>Publisher</strong></label>
        <input class="form-control" type="text" placeholder="${requestScope.book.publisher}" name="publisher" id="publisher" >

        <label for="publishingDate">Publishing Date: </label>
        <input class="form-control" type="date" placeholder="${requestScope.book.publishingDate}" name="publishingDate" id="publishingDate" ><br>

        <label for="exampleFormControlTextarea1">Add description in english</label>
        <textarea class="form-control" id="exampleFormControlTextarea1" maxlength="512" placeholder="${requestScope.book.descriptionEn}" name="descriptionEn" rows="3" ></textarea><br>

        <label for="exampleFormControlTextarea2">Add description in ukrainian</label>
        <textarea class="form-control" id="exampleFormControlTextarea2" maxlength="512" placeholder="${requestScope.book.descriptionUa}" name="descriptionUa" rows="3" ></textarea><br>

        <label for="exampleFormControlSelect1">Book language</label>
        <select name="language" class="form-control" id="exampleFormControlSelect1">
            <option selected value="${requestScope.book.language.toLowerCase()}">${requestScope.book.language}</option>
            <option value="ukrainian">Ukrainian</option>
            <option value="english">English</option>
        </select>

        <label for="number"><strong>Number</strong></label>
        <input class="form-control" type="number" placeholder="${requestScope.book.number}" name="number" id="number" >
        <hr>

        <button type="submit" class="btn btn-primary">Next</button>
    </form>
</div>
</body>
</html>
