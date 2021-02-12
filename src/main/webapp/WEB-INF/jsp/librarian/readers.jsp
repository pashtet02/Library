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
        <th scope="col">Login</th>
        <th scope="col">Name</th>
        <th scope="col">Email</th>
        <th scope="col">Fine</th>
        <th scope="col">Blocked</th>
        <th scope="col">Profile</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.readersList}">
        <tr>
            <td><c:out value="${user.username}" />
            </td>
            <td><c:out value="${user.firstName} ${user.secondName}" />
            </td>
            <td><c:out value="${user.mail}" />
            </td>
            <td><c:out value="${user.fine}" />
            </td>
            <td><c:out value="${user.banned}" />
            </td>
            <c:if test="${sessionScope.role == 'LIBRARIAN'}">
                <td>
                    <a href="<c:url value="/controller?command=userAbonement&userId=${user.id}"/>">abonement</a>
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>