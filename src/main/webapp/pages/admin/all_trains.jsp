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

<c:if test="${not empty incorrect_data}">
    <p class="alert-warning"><fmt:message key="${incorrect_data}"/></p>
</c:if>
<c:if test="${empty incorrect_data}">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="trainNumber"/></th>
            <th scope="col"><fmt:message key="departure"/></th>
            <th scope="col"><fmt:message key="arrival"/></th>
            <th scope="col"><fmt:message key="price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${short_trains_data}" var="short_train_data">
            <tr>
                <td>
                    <a href="${pageContext.request.contextPath}/controller?command=find_train_by_id&trainId=${short_train_data.trainId}">
                            ${short_train_data.trainId}
                    </a>
                </td>
                <td>${short_train_data.departureTime} <br/>
                        ${short_train_data.departureStation} </td>
                <td>${short_train_data.arrivalTime} <br/>
                        ${short_train_data.arrivalStation} </td>
                <td>${short_train_data.price}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<customtag:copyright/>
</body>
</html>
