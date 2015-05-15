<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@tag description="Template" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>WebApp</title>
        <meta charset="UTF-8">
        <link href="<c:url value="/assets/css/bootstrap.css"/>" rel="stylesheet"/>
        <link href="<c:url value="/assets/css/font.css"/>" rel="stylesheet"/>
        <script src="<c:url value="/assets/js/jquery.js"/>" ></script>
        
        <link href="<c:url value="/assets/css/toastr.css"/>" rel="stylesheet"/>
        <script src="<c:url value="/assets/js/bootstrap.js"/>" ></script>
    </head>
    <body>
        <jsp:doBody/>
        <script src="<c:url value="/assets/js/bean.js"/>" ></script>
        <script src="<c:url value="/assets/js/toastr.js"/>" ></script>
    </body>
</html>