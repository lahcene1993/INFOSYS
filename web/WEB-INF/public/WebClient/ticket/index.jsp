<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:client>
    <div class="col-md-12">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title"><span class="fa fa-envelope"></span></h3>
                <div class="box-tools pull-right">
                    <button type="button" id="create" class="btn btn-success btn-xs"><span class="fa fa-plus"><language:language message="new"/></span></button>
                </div><!-- /.box-tools -->
            </div><!-- /.box-header -->
            <div class="box-body no-padding">
                <div class="table-responsive mailbox-messages">
                    <table class="table table-hover table-striped table-condensed">
                        <tbody>
                            <c:forEach items="${entites}" var="entity">
                                <tr class="${entity.etat eq true ?'success':'warning'}">
                                    <td class="mailbox-name">${entity.name}</td>
                                    <td class="mailbox-date"><f:formatDate value="${entity.date}" pattern="yyyy-MM-dd"/></td>
                                    <td>
                                        <form method="POST">
                                            <button class="btn btn-default btn-block btn-xs"><span class="fa fa-eye"></span></button>
                                            <input type="hidden" name="id" value="${entity.id}">
                                            <input type="hidden" name="SHOW" value="${entity.id}">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table><!-- /.table -->
                </div><!-- /.mail-box-messages -->
            </div><!-- /.box-body -->
            <div class="box-footer no-padding">
            </div>
        </div><!-- /. box -->
    </div>
</template:client>