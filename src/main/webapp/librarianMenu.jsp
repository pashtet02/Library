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
        <form method="post" action="<c:url value="/controller?command=librarianMenu"/>">
            <div class="card">
                <h5 class="card-header">Order #${bean.id} from ${bean.username} ${bean.userFirstName} ${bean.userSecondName}</h5>
                <div class="card-body">
                    <h5 class="card-title">Book: ${bean.bookTitle}, available: ${bean.booksNumber}</h5>
                    <p class="card-text">Order time: ${bean.startDate}</p>
                    <c:if test="${bean.userComment != null}">
                        <p class="card-text">${bean.username}`s comment:
                                ${bean.userComment}</p>
                    </c:if>

                    <label for="librarianComment">Your comment:</label>
                    <input type="text" name="librarianComment" placeholder="Enter your comment here" id="librarianComment"/><br>
                    <label for="returnDate">Return to:</label>
                    <input type="date" placeholder="Enter date" name="returnDate" id="returnDate" required><br>
                    <input type="hidden" name="orderId" value="${bean.id}"/>

                    <button type="submit" class="btn btn-success" value="APPROVED" name="action">Accept</button>
                    <button type="submit" class="btn btn-danger" value="REFUSED" name="action">Refuse</button>
                    <button type="submit" class="btn btn-primary" value="READING_HALL" name="action">Allow in reading hall</button>
                </div>
            </div>
        </form>
    </c:forEach>
</div>
</body>
</html>
