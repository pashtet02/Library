<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="header.jsp"/>
    <title>Books</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>List of my books</h2>
<table border="1">
    <thead>
    <tr>
        <td>Order id</td>
        <td>Title</td>
        <td>Author</td>
        <c:if test="${ordersList.get(0).startDate != null}">
            <td>Start date</td>
        </c:if>
        <td>Return date</td>
        <td>Status</td>
    </tr>
    </thead>
    <c:forEach var="bean" items="${ordersList}">

        <% int i = 0;%>
        <tr>
            <td>${bean.id}</td>
            <td>${booksOrderList.get(i).title}</td>
            <td>${booksOrderList.get(i).author}</td>
            <c:if test="${bean.startDate != null}">
                <td>${bean.startDate}</td>
            </c:if>
            <td>${bean.returnDate}</td>
            <td>${bean.status}</td>
        </tr>
        <%i++;%>
    </c:forEach>
</table>

<c:if test="${user.fine > 0}">
    <h5>Your fine: ${user.fine} UAH</h5>
</c:if>

<c:if test="${role == 'ADMIN'}">
    <a href="/library/controller?command=addBook">add book</a>
</c:if>
</body>
</html>
