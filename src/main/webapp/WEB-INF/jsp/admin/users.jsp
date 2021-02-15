<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">
<h2>My library users</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col"><fmt:message key="users.login"/></th>
        <th scope="col"><fmt:message key="users.name"/></th>
        <th scope="col"><fmt:message key="users.email"/></th>
        <th scope="col"><fmt:message key="users.role"/></th>
        <th scope="col"><fmt:message key="users.fine"/></th>
        <th scope="col"><fmt:message key="users.blocked"/></th>
        <th scope="col"><fmt:message key="users.userLocale"/></th>
        <th scope="col"><fmt:message key="users.Edit"/></th>
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
                        <a href="<c:url value="/controller?command=editUser&userId=${user.id}"/>"><fmt:message key="users.Edit"/></a>
                    </td>
                </c:when>
            </c:choose>

        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>