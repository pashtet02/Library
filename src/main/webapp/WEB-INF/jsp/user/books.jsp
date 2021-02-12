<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../../header.jsp"/>
    <title>Books</title>
</head>
<body>
<jsp:include page="../../../navbar.jsp"/>
<h2>List of my books</h2>
<table border="1">
    <thead>
    <tr>
        <td>Title</td>
        <td>Author</td>
        <td>Status</td>
        <td>Start date</td>
        <td>Return date</td>
        <c:if test="${user.fine != 0}">
            <td>Fine</td>
        </c:if>
        <td>Librarian`s comment</td>
    </tr>
    </thead>
    <c:forEach var="bean" items="${requestScope.ordersList}">

        <tr>
            <td>${bean.bookTitle}</td>
            <td>${bean.bookAuthor}</td>
            <c:choose>
                <c:when test="${bean.status == 'APPROVED'}">
                    <td style="color: green">${bean.status}</td>
                </c:when>
                <c:when test="${bean.status == 'READING_HALL'}">
                    <td style="color: blue">${bean.status}</td>
                </c:when>
                <c:when test="${bean.status == 'REFUSED'}">
                    <td style="color: red"> ${bean.status}</td>
                </c:when>
            </c:choose>
            <c:if test="${bean.startDate != null}">
                <td>${bean.startDate}</td>
            </c:if>
            <td>${bean.returnDate}</td>
            <td>${bean.librarianComment}</td>
        </tr>
    </c:forEach>
</table>
<c:if test="${user.fine > 0}">
    <h5>Your fine: ${user.fine} UAH</h5>
</c:if>

<c:if test="${role == 'ADMIN'}">
    <a href="<c:url value="/controller?command=addBook"/>">add book</a>
</c:if>
</body>
</html>
