<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="header.jsp"/>
    <title>Books</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container pt-4">
    <h2>List of active orders</h2>
    <c:forEach var="bean" items="${requestScope.ordersList}">
        <div class="card">
            <h5 class="card-header">Order #${bean.id} from ${bean.username} ${bean.userSecondName}</h5>
            <div class="card-body">
                <h5 class="card-title">Book: ${bean.bookTitle}, available: ${bean.booksNumber}</h5>
                <p class="card-text">Order time: ${bean.startDate}</p>
                <c:if test="${bean.comment != null}">
                    <p class="card-text">${bean.username}`s comment:
                            ${bean.comment}</p>
                </c:if>
                <label for="returnDate">Return to:</label>
                <input type="date" placeholder="Enter date" name="returnDate" id="returnDate"><br>
                <a href="<c:url value="/controller?command=librarianMenu&action=APPROVED&orderId=${bean.id}"/>" class="btn btn-primary">Accept</a>
                <a href="<c:url value="/controller?command=librarianMenu&action=REFUSED&orderId=${bean.id}"/>" class="btn btn-primary">Refuse</a>
                <a href="<c:url value="/controller?command=librarianMenu&action=READING_HALL&orderId=${bean.id}"/>" class="btn btn-primary">Allow in reading hall</a>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
