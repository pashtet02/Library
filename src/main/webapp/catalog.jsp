<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book Catalog</title>
    <jsp:include page="header.jsp"/>
    <meta charset="utf-8">

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

    <style>
        .pagination {
            display: inline-block;
        }

        .pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
        }

        .pagination a.active {
            background-color: #4CAF50;
            color: white;
        }

        .pagination a:hover:not(.active) {background-color: #ddd;}
    </style>

</head>
<body>
<jsp:include page="navbar.jsp"/>
<c:set var="command" value="catalog" scope="request"/>

<div class="form-row">
    <div class="form-group col md-6">
        <form method="get" action="<c:url value="/controller"/>" class="form-inline" accept-charset="UTF-8">
            <input type="hidden" name="command" value="catalog" />
            <input type="text" name="filter" id="filter" placeholder="Search by title">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>


    </div>
    <div class="form-row">
        <form method="get" action="<c:url value="/controller"/>" class="form-inline" accept-charset="UTF-8">
            <input type="hidden" name="command" value="catalog" />
            <label for="sort">Sort by</label>
            <select id="sort" name="sort">
                <option value="title"selected>Title</option>
                <option value="author">Author</option>
                <option value="publisher">Publisher</option>
                <option value="publishingDate">Publishing date</option>
            </select>
            <input type="submit" value="Send">
        </form>
    </div>

</div>

<div class="card-columns">
    <c:choose>
        <c:when test="${requestScope.book == null}">
            <c:forEach var="book" items="${listPagedBooks}">
                <div class="card" style="width: 18rem;">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${book.getTitle()}"/></h5>
                        <h6 class="card-subtitle mb-2 text-muted"><c:out value="${book.getAuthor()}"/></h6>
                        <p class="card-text">ISBN: <c:out value="${book.getISBN()}"/><br>
                            Publisher: <c:out value="${book.getPublisher()}"/><br>
                            Number: <c:out value="${book.getNumber()}"/><br>
                            Language: <c:out value="${book.getLanguage()}"/></p>
                        <c:if test="${sessionScope.userId > 0 && sessionScope.userId != null}">
                            <a href="<c:url value="/order?userId=${sessionScope.userId}&bookId=${book.getId()}"/>" class="card-link">Замовити</a>
                        </c:if>
                        <a href="<c:url value="/catalog?bookid=${book.getId()}"/>" class="card-link">Детальніше</a>
                    </div>
                </div>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope.book.title != null}">
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${requestScope.book.getTitle()}"/></h5>
                    <h6 class="card-subtitle mb-2 text-muted"><c:out value="${requestScope.book.getAuthor()}"/></h6>
                    <p class="card-text">ISBN: <c:out value="${requestScope.book.getISBN()}"/><br>
                        Publisher: <c:out value="${requestScope.book.getPublisher()}"/><br>
                        Number: <c:out value="${requestScope.book.getNumber()}"/><br>
                        Language: <c:out value="${requestScope.book.getLanguage()}"/></p>
                    <c:if test="${sessionScope.userId > 0  && sessionScope.userId != null}">
                        <a href="<c:url value="/order?userId=${sessionScope.userId}&bookId=${requestScope.book.getId()}"/>" class="card-link">Замовити</a>
                    </c:if>
                    <a href="<c:url value="/catalog?bookid=${requestScope.book.getId()}"/>" class="card-link">Детальніше</a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:out value="oops... there is no books found"/>
        </c:otherwise>
    </c:choose>
</div>
</div>


<div class="pagination">
    <c:if test="${sessionScope.page > 1}">
        <a href="<c:url value="/controller?command=catalog&page=${sessionScope.page - 1}"/>">&laquo;</a>
    </c:if>
    <a href="<c:url value="/controller?command=catalog&page=1"/>">1</a>
    <a href="<c:url value="/controller?command=catalog&page=2"/>">2</a>
    <a href="<c:url value="/controller?command=catalog&page=3"/>">3</a>
    <c:if test="${listPagedBooks.size() == 6}">
        <a href="<c:url value="/controller?command=catalog&page=${sessionScope.page + 1}"/>">&raquo;</a>
    </c:if>
</div>
</body>
</html>
