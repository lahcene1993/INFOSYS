<%@tag description="Bootstrap Dashboard" pageEncoding="UTF-8"%>
<%@taglib  tagdir="/WEB-INF/tags/bootstrap/" prefix="b" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/ui/" prefix="ui" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<c:if test="${sessionScope.user eq null}">
    <c:redirect url="logout.html"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <title>${user}</title>
        <meta charset="UTF-8">
        <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/assets/css/font.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/assets/css/toastr.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/assets/css/main.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/assets/css/AdminLTE.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/assets/css/skins/all-skins.css"/>" rel="stylesheet"/>
        <script src="<c:url value="/assets/js/jquery.js"/>" ></script>
        <script src="<c:url value="/assets/js/app.js"/>" ></script>
        <script src="<c:url value="/assets/js/bootstrap.js"/>" ></script>
        <script src="<c:url value="/assets/js/ckeditor/ckeditor.js"/>" ></script>
        <script src="<c:url value="/assets/js/bootbox.min.js"/>" ></script>
        <script src="<c:url value="/assets/js/toastr.js"/>" ></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body class="${sessionScope.user.role.name() eq 'CLIENT'?'skin-yellow':( sessionScope.user.role.name() eq 'ADMIN')?'skin-blue':'skin-green'}">
        <!-- header logo: style can be found in header.less -->
        <header class="main-header">
            <a href="" class="logo">
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <nav class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <!-- User Account: style can be found in dropdown.less -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="fa fa-user"></i>
                                <span>${sessionScope.user.username} <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header ${sessionScope.user.role.name() eq 'CLIENT'?'bg-yellow-active':( sessionScope.user.role.name() eq 'ADMIN')?'bg-blue-active':'bg-green-active'}">
                                    <p>
                                        ${sessionScope.user.username} 
                                    </p>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <form class="ajax-form" method="POST">
                                        <input type="hidden" name="CHANGEPASSWORD" value="TRUE">
                                        <input type="hidden" name="AJAX" value="TRUE">
                                        <button class="btn btn-default pull-left btn-flat"> <span class="fa fa-key"></span></button>
                                        <a href="logout.html" class="btn btn-default btn-flat pull-right"><language:language message="logout"/></a>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="wrapper row-offcanvas row-offcanvas-left">
            <jsp:doBody/>
        </div>
        <script src="<c:url value="/assets/js/bean.js"/>" ></script>
        <script src="<c:url value="/assets/js/ckeditor/ckeditor.js"/>" ></script>
        <script src="<c:url value="/assets/js/ckeditor/adapters/jquery.js"/>" ></script>
        
        
        <script>
        </script>
    </body>
</html>