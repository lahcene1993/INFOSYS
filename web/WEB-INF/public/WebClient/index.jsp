<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:client>
    <div class="col-xs-6">
            <label><language:language message="name"/></label>
            <input type="text" readonly name="nom" value="${entity.nom}" required class="form-control">
        </div>
        <div class="col-xs-6">
            <label><language:language message="fiscal"/></label>
            <input type="text" readonly name="identifiantFiscal" value="${entity.identifiantFiscal}" required class="form-control" >
        </div>
        <div class="col-xs-6">
            <label><language:language message="city"/></label>
            <input type="text" readonly name="ville" value="${entity.ville}" required class="form-control">
            <label><language:language message="country"/></label>
            <input type="text" readonly name="pays" value="${entity.pays}" required class="form-control">
            <label><language:language message="zip"/></label>
            <input type="text" readonly name="codePostal" value="${entity.codePostal}" required class="form-control">
            <label><language:language message="adress"/></label>
            <input type="text" readonly name="adress" value="${entity.adress}" required class="form-control">
        </div>
        <div class="col-xs-6">
            <label><language:language message="phone"/></label>
            <input type="text" readonly name="telephoneFixe" value="${entity.telephoneFixe}" required class="form-control">
            <label><language:language message="mobile_phone"/></label>
            <input type="text" readonly name="telephoneMobile" value="${entity.telephoneMobile}" required class="form-control">
            <label><language:language message="email"/></label>
            <input type="text" readonly name="email" value="${entity.email}" required class="form-control">
            <label>Faxe</label>
            <input type="text" readonly name="fax" value="${entity.fax}" class="form-control">
        </div
</template:client>