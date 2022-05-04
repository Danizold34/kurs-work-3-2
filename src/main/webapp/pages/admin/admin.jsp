<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<head>
    <title>AdminPanel</title>
</head>

<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>
<div class="container-fluid">
    <div class="row">
        <div class="col-2 m-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/controller?command=to_users_page"
                   class="list-group-item list-group-item-action text-style active">
                     <fmt:message key="users"/></a>
                <a href="${pageContext.request.contextPath}/controller?command=to_my_tickets_page"
                   class="list-group-item list-group-item-action text-style">
                    <fmt:message key="tickets"/></a>
                <a href="${pageContext.request.contextPath}/controller?command=show_all_trains"
                   class="list-group-item list-group-item-action text-style active">
                    <fmt:message key="trains"/></a>
                <a href="${pageContext.request.contextPath}/controller?command=to_add_train_page"
                   class="list-group-item list-group-item-action text-style">
                    <fmt:message key="trainAddition"/></a>
            </div>
        </div>

    </div>
</div>
<customtag:copyright/>
