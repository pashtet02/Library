<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../../jspf/directive/header.jsp"/>
    <title>Library</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="../../../static/custom.css" rel="stylesheet"/>
</head>
<body>
<jsp:include page="../../jspf/directive/navbar.jsp"/>

<form method="post" action="<c:url value="/controller?command=editUser&id=${param.userId}"/>">
    <p><c:out value="${requestScope.id}"/></p>
    <div class="container">
        <h2>Edit user:</h2>
        <p>Please fill in this form to edit a user account</p>
        <hr>

        <label for="role"><strong>Role</strong></label>
        <input type="text" placeholder="Enter role" name="role" id="role" pattern="^(USER)$|^(ADMIN)$|^(LIBRARIAN)$">
        <br>

        <label for="exampleFormControlSelect1">Ban a user?</label>
        <select class="form-control" id="exampleFormControlSelect1" name="isBanned">
            <option value="false">no</option>
            <option value="true">yes</option>
        </select>

        <hr>
        <!-- Button trigger modal -->
        <button class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" type="button">
            Done
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">A
                        Are you sure?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container signin">
        <p>Back to users list <a href="<c:url value="/controller?command=usersList"/>">go!</a>.</p>
    </div>
</form>
</body>
</html>

