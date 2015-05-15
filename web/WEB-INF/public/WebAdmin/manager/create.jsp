<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<bs:modal id="client">
    <form method="POST" action="" class="row">
        <div class="col-xs-12">
            <label><language:language message="name"/></label>
            <input type="text" name="nom" value="${entity.nom}" required class="form-control">
        </div>
        <div class="col-xs-6">
            <label><language:language message="city"/></label>
            <input type="text" name="ville" value="${entity.ville}" required class="form-control">
            <label><language:language message="country"/></label>
            <input type="text" name="pays" value="${entity.pays}" required class="form-control">
            <label><language:language message="zip"/></label>
            <input type="text" name="codePostal" value="${entity.codePostal}" required class="form-control">
            <label><language:language message="adress"/></label>
            <input type="text" name="adress" value="${entity.adress}" required class="form-control">
        </div>
        <div class="col-xs-6">
            <label><language:language message="phone"/></label>
            <input type="text" pattern="^(?:0|\(?\+[1-9]*\)?\s?|00[1-9]*\s?)[1-99](?:[\.\-\s]?\d\d){4}$" name="telephoneFixe" value="${entity.telephoneFixe}" required class="form-control">
            <label><language:language message="mobile_phone"/></label>
            <input type="text" pattern="^(?:0|\(?\+[1-9]*\)?\s?|00[1-9]*\s?)[1-99](?:[\.\-\s]?\d\d){4}$" name="telephoneMobile" value="${entity.telephoneMobile}" required class="form-control">
            <label><language:language message="email"/></label>
            <input type="email" name="email" value="${entity.email}" required class="form-control">
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
