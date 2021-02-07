<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<head>
    <title>Book Catalog</title>
    <jsp:include page="header.jsp"/>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

        .pagination a:hover:not(.active) {
            background-color: #ddd;
        }
    </style>

</head>
<div class="container pt-4">
    <jsp:include page="navbar.jsp"/>
    <fmt:setBundle basename="global" var="glo"/>
    <c:set var="command" value="catalog" scope="request"/>

    <div class="form-row">
        <div class="form-group col md-6">
            <form method="post" action="<c:url value="/controller"/>" class="form-inline" accept-charset="UTF-8">
                <input type="hidden" name="command" value="catalog"/>
                <input type="text" name="filter" id="filter"
                       placeholder="<fmt:message key="global.searchByAuthorPlaceholder" bundle="${glo}"/>">
                <button type="submit" class="btn btn-primary ml-2"><fmt:message key="global.searchButton"
                                                                                bundle="${glo}"/></button>
            </form>


        </div>
        <div class="form-row">
            <form method="get" action="<c:url value="/controller"/>" class="form-inline" accept-charset="UTF-8">
                <input type="hidden" name="command" value="catalog"/>
                <label for="sort"><fmt:message key="global.sortByLabel" bundle="${glo}"/></label>
                <select id="sort" name="sort">
                    <option value="title" selected><fmt:message key="global.sortParamTitle"
                                                                bundle="${glo}"/></option>
                    <option value="author"><fmt:message key="global.sortParamAuthor" bundle="${glo}"/></option>
                    <option value="publisher"><fmt:message key="global.sortParamPublisher"
                                                           bundle="${glo}"/></option>
                    <option value="publishingDate">Publishing date</option>
                </select>
                <input type="submit" value="<fmt:message key="global.sortButton" bundle="${glo}"/>">
            </form>
        </div>

    </div>

    <div class="card-columns">
        <c:choose>

            <c:when test="${requestScope.book == null}">
                <c:forEach var="book" items="${listPagedBooks}">
                    <div class="card">
                   <%-- <div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                        <img
                                src="view/testicon.png"
                                class="img-fluid"
                        />
                        <a href="#!">
                            <div class="mask" style="background-color: rgba(251, 251, 251, 0.15)"></div>
                        </a>
                    </div>--%>
                    <div class="card-body">
                    <h5 class="card-title"><c:out value="${book.getTitle()}"/></h5>
                    <h6 class="card-subtitle mb-2 text-muted"><c:out value="${book.getAuthor()}"/></h6>
                    <p class="card-text">ISBN: <c:out value="${book.getISBN()}"/><br>
                        <fmt:message key="global.bookPublisher" bundle="${glo}"/> <c:out
                                value="${book.getPublisher()}"/><br>
                        <fmt:message key="global.booksNumber" bundle="${glo}"/> <c:out
                                value="${book.getNumber()}"/><br>
                        <fmt:message key="global.bookLanguage" bundle="${glo}"/> <c:out
                                value="${book.getLanguage()}"/></p>
                        <c:if test="${sessionScope.userId > 0 && sessionScope.userId != null}">
                            <a href="<c:url value="/controller?command=orderBook&userId=${sessionScope.userId}&bookId=${book.getId()}"/>"
                               class="card-link"><fmt:message key="global.orderBookButton" bundle="${glo}"/></a>
                        </c:if>
                <a href="<c:url value="/controller?command=catalog&bookid=${book.getId()}"/>"
                   class="card-link"><fmt:message key="global.bookDetails" bundle="${glo}"/></a>
                </div>
                </div>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope.book.title != null}">
            <div class="card">
                <%--<div class="bg-image hover-overlay ripple" data-mdb-ripple-color="light">
                    <img src="view/testicon.png" class="img-fluid"/>
                    <a href="#!">
                        <div class="mask" style="background-color: rgba(251, 251, 251, 0.15)"></div>
                    </a>
                </div>--%>
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${requestScope.book.getTitle()}"/></h5>
                    <h6 class="card-subtitle mb-2 text-muted"><c:out
                            value="${requestScope.book.getAuthor()}"/></h6>
                    <p class="card-text">ISBN: <c:out value="${requestScope.book.getISBN()}"/><br>
                        <fmt:message key="global.bookPublisher" bundle="${glo}"/> <c:out
                                value="${requestScope.book.getPublisher()}"/><br>
                        <fmt:message key="global.booksNumber" bundle="${glo}"/> <c:out
                                value="${requestScope.book.getNumber()}"/><br>
                        <fmt:message key="global.bookLanguage" bundle="${glo}"/> <c:out
                                value="${requestScope.book.getLanguage()}"/></p>
                    <c:if test="${sessionScope.userId > 0  && sessionScope.userId != null}">
                        <a href="<c:url value="/controller?command=orderBook&userId=${sessionScope.userId}&bookId=${requestScope.book.getId()}"/>"
                           class="card-link"><fmt:message key="global.orderBookButton" bundle="${glo}"/></a>
                    </c:if>
                    <a href="<c:url value="/controller?command=catalog&bookid=${requestScope.book.getId()}"/>"
                       class="card-link"><fmt:message key="global.bookDetails" bundle="${glo}"/></a>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <fmt:message key="global.noBookFound" bundle="${glo}"/>
        </c:otherwise>
        </c:choose>
    </div>
</
>


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
</div>
</body>
</html>
