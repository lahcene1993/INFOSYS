<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:admin>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <form method="POST" class="well well-lg">
            <div class="col-xs-6">
                <label><language:language message="name"/></label>
                <input name="nom" required value="${entity.nom}" class="form-control" type="text" />
                <label><language:language message="fiscal"/></label>
                <input name="identifiantFiscal" required value="${entity.identifiantFiscal}" class="form-control" type="text" />
                <label><language:language message="rc"/></label>
                <input name="registreCommerce" required value="${entity.registreCommerce}" class="form-control" type="text" />

            </div>
            <div class="col-xs-6">
                <label><language:language message="phone"/></label>
                <input name="telephoneFixe" pattern="^(?:0|\(?\+[1-9]*\)?\s?|00[1-9]*\s?)[1-99](?:[\.\-\s]?\d\d){4}$" required value="${entity.telephoneFixe}" class="form-control" type="text" />
                <label><language:language message="faxe"/></label>
                <input name="faxe" pattern="^(?:0|\(?\+[1-9]*\)?\s?|00[1-9]*\s?)[1-99](?:[\.\-\s]?\d\d){4}$" required value="${entity.faxe}" class="form-control" type="text" />
                <label><language:language  message="adress"/></label>
                <input name="adresse" required value="${entity.adresse}" class="form-control" type="text" />
                <label><language:language message="city"/></label>
                <input name="ville" required value="${entity.ville}" class="form-control" type="text" />
                <label><language:language message="zip"/></label>
                <input name="codePostale" required value="${entity.codePostale}" class="form-control" type="text" />
                <label><language:language message="country"/></label>
                <input name="pays" required value="${entity.pays}" class="form-control" type="text" />
                <br>
            </div>
            <input type="hidden" name="version" value="${entity.version}">
            <input type="hidden" name="id" value="${entity.id}">
            <input type="hidden" name="SAVE" value="TRUE">
            <label></label>
            <div class="row">
                <input type="submit" class="btn btn-success btn-xs btn-block" value="<language:language message="save"/>">
            </div>
        </form>
    </div>
</template:admin>