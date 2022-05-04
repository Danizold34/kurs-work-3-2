<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Ticket</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>
<div class="col-9">

    <form class="was-validated m-2 text-style" method="post"
          action="controller">
        <input type="hidden" name="command" value="buy_ticket"/>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label "> <fmt:message key="trainNumber"/></label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       name="trainId"
                       value="${train_id}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label "> <fmt:message key="departureStation"/></label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       name="departureStation"
                       value="${departure_station}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label "> <fmt:message key="departureTime"/></label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       name="departureTime"
                       value="${departure_time}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label "> <fmt:message key="arrivalStation"/></label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       name="arrivalStation"
                       value="${arrival_station}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label "> <fmt:message key="arrivalTime"/></label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       name="arrivalTime"
                       value="${arrival_time}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label "> <fmt:message key="price"/></label>
            <div class="col-sm-10">
                <input type="text" readonly class="form-control-plaintext"
                       name="price"
                       value="${price}">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="name"/></label>
            <div class="col-sm-10">
                <input type="text"
                       pattern="\w{3,40}"
                       title="<fmt:message key="stringTitle"/>"
                       name="name"
                       required
                       placeholder="<fmt:message key="name"/>">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="lastName"/></label>
            <div class="col-sm-10">
                <input type="text"
                       name="lastName"
                       pattern="\w{3,40}"
                       required
                       title="<fmt:message key="stringTitle"/>"
                       placeholder="<fmt:message key="lastName"/>">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="passportNumber"/></label>
            <div class="col-sm-10">
                <input type="text"
                       name="passportNumber"
                       pattern="\w{3,15}"
                       required
                       title="<fmt:message key="numberTitle"/>"
                       placeholder="<fmt:message key="passportNumber"/>">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="phoneNumber"/></label>
            <div class="col-sm-10">
                <input type="text"
                       required
                       name="phoneNumber"
                       pattern="\w{3,15}"
                       title="<fmt:message key="numberTitle"/>"
                       placeholder="<fmt:message key="phoneNumber"/>">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="seat"/></label>
            <div class="col-sm-10">
                <input type="text"
                       required
                       name="seat"
                       min="1"
                       pattern="\d{1,5}"
                       title="<fmt:message key="seatTitle"/>"
                       placeholder="<fmt:message key="seat"/>">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="date"/></label>
            <div class="col-sm-10">
                <input type="date"
                       required
                       min="${current_date}"
                       value="${current_date}"
                       name="date"
                       placeholder="<fmt:message key="date"/>">
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> <fmt:message key="accountNumber"/></label>
            <div class="col-sm-10">
                <input type="text"
                       required
                       name="accountNumber"
                       min="1"
                       pattern="\d{1,20}"
                       title="<fmt:message key="accountTitle"/>"
                       placeholder="<fmt:message key="accountNumber"/>">
            </div>
        </div>
        <c:if test="${incorrect_data != null}">
            <p class="alert-warning"><fmt:message key="${incorrect_data}"/></p>
        </c:if>
        <c:if test="${message != null}">
            <p class="alert-success"><fmt:message key="${message}"/></p>
        </c:if>
        <button type="submit" class="btn btn-primary">
            <fmt:message key="buyTicket"/>
        </button>
    </form>
    <div class="row">
        <div class="col-md-12">
            <br/>
            <br/>
            <br/>
            <br/>
        </div>
    </div>
</div>
<customtag:copyright/>
</body>
</html>
