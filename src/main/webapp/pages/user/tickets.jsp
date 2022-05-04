<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>

<head>
    <title>Tickets</title>
</head>
<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>

<body>
<c:if test="${not empty message}">
    <p class="alert-warning"><fmt:message key="${message}"/></p>
</c:if>
<c:if test="${empty message}">

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="name"/></th>
            <th scope="col"><fmt:message key="lastName"/></th>
            <th scope="col"><fmt:message key="passportNumber"/></th>
            <th scope="col"><fmt:message key="numberTrain"/></th>
            <th scope="col"><fmt:message key="departure"/></th>
            <th scope="col"><fmt:message key="arrival"/></th>
            <th scope="col"><fmt:message key="seat"/></th>
            <th scope="col"><fmt:message key="price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${tickets}" var="ticket">
            <tr>
                <td>${ticket.passenger.name}</td>
                <td>${ticket.passenger.lastName}</td>
                <td>${ticket.passenger.passportNumber}</td>
                <td>${ticket.trainId}</td>
                <td>${ticket.departureDate} <br/>
                        ${ticket.departureStation}</td>
                <td>${ticket.arrivalDate} <br/>
                        ${ticket.arrivalStation}</td>
                <td>${ticket.seat}</td>
                <td>${ticket.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<customtag:copyright/>
</body>