<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>My library users</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">username</th>
        <th scope="col">Email</th>
        <th scope="col">ROLE</th>
        <th scope="col">Fine</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <th scope="row">${user.getId()}</th>
            <td><c:out value="${user.getUsername()}" />
            </td>
            <td><c:out value="${user.getMail()}" />
            </td>
            <td><c:out value="${user.getRole()}" />
            </td>
            <td><c:out value="${user.getFine()}" />
            </td>
            <td>
                <a href="/library/adminmenu?id=${user.id}">edit</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<a href="books.jsp">show all books</a>
</body>
</html>