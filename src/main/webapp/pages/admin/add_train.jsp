<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="customtag" prefix="customtag" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Add train</title>
    <script src="${pageContext.request.contextPath}/js/my_script.js"></script>
</head>
<body>
<header>
    <jsp:include page="${pageContext.request.contextPath}/pages/header.jsp"/>
</header>
<hr/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="content-block">
                <h2><fmt:message key="trainAddition"/></h2>
            </div>
        </div>
    </div>
    <div class="col-9">
        <form class="was-validated m-2 text-style" method="post"
              action="controller">
            <input type="hidden" name="command" value="add_train"/>

            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> <fmt:message key="trainNumber"/></label>
                <div class="col-sm-10">
                    <input type="text"
                           required
                           name="trainId"
                           min="1"
                           pattern="\d{1,19}"
                           title="<fmt:message key="trainNumberTitle"/>"
                           placeholder="<fmt:message key="trainNumber"/>">
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label"> <fmt:message key="seatNumber"/></label>
                <div class="col-sm-10">
                    <input type="text"
                           required
                           name="seatNumber"
                           min="1"
                           pattern="\d{1,5}"
                           title="<fmt:message key="seatTitle"/>"
                           placeholder="<fmt:message key="seatNumber"/>">
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="content-block">
                        <h2><fmt:message key="route"/></h2>
                    </div>
                </div>
            </div>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="station"/></th>
                    <th scope="col"><fmt:message key="time"/></th>
                    <th scope="col"><fmt:message key="price"/></th>
                </tr>
                </thead>
                <tbody id="route">
                <tr>
                    <td>
                        <input name="station" required type="text" pattern="\w{3,40}"
                               title="<fmt:message key="stringTitle"/>">
                    </td>
                    <td>
                        <input name="time" required type="time" placeholder="<fmt:message key="time"/>">
                    </td>
                    <td>
                        <input type="text"
                               required
                               name="price"
                               min="1"
                               pattern="\d{1,5}"
                               title="<fmt:message key="seatTitle"/>"
                               placeholder="<fmt:message key="price"/>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input name="station" required type="text" pattern="\w{3,40}"
                               title="<fmt:message key="stringTitle"/>">
                    </td>
                    <td>
                        <input name="time" required type="time" placeholder="<fmt:message key="time"/>">
                    </td>
                    <td>
                        <input type="text"
                               required
                               name="price"
                               min="1"
                               pattern="\d{1,5}"
                               title="<fmt:message key="seatTitle"/>"
                               placeholder="<fmt:message key="price"/>">
                    </td>
                </tr>
                </tbody>
            </table>
            <button  class="btn btn-secondary" onclick="return addField()">
                <fmt:message key="addStation"/>
            </button>
            <br/>
            <button type="submit" class="btn btn-primary">
                <fmt:message key="add"/>
            </button>
        </form>
        <c:if test="${incorrect_data != null}">
            <p class="alert-warning"><fmt:message key="${incorrect_data}"/></p>
        </c:if>
        <c:if test="${message != null}">
            <p class="alert-success"><fmt:message key="${message}"/></p>
        </c:if>
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