<%@tag import="java.util.Properties"%>
<%@tag description="Language" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="message"%>
<%@attribute name="addon"%>
<c:if test="${param.language ne null }">
    <c:set var="language" scope="session" value="${param.language}"></c:set>
</c:if>
<%
    Properties properties=new Properties();
    properties.load(getClass().getResourceAsStream("/com/web/language/fr.properties"));
    
%>
<%= properties.getProperty(message) %>