<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  tagdir="/WEB-INF/tags/language/" prefix="language" %>
<bs:modal id="client">
    <form method="POST" action="" class="row">
        <label><language:language  message="name"/></label>
        <input type="text" required name="name" value="" class="form-control">
        <label><language:language  message="message"/></label>
        <textarea name="message" required class="form-control"></textarea>
        <br>
        <input type="hidden" name="sent" value="TRUE">
        <input type="hidden" name="client" value="${client.id}">
        <input type="hidden" name="SAVE" value="TRUE">
        <input type="submit" class="btn btn-primary">
    </form>
</bs:modal>