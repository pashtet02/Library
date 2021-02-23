<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../jspf/directive/header.jsp"/>
    <title>Library</title>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">
    <h2><fmt:message key="books.mainLabel"/></h2>
    <c:choose>
        <c:when test="${requestScope.ordersList.size() == 0}">
            <p><fmt:message key="books.noRecordsYet"/> <a href="<c:url value="/controller?command=catalog"/>"><fmt:message key="books.orderABook"/></a></p>
        </c:when>
        <c:when test="${requestScope.ordersList.size() > 0}">
            <table border="1">
                <thead>
                <tr>
                    <td><fmt:message key="aboutBook.title"/></td>
                    <td><fmt:message key="aboutBook.author"/></td>
                    <td><fmt:message key="books.status"/></td>
                    <td><fmt:message key="books.returnDate"/></td>
                    <td><fmt:message key="books.yourComment"/></td>
                    <td><fmt:message key="books.librariansComment"/></td>
                </tr>
                </thead>
                <c:forEach var="bean" items="${requestScope.ordersList}">

                    <tr>
                        <td>${bean.bookTitle}</td>
                        <td>${bean.bookAuthor}</td>
                        <c:choose>
                            <c:when test="${bean.status == 'APPROVED'}">
                                <td style="color: green"><fmt:message key="books.statusApproved"/></td>
                            </c:when>
                            <c:when test="${bean.status == 'READING_HALL'}">
                                <td style="color: blue"><fmt:message key="books.statusReadingHall"/></td>
                            </c:when>
                            <c:when test="${bean.status == 'REFUSED'}">
                                <td style="color: red"><fmt:message key="books.statusRefused"/></td>
                            </c:when>
                            <c:when test="${bean.status == 'RESERVED'}">
                                <td style="color: #8800ff"><fmt:message key="books.statusReserved"/></td>
                            </c:when>
                            <c:when test="${bean.status == 'RETURNED'}">
                                <td style="color: #002aff"><fmt:message key="books.statusReturned"/></td>
                            </c:when>
                        </c:choose>
                        <td>${bean.returnDate}</td>
                        <td>${bean.userComment}</td>
                        <td>${bean.librarianComment}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>

    <c:if test="${sessionScope.user.fine > 0}">
        <h5><fmt:message key="users.fine"/> <fmt:formatNumber value="${sessionScope.user.fine}"/> UAH</h5>
    </c:if>
</div>
</body>
</html>
