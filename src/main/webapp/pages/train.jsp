<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Train</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>
<c:if test="${not empty incorrect_data}">
    <p class="alert-warning"><fmt:message key="${incorrect_data}"/></p>
</c:if>
<c:if test="${empty incorrect_data}">
    <div class="content-block">
        <h1><fmt:message key="numberTrain"/> ${train_number}</h1>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="station"/></th>
            <th scope="col"><fmt:message key="time"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${train.routes}" var="route">
            <tr>
                <td>${route.station}</td>
                <td>${route.time}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<customtag:copyright/>
</body>
</html>
