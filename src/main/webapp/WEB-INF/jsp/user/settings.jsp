<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <c:set var="title" value="Settings" scope="page"/>
    <%@ include file="../../jspf/directive/header.jsp" %>
    <link href="static/aboutBook.css" rel="stylesheet">

</head>


<body>
<div class="container">
    <%@ include file="../../jspf/directive/navbar.jsp" %>

    <div class="main-body">
        <form id="settings_form" action="controller" method="post">
            <input type="hidden" name="command" value="updateSettings"/>


            <div class="row gutters-sm">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <div class="card-body">
                            <div class="d-flex flex-column align-items-center text-center">
                                <img src="view/images.png" alt="..." class="rounded-circle" width="150">
                                <div class="mt-3">
                                    <h4>${sessionScope.user.username}</h4>
                                    <p class="text-secondary mb-1">${sessionScope.user.mail}</p>
                                    <input type="submit" class="btn button-primary"
                                           value='<fmt:message key="settings_jsp.button.update"/>'><br/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <form>

                </form>
                <div class="col-md-8">
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="settings_jsp.label.first_name"/>:</h6>
                                </div>

                            </div>
                            <div>
                                <input class="col-sm-9 text-secondary form-control" name="firstName"
                                       placeholder="${sessionScope.user.firstName}">
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0"><fmt:message key="settings_jsp.label.last_name"/>:</h6>
                                </div>

                            </div>
                            <div>
                                <input class="col-sm-9 text-secondary form-control" name="lastName"
                                       placeholder="${sessionScope.user.secondName}">
                            </div>
                            <hr>
                            <div>
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Locale:</h6>
                                </div>
                                <select class="form-control" name="localeToSet">
                                    <c:choose>
                                        <c:when test="${not empty defaultLocale}">
                                            <option value="">${defaultLocale}[Default]</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value=""/>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:forEach var="localeName" items="${locales}">
                                        <option value="${localeName}">${localeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Fine:</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    <c:choose>
                                        <c:when test="${sessionScope.user.fine > 0}">
                                            ${sessionScope.user.fine} UAH <a href="user_menu.jsp">pay</a>
                                        </c:when>
                                        <c:when test="${sessionScope.user.fine <= 0}">
                                            You don`t have any fines.
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>