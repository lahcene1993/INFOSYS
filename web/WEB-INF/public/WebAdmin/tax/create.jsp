<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<bs:modal id="client">
    <form method="POST" action="" class="row">
        <div class="col-xs-12">
            <label><language:language message="value"/></label>
            <input type="text" required pattern="[-+]?[0-9]*[.]?[0-9]+" name="value" value="${entity.value}" required class="form-control">
        </div>
        <input type="hidden" name="SAVE" value="TRUE">
        <input type="hidden" name="version" value="${entity.version}">
        <input type="hidden" name="id" value="${entity.id}">
        <div class="col-xs-6">
            <br>
            <button type="submit" class="btn btn-success btn-block btn-xs"><span class="fa fa-plus-square-o"></span> <language:language message="save"/> </button>
        </div>
    </form>
</bs:modal>
