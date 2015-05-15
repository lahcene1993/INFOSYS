<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:index>
    <bs:modal title="" id="login" icon="user"> 
        <form method="POST" class="form" action="">
            <label><language:language  message="username"/></label>
            <input type="text" id="username" name="username" required="required" class="form-control">
            <label><language:language  message="password"/></label>
            <input type="password" id="password" name="password" required="required" class="form-control">
            <br>
            <input type="hidden" name="LOGIN" value="TRUE">
            <button type="submit" class="btn btn-success pull-right"><span class="fa fa-sign-in"> <language:language message="login"/></span></button>
        </form>
    </bs:modal>
    <script>
        $('#login').modal();
    </script>
</template:index>