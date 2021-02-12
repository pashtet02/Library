<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<head>
    <c:set var="title" value="Settings" scope="page" />
    <%@ include file="../../../header.jsp" %>
</head>


<body>
<table id="main-container">

    <%@ include file="../../../navbar.jsp" %>
    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="settings_form" action="controller" method="post">
                <input type="hidden" name="command" value="updateSettings" />

                <div>
                    <p>
                        <fmt:message key="settings_jsp.label.localization"/>
                    </p>
                    <select name="localeToSet">
                        <c:choose>
                            <c:when test="${not empty defaultLocale}">
                                <option value="">${defaultLocale}[Default]</option>
                            </c:when>
                            <c:otherwise>
                                <option value=""/>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="localeName" items="${locales}">
                            <option value="${localeName}">${localeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div>
                    <p>
                        <fmt:message key="settings_jsp.label.first_name"/>
                    </p>
                    <input name="firstName" placeholder="${sessionScope.user.firstName}">
                </div>

                <div>
                    <p>
                        <fmt:message key="settings_jsp.label.last_name"/>
                    </p>
                    <input name="lastName" placeholder="${sessionScope.user.secondName}">
                </div>

                <input type="submit" value='<fmt:message key="settings_jsp.button.update"/>'><br/>
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>
</table>
</body>
</html>