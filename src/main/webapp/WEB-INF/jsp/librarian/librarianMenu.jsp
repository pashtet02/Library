<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <jsp:include page="../../jspf/directive/header.jsp"/>
    <title>Books</title>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container pt-4">
    <h2><fmt:message key="librarianMenu.listOfOrdersLabel"/></h2>
    <c:choose>
        <c:when test="${requestScope.ordersList.size() > 0}">
            <c:forEach var="bean" items="${requestScope.ordersList}">
                <form method="post" action="<c:url value="/controller?command=librarianMenu"/>">
                    <div class="card">
                        <h5 class="card-header"><fmt:message key="librarianMenu.order"/> #${bean.id} <fmt:message key="librarianMenu.from"/> ${bean.username} ${bean.userFirstName} ${bean.userSecondName}</h5>
                        <div class="card-body">
                            <h5 class="card-title"><fmt:message key="librarianMenu.book"/> ${bean.bookTitle}, <fmt:message key="librarianMenu.availableLabel"/> ${bean.booksNumber}</h5>
                            <p class="card-text"><fmt:message key="librarianMenu.orderTime"/> ${bean.startDate}</p>
                            <c:if test="${bean.userComment != null}">
                                <p class="card-text">${bean.username}<fmt:message key="librarianMenu.usersComment"/>
                                        ${bean.userComment}</p>
                            </c:if>

                            <label for="librarianComment"><fmt:message key="librarianMenu.librarianComment"/></label>
                            <textarea class="form-control" placeholder="<fmt:message key="librarianMenu.librarianCommentPlaceholder"/>" name="librarianComment" id="librarianComment" rows="2" maxlength="250"></textarea><br>
                            <label for="returnDate"><fmt:message key="librarianMenu.returnTo"/></label>
                            <input class="form-control" type="date" placeholder="Enter date" name="returnDate" id="returnDate" required><br>
                            <input type="hidden" name="orderId" value="${bean.id}"/>

                            <button type="submit" class="btn btn-success" value="APPROVED" name="action"><fmt:message key="librarianMenu.acceptButton"/></button>
                            <button type="submit" class="btn btn-danger" value="REFUSED" name="action"><fmt:message key="librarianMenu.refuseButton"/></button>
                            <button type="submit" class="btn btn-primary" value="READING_HALL" name="action"><fmt:message key="librarianMenu.readingHallButton"/></button>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </c:when>
        <c:when test="${requestScope.ordersList.size() == 0}">
            <p><fmt:message key="librarianMenu.noOrders"/></p>
        </c:when>
    </c:choose>
</div>
</body>

</html>
