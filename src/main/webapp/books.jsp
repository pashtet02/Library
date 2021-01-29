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
<h2>My library books list</h2>
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>ISBN</th>
        <th>Publisher</th>
        <th>Number</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${userBookList}">
    <tr>
        <td><c:out value="${book.getId()}" />
        </td>
        <td><c:out value="${book.getTitle()}" />
        </td>
        <td><c:out value="${book.getAuthor()}" />
        </td>
        <td><c:out value="${book.getISBN()}" />
        </td>
        <td><c:out value="${book.getPublisher()}" />
        </td>
        <td><c:out value="${book.getNumber()}" />
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${role == 'LIBRARIAN'}">
    <a href="users.jsp">to users list</a>
    <br>
    <a href="addBook.jsp">add book</a>
</c:if>

<a href="main.jsp">to main page</a>

</body>
</html>
