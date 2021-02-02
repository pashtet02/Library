<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Registration page</title>
    <link href="static/custom.css" rel="stylesheet"></link>
</head>
<body>

<form method="post" action="/library/first/usermenu">
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter login" name="login" id="login" required>

        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Enter email" name="email" id="email" required>
        <br>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" id="password" required>

        <label for="password-repeat"><b>Repeat Password</b></label>
        <input type="password" placeholder="Repeat Password" name="password-repeat" id="password-repeat" required>
        <hr>

        <button type="submit" class="registerbtn">Register</button>
    </div>

    <div class="container signin">
        <p>Already have an account? <a href="/library/first">Sign in</a>.</p>
    </div>
</form>
</body>
</html>
