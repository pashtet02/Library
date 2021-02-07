<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container mt-5">
    <fmt:setBundle basename="global" var="glo"/>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="<c:url value="/controller?command=catalog&page=1"/>"><fmt:message key="global.siteName" bundle="${glo}"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/controller?command=catalog&page=1"/>"><fmt:message key="global.booklist" bundle="${glo}"/><br/>  </a>
                </li>
                <c:if test="${sessionScope.role != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/controller?command=listOrders"/>"><fmt:message key="global.myBooksLabel" bundle="${glo}"/></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.role == 'ADMIN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/controller?command=usersList"/>"><fmt:message key="global.usersList" bundle="${glo}"/></a>
                    </li>
                </c:if>
            </ul><%--
            <div class="navbar-text mr-3">
                <label for="languageSelect"><fmt:message key="global.chooseLanguage" bundle="${glo}"/></label>
                <select class="nav-control" id="languageSelect">
                    <option>Українська</option>
                    <option>English</option>
                </select>
            </div>--%>

    <div class="navbar-text mr-3">
        <c:choose>
            <c:when test="${login != null}">
              <a class="nav-link" href="<c:url value="/controller?command=logout"/>">${login}</a>
            </c:when>
            <c:otherwise>
            <a class="nav-link" href="<c:url value="/login.jsp"/>"><fmt:message key="global.signIn" bundle="${glo}"/></a>
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


