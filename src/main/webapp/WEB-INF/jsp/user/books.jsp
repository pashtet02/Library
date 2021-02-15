<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../jspf/directive/header.jsp"/>
    <title>Books</title>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">
    <h2>List of my books</h2>
    <c:choose>
        <c:when test="${requestScope.ordersList.size() == 0}">
            <p>You dont have any orders yet, but you can <a href="<c:url value="/controller?command=catalog"/>">order a book</a></p>
        </c:when>
        <c:when test="${requestScope.ordersList.size() > 0}">
            <table border="1">
                <thead>
                <tr>
                    <td>Title</td>
                    <td>Author</td>
                    <td>Status</td>
                    <td>Return date</td>
                    <td>Your comment</td>
                    <td>Librarian`s comment</td>
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
                                <td style="color: #8800ff"> ${bean.status}</td>
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
        <h5>Your fine: ${sessionScope.user.fine} UAH</h5>
    </c:if>
</div>
</body>
</html>
