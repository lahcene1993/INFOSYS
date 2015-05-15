<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="b" %>
<% request.setAttribute("exception", exception);     %>
<template:index>
    <b:modal id="exception">
    <b:alert type="danger">
        <span class="fa fa-warning"></span>
        <p>${exception.getMessage()}</p>
    </b:alert>
</b:modal>
        <script>
            $("#exception").modal();
        </script>
</template:index>