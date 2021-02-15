<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pashtet
  Date: 11.02.2021
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add image</title>
    <link href="../../../static/custom.css" rel="stylesheet"/>
    <jsp:include page="../../jspf/directive/header.jsp"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>
<div class="container">
    <h3>File Upload:</h3>
    Select a file to upload: <br />
    <form action = "<c:url value="/controller?command=addImage"/>" method="post"
          enctype = "multipart/form-data">
        <input type = "file" name = "file" size = "50" pattern="[A-Za-z0-9]"/>
        <br />
        <input class="btn btn-primary" type = "submit" value = "Upload File" />
    </form>
</div>
</body>
</html>
