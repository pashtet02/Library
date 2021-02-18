<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <h3><fmt:message key="addImage.mainLabel"/></h3>
    <fmt:message key="addImage.selectFile"/> <br />
    <form action = "<c:url value="/controller?command=addImage"/>" method="post"
          enctype = "multipart/form-data">
        <input type = "file" name = "file" size = "50" pattern="[A-Za-z0-9]"/>
        <br />
        <input class="btn btn-primary" type = "submit" value = "<fmt:message key="editBook.nextBook"/>" />
    </form>
</div>
</body>
</html>
