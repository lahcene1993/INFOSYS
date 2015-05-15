<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:admin>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <form method="POST" class="well well-lg">
            <div class="">
                <label><language:language message="server"/></label>
                <input name="server" value="${entity.server}" class="form-control" type="text" />
                <label><language:language message="port"/></label>
                <input name="port" value="${entity.port}" class="form-control" type="text" />
                <label><language:language message="database"/></label>
                <input name="database" value="${entity.database}" class="form-control" type="text" />
                <label><language:language message="username"/></label>
                <input name="username" value="${entity.username}" class="form-control" type="text" />
                <label><language:language message="password"/></label>
                <input name="password" value="${entity.password}" class="form-control" type="text" />
                <label><language:language message="offline"/></label>
                <input name="offline" value="${entity.offline}" class="form-control" type="text" />
                <br>
                <input type="submit" class="btn btn-success btn-block">
            </div>
            <input type="hidden" name="version" value="${entity.version}">
            <input type="hidden" name="id" value="${entity.id}">
            <input type="hidden" name="SAVE" value="TRUE">
        </form>
    </div>
</template:admin>