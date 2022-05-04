<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginstyle.css">

</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>


<div class="container" >
    <div class="row">
        <div class="col-4 mx-auto my-lg-4 p-3 bg-light">
            <form class="form-horizontal" name="loginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="register"/>
                <input type="hidden" name="role" value="user"/>
                <span class="heading"><fmt:message key="registration"/></span>
                <div class="form-group">
                    <input type="email" class="form-control" required name="email" placeholder="<fmt:message key="email"/>">
                    <i class="fa fa-envelope"></i>
                </div>
                <div class="form-group">
                    <input class="form-control" name="username"
                           required
                           pattern="\w{3,40}"
                           title="<fmt:message key="stringTitle"/>"
                           placeholder="<fmt:message key="username"/>">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" name="password"
                           required
                           pattern="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$"
                           title="<fmt:message key="passwordTitle"/>"
                           placeholder=<fmt:message
                            key="password"/>>
                    <i class="fa fa-lock"></i>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-default"><fmt:message key="registration"/></button>
                </div>
                <c:if test="${not empty incorrect_data}">
                    <p class="alert-warning"><fmt:message key="${incorrect_data}"/></p>
                </c:if>
                <p class="message text-center"><fmt:message key="registered"/>
                    <a href="${pageContext.request.contextPath}/controller?command=to_login_page"
                       class="text-style"><fmt:message key="login"/></a></p>
            </form>
        </div>
    </div>
</div>
<customtag:copyright/>
</body>
</html>
