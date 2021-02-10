<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Abonement</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>${requestScope.usr.username} abonement</h2>
<p>
    User details:
    First name: ${requestScope.usr.firstName} <br>
    Second name: ${requestScope.usr.secondName} <br>
    Mail: ${requestScope.usr.mail} <br>
    Fine: ${requestScope.usr.fine} UAH
</p>
<table border="1">
    <thead>
    <tr>
        <td>Title</td>
        <td>Author</td>
        <td>Status</td>
        <td>Start date</td>
        <td>Return date</td>
        <td>Librarian`s comment</td>
        <td>Close</td>
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
                <c:when test="${bean.status == 'RESERVED'}">
                    <td style="color: yellow"> ${bean.status}</td>
                </c:when>
            </c:choose>
            <c:if test="${bean.startDate != null}">
                <td>${bean.startDate}</td>
            </c:if>
            <c:choose>
                <c:when test="${bean.returnDate == null}">
                    <td>No return date</td>
                </c:when>
                <c:when test="${bean.returnDate != null}">
                    <td>${bean.returnDate}</td>
                </c:when>
            </c:choose>
            <td>${bean.librarianComment}</td>
            <td><a href="<c:url value="/controller?command=userAbonement&action=return&orderId=${bean.id}"/>">return</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
