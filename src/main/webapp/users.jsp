<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.jt.name.domain.Book" %>
<%@ page import="com.epam.jt.name.domain.User" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>My library users</h2>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">username</th>
        <th scope="col">Email</th>
        <th scope="col">ROLE</th>
        <th scope="col">Fine</th>
        <th scope="col">Hello</th>
    </tr>
    </thead>
    <tbody>

    <%

        List<User> list = (List<User>) session.getAttribute("usrLst");
        for (User s : list) {
    %>
    <tr>
        <th scope="row"><%=s.getId()%></th>
        <td><%=s.getUsername()%>
        </td>
        <td><%=s.getMail()%>
        </td>
        <td><%=s.getRole()%>
        </td>
        <td><%=s.getFine()%>
        </td>
        <td><a href="update.jsp?id=<%=s.getId()%>">update</a></td>
        <td><a href="deleteStudent.jsp?id=<%=s.getId()%>">delete</a></td>
    </tr>
    <%
        }
    %>
    </tbody>

</table>

<a href="books.jsp">show all books</a>
</body>
</html>