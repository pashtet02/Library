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
    <title><fmt:message key="userAbonement.abonement"/></title>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>

<div class="container">

    <h2>${requestScope.usr.username} <fmt:message key="userAbonement.abonement"/></h2>
    <p>
        <fmt:message key="userAbonement.userDetails"/>
        <fmt:message key="userAbonement.firstNAme"/> ${requestScope.usr.firstName} <br>
        <fmt:message key="userAbonement.secondNAme"/> ${requestScope.usr.secondName} <br>
        <fmt:message key="userAbonement.mail"/> ${requestScope.usr.mail} <br>
        <fmt:message key="userAbonement.fine"/> <fmt:formatNumber value="${requestScope.usr.fine}" currencyCode="UAH"/> UAH
    </p>
    <c:choose>
        <c:when test="${requestScope.ordersList.size() > 0}">
            <table border="1">
                <thead>
                <tr>
                    <td><fmt:message key="aboutBook.title"/></td>
                    <td><fmt:message key="aboutBook.author"/></td>
                    <td><fmt:message key="books.status"/></td>
                    <td><fmt:message key="userAbonement.startDate"/></td>
                    <td><fmt:message key="books.returnDate"/></td>
                    <td><fmt:message key="books.librariansComment"/></td>
                    <td><fmt:message key="userAbonement.returnButton"/></td>
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
                        <c:if test="${bean.startDate != null}">
                            <td>${bean.startDate}</td>
                        </c:if>
                        <c:choose>
                            <c:when test="${bean.returnDate == null}">
                                <td><fmt:message key="userAbonement.noReturnDate"/></td>
                            </c:when>
                            <c:when test="${bean.returnDate != null}">
                                <td>${bean.returnDate}</td>
                            </c:when>
                        </c:choose>
                        <td>${bean.librarianComment}</td>

                        <td>
                            <c:if test="${bean.status == 'APPROVED' || bean.status == 'READING_HALL'}">
                                <form method="post"
                                      action="<c:url value="/controller?command=userAbonement&action=return&orderId=${bean.id}"/>">

                                    <button class="btn btn-success" data-toggle="modal" data-target="#exampleModal"
                                            type="button">
                                        <fmt:message key="userAbonement.returnButton"/>
                                    </button>

                                    <!-- Modal -->
                                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel"><fmt:message
                                                            key="userAbonement.modal.buttonAccept"/></h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <fmt:message key="userAbonement.modal.cantChangeAction"/>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary"
                                                            data-dismiss="modal"><fmt:message
                                                            key="userAbonement.modal.buttonClose"/></button>
                                                    <button type="submit" class="btn btn-primary"><fmt:message
                                                            key="userAbonement.modal.buttonAccept"/></button>
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
            <p><fmt:message key="userAbonement.noRecords"/></p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
