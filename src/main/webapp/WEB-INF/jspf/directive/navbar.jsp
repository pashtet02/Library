<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container mt-5">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="<c:url value="/controller?command=catalog&page=1"/>"><fmt:message key="global.siteName"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/controller?command=catalog&page=1"/>"><fmt:message key="global.booklist"/><br/>  </a>
                </li>
                <c:if test="${sessionScope.role != null}">
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${sessionScope.role == 'LIBRARIAN'}">
                                <a class="nav-link" href="<c:url value="/controller?command=librarianMenu"/>"><fmt:message key="global.librarianActiveOrders"/></a>
                            </c:when>
                            <c:when test="${sessionScope.role == 'USER'}">
                                <a class="nav-link" href="<c:url value="/controller?command=listOrders"/>"><fmt:message key="global.myBooksLabel"/></a>
                            </c:when>
                        </c:choose>
                    </li>
                </c:if>

                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/controller?command=usersList"/>"><fmt:message key="global.usersList" /></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.role == 'LIBRARIAN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/controller?command=usersList"/>"><fmt:message key="global.usersList" /></a>
                    </li>
                </c:if>
            </ul>

    <div class="navbar-text mr-3">
        <c:choose>
            <c:when test="${sessionScope.login != null}">
                <div class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle waves-effect" id="navbarDropdownMenuLink4" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="true">
                        <i class="united kingdom flag m-0">${sessionScope.login}</i>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="<c:url value="/controller?command=viewSettings"/>">My profile</a>
                        <a class="dropdown-item" href="<c:url value="/controller?command=logout"/>">Logout</a>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
            <a class="nav-link" href="<c:url value="/login.jsp"/>"><fmt:message key="global.signIn"/></a>
            </c:otherwise>
        </c:choose>
                </div>
        </div>
    </nav>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


