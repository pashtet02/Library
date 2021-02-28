<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE>
<html lang="en">
<head>
    <title>Book Catalog</title>
    <jsp:include page="WEB-INF/jspf/directive/header.jsp"/>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="static/catalog.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <jsp:include page="WEB-INF/jspf/directive/navbar.jsp"/>

    <c:set var="command" value="catalog" scope="request"/>
    <div class="form-row">
        <div class="form-group col md-6">
            <div class="container">
                <form method="post" action="<c:url value="/controller"/>" class="form-inline" accept-charset="UTF-8">
                    <input type="hidden" name="command" value="catalog"/>
                    <input required type="text" name="filter" id="filter">
                    <div class="btn-group">
                        <button type="submit" name="filterParam" value="${sessionScope.filterParam}"
                                class="btn btn-primary"><fmt:message key="global.SearchByButton"/>
                            <c:choose>
                                <c:when test="${sessionScope.filterParam == 'title'}">
                                    <fmt:message key="global.searchByTitle"/>
                                </c:when>
                                <c:when test="${sessionScope.filterParam == 'author'}">
                                    <fmt:message key="global.searchByAuthor"/>
                                </c:when>
                            </c:choose>
                            </button>
                        <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="sr-only">Toggle Dropdown</span>
                        </button>
                        <div class="dropdown-menu">
                            <button class="dropdown-item" type="submit" name="filterParam" value="title"><fmt:message key="aboutBook.title"/></button>
                            <button class="dropdown-item" type="submit" name="filterParam" value="author"><fmt:message key="aboutBook.author"/></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="form-row">
            <form method="get" action="<c:url value="/controller"/>" class="form-inline" accept-charset="UTF-8">
                <input type="hidden" name="command" value="catalog"/>
                <label for="sort"><fmt:message key="global.sortByLabel"/></label>
                <select id="sort" name="sort">
                    <option value="title"><fmt:message key="global.sortParamTitle"
                    /></option>
                    <option value="author"><fmt:message key="global.sortParamAuthor"/></option>
                    <option value="publisher"><fmt:message key="global.sortParamPublisher"/></option>
                    <option value="publishingDate"><fmt:message key="aboutBook.publishingDate"/></option>
                </select>
                <input type="submit" value="<fmt:message key="global.sortButton"  />">
            </form>
        </div>
    </div>

    <div class="card-columns">
        <c:choose>
            <c:when test="${requestScope.book == null}">
                <c:forEach var="book" items="${requestScope.listPagedBooks}">
                    <div class="row row-cols-1 row-cols-md-3 g-4">
                        <div class="col">
                            <div class="card h-100">
                                <img src="view/${book.image}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title"><c:out value="${book.title}"/></h5>
                                    <h6 class="card-subtitle mb-2 text-muted"><c:out value="${book.author}"/></h6>
                                    <p class="card-text"> <fmt:message key="aboutBook.publishingDate"/>: <c:out value="${book.publishingDate}"/><br>
                                        <fmt:message key="global.bookPublisher"/> <c:out
                                                value="${book.publisher}"/><br>
                                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                                        <fmt:message key="global.booksNumber"/> <c:out
                                                value="${book.number}"/><br>
                                        </c:if>
                                        <fmt:message key="global.bookLanguage"/> <c:out
                                                value="${book.language}"/></p>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.role == 'USER'}">
                                            <a href="<c:url value="/controller?command=orderBook&userId=${sessionScope.userId}&bookId=${book.id}"/>"
                                               class="card-link"><fmt:message key="global.orderBookButton"/></a>
                                        </c:when>
                                        <c:when test="${sessionScope.user.role == 'ADMIN'}">
                                            <a href="<c:url value="/controller?command=editBook&bookId=${book.id}"/>"
                                               class="card-link"><fmt:message key="global.editButton"/></a>
                                        </c:when>
                                    </c:choose>
                                    <a href="<c:url value="/controller?command=catalog&bookid=${book.id}"/>"
                                       class="card-link"><fmt:message key="global.bookDetails"/></a>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
                <c:if test="${requestScope.listPagedBooks.size() == 0}">
                    <p><fmt:message key="global.noBookFound"/></p>
                </c:if>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="global.noBookFound"/></p>
            </c:otherwise>
        </c:choose>
    </div>
    <div style="text-align: center">
        <nav aria-label="hello">
            <ul class="pagination">
                <c:if test="${sessionScope.page > 1}">
                    <a class="page-link"
                       href="<c:url value="/controller?command=catalog&page=${sessionScope.page - 1}"/>"><fmt:message key="catalog.previous"/></a>
                </c:if>
                <c:if test="${requestScope.listPagedBooks.size() == 6}">
                    <a class="page-link"
                       href="<c:url value="/controller?command=catalog&page=${sessionScope.page + 1}"/>"><fmt:message key="catalog.next"/></a>
                </c:if>
            </ul>
        </nav>
    </div>
</div>

</body>
</html>
