<%--
  Created by IntelliJ IDEA.
  User: Pashtet
  Date: 21.01.2021
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration page</title>
</head>
<body>
<div class="form">

    <h1>Реєстраційна форма</h1><br>
    <form method="post" action="/library/usermenu">
        <input type="text" required placeholder="login" name="login"><br>
        <input type="text" required placeholder="mail" name="mail"><br>
        <input type="password" required placeholder="password" name="password"><br><br>

        <input class="button" type="submit" value="Register">
    </form>
</div>
</body>
</html>
