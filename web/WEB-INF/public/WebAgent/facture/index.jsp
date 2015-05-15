<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:agent>
    <datalist id="clients">
        <c:forEach items="${clients}" var="client">
            <option value="${client.nom}">${client.nom}</option>
        </c:forEach>
    </datalist>
    <div class="container-fluid alert alert-info no-print">
        <form class="form-search col-md-12 form-inline" method="POST" >
            <label class="btn btn-default"><language:language message="date"/></label>
            <input type="date" name="date" class="form-control">
            <label class="btn btn-default"><language:language message="costumer"/></label>
            <input list="clients" type="text" name="client" class="form-control">
            <input type="hidden" name="SEARCH" value="TRUE">
            <button type="submit" class="btn  btn-primary"><span  class="fa fa-search"></span></button>
            <button type="button" id="create" class="btn btn-success"><span class="fa fa-plus"><language:language message="new"/></span></button>
        </form>              
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <jsp:include page="search.jsp"/>
    </div>
</template:agent>