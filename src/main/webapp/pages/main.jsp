<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="customtag" prefix="customtag"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>MainNuha</title>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<div class="content">
    <section class="hero-area bg-1 text-center overly">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="content-block">
                        <h1><fmt:message key="railway"/></h1>
                    </div>
                    <div class="advance-search">
                        <form method="GET" action="controller">
                            <input type="hidden" name="command" value="find_trains_by_stations"/>
                            <div class="row">
                                <div class="col-lg-6 col-md-12">
                                    <div class="block d-flex">
                                        <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" type="search"
                                               name="departureStation"
                                               pattern="\w{3,40}"
                                               required
                                               title="<fmt:message key="stringTitle"/>"
                                               placeholder="
                                        <fmt:message key="departureStation"/>">
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-12">
                                    <div class="block d-flex">
                                        <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0" type="search"
                                               pattern="\w{3,40}"
                                               required
                                               title="<fmt:message key="stringTitle"/>"
                                               name="arrivalStation" placeholder="
                                        <fmt:message key="arrivalStation"/>">
                                        <button class="btn btn-main"><fmt:message key="search"/></button>
                                    </div>
                                </div>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </div>
    </section>

    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <br/>
                <br/>
                <br/>
                <br/>
                <div class="content-block">
                    <h2><fmt:message key="popularRoutes"/></h2>
                </div>
            </div>
        </div>

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
                        <c:if test="${sessionScope.user.role.ordinal() == 1}">
                            <br/>
                            <form method="POST" action="controller">
                                <input type="hidden" name="command" value="to_buy_ticket_page"/>
                                <input type="hidden" name="trainId" value="${short_train_data.trainId}"/>
                                <input type="hidden" name="departureTime" value="${short_train_data.departureTime}"/>
                                <input type="hidden" name="departureStation"
                                       value="${short_train_data.departureStation}"/>
                                <input type="hidden" name="arrivalTime" value="${short_train_data.arrivalTime}"/>
                                <input type="hidden" name="arrivalStation" value="${short_train_data.arrivalStation}"/>
                                <input type="hidden" name="price" value="${short_train_data.price}"/>
                                <button type="submit"><fmt:message key="buyTicket"/></button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="row">
            <div class="col-md-12">
                <br/>
                <br/>
                <br/>
                <br/>
            </div>
        </div>
    </div>
</div>
<customtag:copyright/>
</body>

</html>
