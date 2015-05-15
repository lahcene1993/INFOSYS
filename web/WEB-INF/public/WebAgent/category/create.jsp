<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  tagdir="/WEB-INF/tags/language/" prefix="language" %>
<bs:modal id="client">
    <form method="POST" action="" class="row">
        <label><language:language  message="name"/></label>
        <input type="text" name="nom" required value="${entity.nom}" class="form-control">
        <br>
        <input type="hidden" name="SAVE" value="TRUE">
        <input type="hidden" name="version" value="${entity.version}">
        <input type="hidden" name="id" value="${entity.id}">
        <input type="submit" class="btn btn-primary">
    </form>
</bs:modal>