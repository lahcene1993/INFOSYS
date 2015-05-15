<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:admin>
    <div class="container-fluid alert alert-info">
        <form class="form-search col-md-12 form-inline" method="POST" >
            <label class="btn btn-default"><language:language message="name"/></label>
            <input type="text" name="nom" class="form-control">
            <input type="hidden" name="SEARCH" value="TRUE">
            <button type="submit" class="btn  btn-primary"><span  class="fa fa-search"></span></button>
            <button type="button" id="create" class="btn btn-success"><span class="fa fa-plus"><language:language message="new"/></span></button>
        </form>             
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <jsp:include page="search.jsp"/>
    </div>
</template:admin>