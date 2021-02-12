<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <jsp:include page="../../../header.jsp"/>
</head>
<body>
<jsp:include page="../../../navbar.jsp"/>
<div class="container">
<h2>My library users</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">username</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">ROLE</th>
        <th scope="col">Fine</th>
        <th scope="col">Blocked</th>
        <th scope="col">UserLocale</th>
        <th scope="col">Edit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <th scope="row">${user.getId()}</th>
            <td><c:out value="${user.getUsername()}" />
            </td>
            <td><c:out value="${user.getFirstName()} ${user.getSecondName()}" />
            </td>
            <td><c:out value="${user.getMail()}" />
            </td>
            <td><c:out value="${user.getRole()}" />
            </td>
            <td><c:out value="${user.getFine()}" />
            </td>
            <td><c:out value="${user.isBanned()}" />
            </td>
            <td><c:out value="${user.getUserLocale()}" />
            </td>
            <c:choose>
                <c:when test="${sessionScope.role == 'ADMIN'}">
                    <td>
                        <a href="<c:url value="/controller?command=editUser&userId=${user.id}"/>">edit</a>
                    </td>
                </c:when>
            </c:choose>

        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<a href="<c:url value="/controller?command=listOrders"/>">show your orders</a>
</body>
</html>