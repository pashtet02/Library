<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="static/login.css" rel="stylesheet">

<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->

        <!-- Icon -->
        <div class="fadeIn first">
            <img src="https://cdn0.iconfinder.com/data/icons/cosmo-culture/40/books_1-512.png" id="icon" alt="User Icon" />
        </div>
        <form method="post" action="<c:url value="/controller"/>">
            <input type="hidden" name="command" value="login" />
            <input type="text" required placeholder="login" class="fadeIn second" name="login" id="login" pattern="[a-zA-Z0-9]{4,20}"><br>
            <input type="password" required placeholder="password" class="fadeIn third" name="password" id="password" pattern="[a-zA-Z0-9]{4,32}"><br><br>
            <input class="fadeIn fourth" type="submit" value="Enter">
        </form>
        <!-- GO To registration page -->
        <div id="formFooter">
            <a class="underlineHover" href="registration.jsp">I don`t have an account :(</a>
        </div>
    </div>
</div>