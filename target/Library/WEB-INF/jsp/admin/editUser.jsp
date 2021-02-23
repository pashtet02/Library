<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../jspf/directive/header.jsp"/>
    <title>Library</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../../../static/custom.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>

<form method="post" action="<c:url value="/controller?command=editUser&id=${param.userId}"/>">
    <p><c:out value="${requestScope.id}"/></p>
    <div class="container">
        <h2><fmt:message key="editUser.mainLabel"/></h2>
        <p><fmt:message key="editUser.description"/></p>
        <hr>

        <label for="role"><strong><fmt:message key="editUser.role"/></strong></label>
        <input type="text" placeholder="Enter role" name="role" id="role" pattern="^(USER)$|^(ADMIN)$|^(LIBRARIAN)$">
        <br>

        <label for="exampleFormControlSelect1"><fmt:message key="editUser.banAUser"/></label>
        <select class="form-control" id="exampleFormControlSelect1" name="isBanned">
            <option value="false"><fmt:message key="editUser.no"/></option>
            <option value="true"><fmt:message key="editUser.yes"/></option>
        </select>

        <hr>
        <!-- Button trigger modal -->
        <button class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" type="button">
            <fmt:message key="editBook.nextBook"/>
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="editUser.changeUser"/></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <fmt:message key="editUser.AreYouSure"/>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="editUser.close"/></button>
                        <button type="submit" class="btn btn-primary"><fmt:message key="editUser.saveChangesButton"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container signin">
        <p><fmt:message key="editUser.backToUsers"/><a href="<c:url value="/controller?command=usersList"/>"><fmt:message key="editUser.labelDesc"/></a></p>
    </div>
</form>
</body>
</html>

