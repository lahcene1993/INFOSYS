<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:agent>
    <div class="container-fluid alert alert-info">
        <form class="form-search col-md-12 form-inline" method="POST" >
            <label class="btn btn-default"><language:language message="product"/></label>
            <input type="text" name="libele" class="form-control">
            <label class="btn btn-default"><language:language message="category"/></label>
            <select name="category" class="form-control">
                <option></option>
                <c:forEach items="${categories}" var="item">
                    <option value="${item.id}">${item.nom}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="SEARCH" value="TRUE">
            <button type="submit" class="btn  btn-primary"><span  class="fa fa-search"></span></button>
            <button type="button" id="create" class="btn btn-success"><span class="fa fa-plus"><language:language message="new"/></span></button>
        </form>              
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <jsp:include page="search.jsp"/>
    </div>
</template:agent>