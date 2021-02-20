<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE>
<html lang="en">
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="static/login.css" rel="stylesheet">
    <jsp:include page="WEB-INF/jspf/directive/header.jsp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" charset="utf-8">
    <title>Library</title>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <form method="post" action="<c:url value="/controller"/>">
            <div class="container">
                <h1><fmt:message key="registration.mainLabel"/></h1>
                <p><fmt:message key="registration.description"/></p>
                <hr>
                <input type="hidden" name="command" value="registration"/>
                <%--<label for="login"><strong><fmt:message key="registration.login"/></strong></label><br>--%>
                <input type="text" required placeholder="<fmt:message key="registration.login"/>" class="fadeIn second" name="login" id="login"
                       pattern="[A-Za-z0-9]{4,20}"><br>
                <c:if test="${requestScope.userExistsError != null}">
                    <label>${requestScope.userExistsError}</label><br>
                </c:if>

                <%--<label for="firstName"><strong><fmt:message key="registration.firstName"/> </strong></label><br>--%>
                <input type="text" required placeholder="<fmt:message key="registration.firstName"/>" class="fadeIn second" name="firstName" id="firstName"><br>

                <%--<label for="secondName"><strong><fmt:message key="registration.secondName"/> </strong></label>--%>
                <input type="text" required placeholder="<fmt:message key="registration.secondName"/>" class="fadeIn second" name="secondName" id="secondName"><br>

                <%--<label for="email"><strong><fmt:message key="registration.email"/></strong></label><br>--%>
                <input type="email" required placeholder="<fmt:message key="registration.email"/>" class="fadeIn second" name="email" id="email">
                <c:if test="${requestScope.emailExistsError != null}">
                    <label>${requestScope.emailExistsError}</label>
                </c:if>
                <br>

                <%--<label for="password"><strong><fmt:message key="registration.password"/></strong></label>--%>
                <input type="password" required placeholder="password" class="fadeIn third" name="password"
                       id="password" pattern="[A-Za-z0-9]{4,32}"><br>
                <small id="passwordHelpBlock" class="form-text text-muted">
                    <fmt:message key="registration.passwordDescription"/>
                </small>

                <%--<label for="password-repeat"><strong><fmt:message key="registration.repeatPassword"/></strong></label>--%>
                <input type="password" required placeholder="<fmt:message key="registration.repeatPassword"/>" class="fadeIn third"
                       name="password-repeat" id="password-repeat" pattern="[A-Za-z0-9]{4,32}"><br>
                <hr>
                <input class="fadeIn fourth" type="submit" value="<fmt:message key="registration.registerButton"/>">

            </div>
        </form>

        <c:if test="${requestScope.errMessage != null}">
            <label for="password-repeat"><strong><fmt:message key="registration.passwordDoesntMatch"/></strong></label>
        </c:if>

        <!-- GO To login page -->
        <div id="formFooter">
            <a class="underlineHover" href="login.jsp"><fmt:message key="registration.alreadyHaveAnAccount"/></a>
        </div>
    </div>
</div>
</body>
</html>






