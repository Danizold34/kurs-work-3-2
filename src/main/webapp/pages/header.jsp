<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Header</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/plugins/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- Owl Carousel -->
    <link href="${pageContext.request.contextPath}/plugins/slick-carousel/slick/slick.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/plugins/slick-carousel/slick/slick-theme.css" rel="stylesheet">
    <!-- Fancy Box -->
    <link href="${pageContext.request.contextPath}/plugins/fancybox/jquery.fancybox.pack.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/plugins/jquery-nice-select/css/nice-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/plugins/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css"
          rel="stylesheet">
    <!-- CUSTOM CSS -->
    <link href="${pageContext.request.contextPath}/css/mystyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
</head>
<body class="body-wrapper">


<section>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <nav class="navbar navbar-expand-lg  navigation">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                        <img src="${pageContext.request.contextPath}/image/logo.jpg" width="193" height="38" alt="">
                    </a>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp"><fmt:message
                                        key="home"/><span class="sr-only">(current)</span></a>
                            </li>
                            <c:if test="${sessionScope.user != null}">
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/controller?command=to_profile_page"><fmt:message
                                            key="profile"/></a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.user.role.ordinal() == 0}">
                                <li class="nav-item">
                                    <a class="nav-link"
                                       href="${pageContext.request.contextPath}/controller?command=to_admin_page"><fmt:message
                                            key="adminPanel"/></a>
                                </li>
                            </c:if>
                        </ul>
                        <c:if test="${sessionScope.user == null}">
                            <form class="form-inline my-2 my-lg-0"
                                  action="${pageContext.request.contextPath}/controller"
                                  method="get">
                                <input type="hidden" name="command" value="to_login_page"/>
                                <button class="nav-link btn-dark active" type="submit"><fmt:message
                                        key="login"/></button>
                            </form>
                        </c:if>
                        <c:if test="${sessionScope.user != null}">
                            <div class="dropdown">
                                <a class="nav-link login-button active" href="#" role="button"
                                   data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                        ${sessionScope.user.username}
                                </a>
                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/controller?command=to_profile_page">
                                        <fmt:message key="profile"/>
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/controller?command=logout">
                                        <fmt:message key="logout"/>
                                    </a>
                                </div>
                            </div>
                        </c:if>
                        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/controller"
                              method="get">
                            <input type="hidden" name="command" value="change_locale"/>
                            <div class="dropdown">
                                <a class="nav-link" href="#" role="button"
                                   data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-globe fa-2x"></i>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="change_language">
                                    <button class="dropdown-item" type="submit" name="language" value="en">English
                                    </button>
                                    <button class="dropdown-item" type="submit" name="language" value="ru">Русский
                                    </button>
                                </div>
                            </div>
                        </form>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</section>

<!-- JAVASCRIPTS -->
<script src="${pageContext.request.contextPath}/plugins/tether/js/tether.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/raty/jquery.raty-fa.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/dist/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/seiyria-bootstrap-slider/dist/bootstrap-slider.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/slick-carousel/slick/slick.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-nice-select/js/jquery.nice-select.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/fancybox/jquery.fancybox.pack.js"></script>
<script src="${pageContext.request.contextPath}/plugins/smoothscroll/SmoothScroll.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCC72vZw-6tGqFyRhhg5CkF2fqfILn2Tsw"></script>
</body>
</html>