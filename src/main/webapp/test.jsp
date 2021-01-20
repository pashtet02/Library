<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.jt.name.entity.Book" %>
<%@ page import="com.epam.jt.name.entity.User" %>
<html>
<head>
    <title>All books</title>
</head>
<body>
<h2>My library users</h2>
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>username</th>
        <th>Email</th>
        <th>ROLE</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>

    <%

        List<User> list = (List<User>) session.getAttribute("usrLst");
        for (User s : list) {
    %>
    <tr>
        <td><%=s.getId()%>
        </td>
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