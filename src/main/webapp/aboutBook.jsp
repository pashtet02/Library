<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="WEB-INF/jspf/directive/header.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="utf-8">
    <link href="static/aboutBook.css" rel="stylesheet">
    <title>About book</title>
</head>
<body>
<div class="container">
    <jsp:include page="WEB-INF/jspf/directive/navbar.jsp"/>
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="view/${requestScope.book.image}" alt="..." class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${requestScope.book.title}</h4>
                                <p class="text-secondary mb-1">${requestScope.book.author}</p>
                                <c:choose>
                                    <c:when test="${sessionScope.user.role == 'USER'}">
                                        <button class="btn btn-primary">Order</button>
                                    </c:when>
                                    <c:when test="${sessionScope.user.role == 'ADMIN'}">
                                        <a href="<c:url value="/controller?command=editBook&bookId=${requestScope.book.id}"/>">Edit</a>
                                    </c:when>
                                    <c:otherwise>
                                        <p><fmt:message key="aboutBook.SignInLabel"/><br></p>
                                        <button class="btn btn-outline-primary"><fmt:message key="global.signIn"/></button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="aboutBook.title"/>:</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${requestScope.book.title}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="aboutBook.author"/>:</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${requestScope.book.author}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="aboutBook.publisher"/>:</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${requestScope.book.publisher}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="aboutBook.publishingDate"/>:</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${requestScope.book.publishingDate}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="aboutBook.language"/>:</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${requestScope.book.language}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="aboutBook.description"/>:</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <c:choose>
                                    <c:when test="${requestScope.book.language == 'english'}">
                                        ${requestScope.book.descriptionEn}
                                    </c:when>
                                    <c:when test="${requestScope.book.language == 'ukrainian'}">
                                        ${requestScope.book.descriptionUa}
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

