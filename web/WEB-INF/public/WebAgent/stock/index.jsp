<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:agent>
        <div class="container-fluid alert alert-info">
        <form class="form-search col-md-12 form-inline" onsubmit="return false;" method="POST" >
            <label></label>
            <select class="form-control" name="by">
                <option value="1">Id</option>
                <option value="2">Nom</option>
            </select>
            <label></label>
            <input type="text" name="value" class="form-control">
            <button name="SEARCH" type="submit" class="btn  btn-primary"><span  class="fa fa-search"></span></button>
            <button type="button" id="create" class="btn btn-success"><span class="fa fa-plus"><language:language message="new"/></span></button>
        </form>              
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <jsp:include page="search.jsp"/>
    </div>
</template:agent>