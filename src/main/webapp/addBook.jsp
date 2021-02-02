<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Add book</title>
    <link href="static/custom.css" rel="stylesheet"/>
</head>
<body>
<form method="post" action="/library/first/menu">
    <div class="container">
        <h1>Add a book</h1>
        <p>Please fill in this form to add a book to catalog</p>
        <hr>

        <label for="title"><b>Title</b></label>
        <input type="text" placeholder="Enter title" name="title" id="title" required>

        <label for="author"><b>Author</b></label>
        <input type="text" placeholder="Enter author" name="author" id="author" required>

        <label for="ISBN"><b>ISBN</b></label>
        <input type="number" placeholder="Enter ISBN" name="ISBN" id="ISBN" required>
        <br>
        <label for="publisher"><b>Publisher</b></label>
        <input type="text" placeholder="Enter publisher" name="publisher" id="publisher" required>

        <label for="language"><b>Language</b></label>
        <input type="text" placeholder="Enter language" name="language" id="language" required>

        <label for="number"><b>Number</b></label>
        <input type="number" placeholder="Enter number of books" name="number" id="number" required>
        <hr>

        <button type="submit" class="registerbtn">Add to catalog</button>
    </div>
</form>
</body>
</html>
