<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container mt-5">
    <fmt:setBundle basename="global" var="glo"/>
    <fmt:setLocale value="RU"/>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/library">Library</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/library/catalog?page=1">Catalog</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/usermenu"/>">My books</a>
                </li>
                <c:if test="${sessionScope.role == 'LIBRARIAN'}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/menu"/>">Users list</a>
                    </li>
                </c:if>
            </ul>
            <div class="navbar-text mr-3">
                <label for="languageSelect">Choose a language</label>
                <select class="nav-control" id="languageSelect">
                    <option>English <fmt:setLocale value="en-EN"/></option>
                    <option>Українська <fmt:setLocale value="ru-RU"/></option>
                </select>
            </div>

            <div class="navbar-text mr-3">
                <a class="nav-link" href="/library/logout">${login}</a>
            </div>
        </div>
    </nav>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>


