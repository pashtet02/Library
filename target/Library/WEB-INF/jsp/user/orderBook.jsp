<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xml:lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order book</title>
    <link href="../../../static/custom.css" rel="stylesheet"/>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">
<form method="post" action="<c:url value="/controller"/>">
    <div class="container">
        <h1><fmt:message key="orderBook.mainLabel"/></h1>
        <p><fmt:message key="orderBook.description"/></p>
        <p><fmt:message key="orderBook.youCanChangeName"/> <a href="<c:url value="/controller?command=viewSettings"/>"><fmt:message key="orderBook.yourProfile"/></a> </p>
        <hr>
        <input type="hidden" name="command" value="orderBook" />
        <input type="hidden" name="userId" value="${param.userId}" />
        <input type="hidden" name="bookId" value="${param.bookId}" />
        <p><strong><fmt:message key="userAbonement.firstNAme"/> ${sessionScope.user.firstName}</strong></p>
        <hr>

        <p><strong><fmt:message key="userAbonement.secondNAme"/> ${sessionScope.user.secondName}</strong></p>
        <hr>

        <p><strong>Email: ${sessionScope.user.mail}</strong></p>
        <hr>
        <label for="comment"><strong><fmt:message key="orderBook.Comment"/> </strong></label>
        <textarea class="form-control" placeholder="My comment" name="comment" id="comment" maxlength="250" rows="3" required></textarea><br>

        <br>

        <button class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" type="button">
            <fmt:message key="orderBook.modal.OrderButton"/>
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="orderBook.modal.OrderButton"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <fmt:message key="orderBook.modal.doYouWantToOrder"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="orderBook.modal.closeButton"/></button>
                        <button type="submit" class="btn btn-success"><fmt:message key="orderBook.modal.OrderButton"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</div>
</body>
</html>
