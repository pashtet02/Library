<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Book Catalog</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="card-columns">
    <c:forEach var="book" items="${bookList}">
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title"><c:out value="${book.getTitle()}" /></h5>
            <h6 class="card-subtitle mb-2 text-muted"> <c:out value="${book.getAuthor()}" /></h6>
            <p class="card-text">ISBN: <c:out value="${book.getISBN()}"/><br>
                Publisher: <c:out value="${book.getPublisher()}" /><br>
                Number:  <c:out value="${book.getNumber()}" /></p>
            <a href="#" class="card-link">Замовити</a>
            <a href="#" class="card-link">Детальніше</a>
        </div>
    </div>
        </c:forEach>
</div>

</div>
</body>
</html>
