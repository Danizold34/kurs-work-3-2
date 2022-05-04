<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>

    <title>Error page</title>
</head>
<body>

<div>
    <h1>Oops!</h1>
    <h2>404 Not Found</h2>
    <div >
        Sorry, an error has occured, Requested page not found!
    </div>
    <div >
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary btn-lg">
            <span><i class="fas fa-home"></i></span> Take Me Home
        </a>
    </div>
</div>

</body>
</html>