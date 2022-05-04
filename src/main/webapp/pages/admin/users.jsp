<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Trains</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="username"/></th>
        <th scope="col"><fmt:message key="email"/></th>
        <th scope="col"><fmt:message key="role"/></th>
        <th scope="col"><fmt:message key="edit"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <form method="post" action="controller">
            <input type="hidden" name="command" value="change_role"/>
            <input type="hidden" name="username" value="${user.username}"/>
            <input type="hidden" name="email" value="${user.email}"/>
            <input type="hidden" name="userId" value="${user.id}"/>
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td><select class="custom-select" id="roleValue" name="role">
                    <c:if test="${user.role.ordinal() == 0}">
                    <option value="0"><fmt:message key="admin"/></option>
                    <option value="1"><fmt:message key="user"/></option>
                    </c:if>
                    <c:if test="${user.role.ordinal() == 1}">
                        <option value="1"><fmt:message key="user"/></option>
                        <option value="0"><fmt:message key="admin"/></option>
                    </c:if>
                </select>
                </td>
                <td>
                    <button type="submit"><fmt:message key="edit"/></button>
                </td>
            </tr>
        </form>
    </c:forEach>
    </tbody>
</table>
<customtag:copyright/>
</body>
</html>
