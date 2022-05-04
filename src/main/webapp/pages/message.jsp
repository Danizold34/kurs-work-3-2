<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<head>
    <title>Profile</title>
</head>

<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>
<c:if test="${incorrect_data != null}">
    <p class="alert-warning"><fmt:message key="${incorrect_data}"/></p>
</c:if>
<c:if test="${ticket_bought != null}">
    <div class="content-block">
    <p class="alert-success"><fmt:message key="${ticket_bought}"/></p>
    <div>
</c:if>
<customtag:copyright/>
