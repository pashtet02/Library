<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>library</title>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<div class="container">
<jsp:include page="../../jspf/directive/navbar.jsp"/>

<h2><fmt:message key="users.mainLabel"/></h2>
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
    <c:forEach var="user" items="${requestScope.usersList}">
        <tr>
            <th scope="row">${user.id}</th>
            <td><c:out value="${user.username}" />
            </td>
            <td><c:out value="${user.firstName} ${user.secondName}" />
            </td>
            <td><c:out value="${user.mail}" />
            </td>
            <td><c:out value="${user.role}" />
            </td>
            <td><c:out value="${user.fine}" />
            </td>
            <td><c:out value="${user.banned}" />
            </td>
            <td><c:out value="${user.userLocale}" />
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