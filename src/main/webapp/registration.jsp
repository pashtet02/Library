<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
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
                <h1>Register</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>
                <input type="hidden" name="command" value="registration"/>
                <label for="login"><b>Login</b></label><br>
                <input type="text" required placeholder="login" class="fadeIn second" name="login" id="login"
                       pattern="[A-Za-z0-9]{4,20}"><br>
                <c:if test="${requestScope.userExistsError != null}">
                    <label>${requestScope.userExistsError}</label><br>
                </c:if>

                <label for="firstName"><b>First name: </b></label>
                <input type="text" required placeholder="first name" class="fadeIn second" name="firstName" id="firstName"><br>

                <label for="secondName"><b>Second name: </b></label>
                <input type="text" required placeholder="second name" class="fadeIn second" name="secondName" id="secondName"><br>

                <label for="email"><b>Email</b></label><br>
                <input type="email" required placeholder="Enter email" class="fadeIn second" name="email" id="email">
                <c:if test="${requestScope.emailExistsError != null}">
                    <label>${requestScope.emailExistsError}</label>
                </c:if>
                <br>

                <label for="password"><b>Password</b></label>
                <input type="password" required placeholder="password" class="fadeIn third" name="password"
                       id="password" pattern="[A-Za-z0-9]{4,32}"><br>
                <small id="passwordHelpBlock" class="form-text text-muted">
                    Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.
                </small>

                <label for="password-repeat"><b>Repeat Password</b></label>
                <input type="password" required placeholder="Repeat password" class="fadeIn third"
                       name="password-repeat" id="password-repeat" pattern="[A-Za-z0-9]{4,32}"><br>
                <hr>
                <input class="fadeIn fourth" type="submit" value="Register">

            </div>
        </form>

        <c:if test="${requestScope.errMessage != null}">
            <label for="password-repeat"><b>Passwords doesn`t match</b></label>
        </c:if>

        <!-- GO To registration page -->
        <div id="formFooter">
            <a class="underlineHover" href="login.jsp">Already have an account?</a>
        </div>
    </div>
</div>
</body>
</html>






