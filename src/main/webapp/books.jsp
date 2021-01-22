<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.jt.name.entity.Book" %>
<%@ page import="com.epam.jt.name.entity.User" %>
<html>
<body>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<h2>My library books list</h2>
<table border="1">
    <thead>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>ISBN</th>
        <th>Publisher</th>
        <th>Publishing Date</th>
        <th>Number</th>
    </tr>
    </thead>
    <tbody>

    <%

        List<Book> lst = (List<Book>) session.getAttribute("bookList");
        for (int i = 0; i < lst.size(); i++) {

    %>
    <tr>
        <td><%=lst.get(i).getId()%>
        </td>
        <td><%=lst.get(i).getTitle()%>
        </td>
        <td><%=lst.get(i).getAuthor()%>
        </td>
        <td><%=lst.get(i).getISBN()%>
        </td>
        <td><%=lst.get(i).getPublisher()%>
        </td>
        <td><%=lst.get(i).getPublishingDate()%>
        </td>
        <td><%=lst.get(i).getNumber()%>
        </td>
        <td><a href="update.jsp?id=<%=lst.get(i).getId()%>">update</a></td>
        <td><a href="deleteStudent.jlst.get(s)p?id=<%=lst.get(i).getId()%>">delete</a></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<a href="users.jsp">to users list</a>
<br>
<a href="test.html">to main page</a>
<br>

<a href="addBook.jsp">to main page</a>
</body>
</html>
