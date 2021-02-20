<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE>
<html lang="en">
<head>
    <title>Library</title>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">
    <h2><fmt:message key="users.listOfReaders"/></h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="users.login"/></th>
            <th scope="col"><fmt:message key="users.name"/></th>
            <th scope="col"><fmt:message key="users.email"/></th>
            <th scope="col"><fmt:message key="users.fine"/></th>
            <th scope="col"><fmt:message key="users.blocked"/></th>
            <th scope="col"><fmt:message key="users.profile"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${requestScope.readersList}">
            <tr>
                <td><c:out value="${user.username}"/>
                </td>
                <td><c:out value="${user.firstName} ${user.secondName}"/>
                </td>
                <td><c:out value="${user.mail}"/>
                </td>
                <td><c:out value="${user.fine}"/>
                </td>
                <td><c:choose>
                    <c:when test="${user.banned == true}">
                        <fmt:message key="editUser.yes"/>
                    </c:when>
                    <c:when test="${user.banned == false}">
                        <fmt:message key="editUser.no"/>
                    </c:when>
                </c:choose>
                </td>
                <c:if test="${sessionScope.role == 'LIBRARIAN'}">
                    <td>
                        <a href="<c:url value="/controller?command=userAbonement&userId=${user.id}"/>"><fmt:message
                                key="users.profile"/></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>