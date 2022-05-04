<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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


<div class="container-fluid">
    <div class="row">
        <div class="col-2 m-2">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/controller?command=to_profile_page"
                   class="list-group-item list-group-item-action text-style active">
                    <i class="fas fa-user-edit"></i> <fmt:message key="profile"/></a>
                <a href="${pageContext.request.contextPath}/controller?command=to_my_tickets_page"
                   class="list-group-item list-group-item-action text-style">
                    <i class="fas fa-eye"></i> <fmt:message key="myTickets"/></a>
            </div>
        </div>
        <div class="col-9">

            <form class="was-validated m-2 text-style" method="post"
                  action="controller">
                <input type="hidden" name="command" value="change_password"/>
                <div class="form-group row">
                    <label  class="col-sm-2 col-form-label "> <fmt:message key="username"/></label>
                    <div class="col-sm-10">
                        <input type="text" readonly class="form-control-plaintext"
                               value="${sessionScope.user.username}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"> <fmt:message key="password"/></label>
                    <div class="col-sm-10">
                        <input type="password"
                               name="password"
                               placeholder="<fmt:message key="password"/>">

                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label"> <fmt:message key="newPassword"/></label>
                    <div class="col-sm-10">
                        <input type="password" class="form" name="newPassword"
                               placeholder="<fmt:message key="newPassword"/>">
                    </div>
                </div>
                <c:if test="${incorrect_data != null}">
                    <p class="alert-warning"><fmt:message key="${incorrect_data}"/></p>
                </c:if>
                <c:if test="${message != null}">
                    <p class="alert-success"><fmt:message key="${message}"/></p>
                </c:if>
                <button type="submit" class="btn btn-primary">
                    <fmt:message key="edit"/>
                </button>
            </form>
        </div>
    </div>
</div>
<customtag:copyright/>
