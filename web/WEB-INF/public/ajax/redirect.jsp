<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    document.location =  '<c:url value="${page}"/>';
</script>
