<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

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

        <h1><fmt:message key="editBook.mainLabel"/> "${requestScope.book.title}"</h1>
        <p><fmt:message key="editBook.description"/></p>
        <hr>
        <input type="hidden" name="id" value="${requestScope.book.id}">
        <label for="title"><strong><fmt:message key="aboutBook.title"/></strong></label>
        <input class="form-control" type="text" placeholder="${requestScope.book.title}" name="title" id="title">

        <label for="author"><strong><fmt:message key="aboutBook.author"/></strong></label>
        <input class="form-control" type="text" placeholder="${requestScope.book.author}" name="author" id="author">

        <label for="ISBN"><strong><fmt:message key="addBook.ISBN"/></strong></label>
        <input class="form-control" type="number" placeholder="${requestScope.book.ISBN}" name="ISBN" id="ISBN">
        <br>
        <label for="publisher"><strong><fmt:message key="aboutBook.publisher"/></strong></label>
        <input class="form-control" type="text" placeholder="${requestScope.book.publisher}" name="publisher"
               id="publisher">

        <label for="publishingDate"><fmt:message key="aboutBook.publishingDate"/>: </label>
        <input class="form-control" type="date" placeholder="${requestScope.book.publishingDate}" name="publishingDate"
               id="publishingDate"><br>

        <label for="exampleFormControlTextarea1"><fmt:message key="editBook.descriptionEN"/></label>
        <textarea class="form-control" id="exampleFormControlTextarea1" maxlength="512"
                  placeholder="${requestScope.book.descriptionEn}" name="descriptionEn" rows="3"></textarea><br>

        <label for="exampleFormControlTextarea2"><fmt:message key="editBook.descriptionUA"/></label>
        <textarea class="form-control" id="exampleFormControlTextarea2" maxlength="512"
                  placeholder="${requestScope.book.descriptionUa}" name="descriptionUa" rows="3"></textarea><br>

        <label for="exampleFormControlSelect1"><fmt:message key="aboutBook.language"/></label>
        <select name="language" class="form-control" id="exampleFormControlSelect1">
            <option selected value="${requestScope.book.language.toLowerCase()}">${requestScope.book.language}</option>
            <option value="ukrainian"><fmt:message key="addbook.ukrainian"/></option>
            <option value="english"><fmt:message key="addbook.english"/></option>
        </select>

        <label for="number"><strong><fmt:message key="addbook.number"/></strong></label>
        <input class="form-control" type="number" placeholder="${requestScope.book.number}" name="number" id="number">
        <hr>

        <button type="submit" class="btn btn-primary"><fmt:message key="editBook.nextBook"/></button>
    </form>

    <form action="<c:url value="/controller?command=deleteBook&bookId=${requestScope.book.id}"/>" method="post">
        <button class="btn btn-danger" data-toggle="modal" data-target="#exampleModal"
                type="button">
           Delete
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Delete this book</h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        You can not return back this action
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary"
                                data-dismiss="modal"><fmt:message
                                key="userAbonement.modal.buttonClose"/></button>
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

</div>
</body>
</html>
