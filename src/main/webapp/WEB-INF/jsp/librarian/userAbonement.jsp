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
    <title>Abonement</title>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>

<div class="container">

<h2>${requestScope.usr.username} abonement</h2>
<p>
    User details:
    First name: ${requestScope.usr.firstName} <br>
    Second name: ${requestScope.usr.secondName} <br>
    Mail: ${requestScope.usr.mail} <br>
    Fine: ${requestScope.usr.fine} UAH
</p>
<c:choose>
    <c:when test="${requestScope.ordersList.size() > 0}">
        <table border="1">
            <thead>
            <tr>
                <td>Title</td>
                <td>Author</td>
                <td>Status</td>
                <td>Start date</td>
                <td>Return date</td>
                <td>Librarian`s comment</td>
                <td>Close</td>
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
                        <c:when test="${bean.status == 'RETURNED'}">
                            <td style="color: #002f80">${bean.status}</td>
                        </c:when>
                        <c:when test="${bean.status == 'READING_HALL'}">
                            <td style="color: blue">${bean.status}</td>
                        </c:when>
                        <c:when test="${bean.status == 'REFUSED'}">
                            <td style="color: red"> ${bean.status}</td>
                        </c:when>
                        <c:when test="${bean.status == 'RESERVED'}">
                            <td style="color: yellow"> ${bean.status}</td>
                        </c:when>
                    </c:choose>
                    <c:if test="${bean.startDate != null}">
                        <td>${bean.startDate}</td>
                    </c:if>
                    <c:choose>
                        <c:when test="${bean.returnDate == null}">
                            <td>No return date</td>
                        </c:when>
                        <c:when test="${bean.returnDate != null}">
                            <td>${bean.returnDate}</td>
                        </c:when>
                    </c:choose>
                    <td>${bean.librarianComment}</td>

                    <td>
                        <c:if test="${bean.status == 'APPROVED' || bean.status == 'READING_HALL'}">
                            <form method="post" action="<c:url value="/controller?command=userAbonement&action=return&orderId=${bean.id}"/>">

                                <button class="btn btn-success" data-toggle="modal" data-target="#exampleModal" type="button">
                                    Return
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Return a book</h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                You can not change this action
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary">Return book</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <p>No records found</p>
    </c:otherwise>
</c:choose>
</div>
</body>
</html>
