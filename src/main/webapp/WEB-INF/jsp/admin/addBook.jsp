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

        <h1><fmt:message key="addBook.mainLabel"/></h1>
        <p><fmt:message key="addBook.description"/></p>
        <hr>

        <label for="title"><b><fmt:message key="aboutBook.title"/></b></label>
        <input class="form-control" type="text" placeholder="<fmt:message key="addBook.title"/>" name="title" id="title" required>

        <label for="author"><b><fmt:message key="aboutBook.author"/></b></label>
        <input class="form-control" type="text" placeholder="<fmt:message key="addBook.author"/>" name="author" id="author" required>

        <label for="ISBN"><b>ISBN</b></label>
        <input class="form-control" type="number" placeholder="<fmt:message key="addBook.ISBN"/>" name="ISBN" id="ISBN" required>
        <br>
        <label for="publisher"><b><fmt:message key="aboutBook.publisher"/></b></label>
        <input class="form-control" type="text" placeholder="<fmt:message key="addBook.publisher"/>" name="publisher" id="publisher" required>

        <label for="publishingDate"><fmt:message key="aboutBook.publishingDate"/>: </label>
        <input class="form-control" type="date" placeholder="<fmt:message key="addBook.publishingDate"/>" name="publishingDate" id="publishingDate" required><br>

        <label for="exampleFormControlTextarea1"><fmt:message key="aboutBook.description"/></label>
        <textarea class="form-control" id="exampleFormControlTextarea1" maxlength="512" name="description" rows="3" required></textarea><br>

        <label for="exampleFormControlSelect1"><fmt:message key="aboutBook.language"/></label>
        <select name="language" class="form-control" id="exampleFormControlSelect1">
            <option value="ukrainian"><fmt:message key="addbook.ukrainian"/></option>
            <option value="english"><fmt:message key="addbook.english"/></option>
        </select>

        <label for="number"><b><fmt:message key="addbook.number"/></b></label>
        <input class="form-control" type="number" placeholder="<fmt:message key="addbook.number"/>" name="number" id="number" required>
        <hr>

        <button type="submit" class="btn btn-primary"><fmt:message key="addbook.nextPage"/></button>

    </form>
</div>
</body>
</html>
