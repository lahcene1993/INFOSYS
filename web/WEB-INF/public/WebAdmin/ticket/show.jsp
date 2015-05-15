<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<template:admin>
    <div class="container-fluid">
        <c:forEach items="${entites}" var="message">
            <div class="panel panel-${message.sent?'primary':'success'}">
                <div class="panel-heading">
                    <div class="panel-title"><f:formatDate value="${message.date}" pattern="yyyy-MM-dd"/><span class="fa fa-${message.sent?'user pull-left':'support pull-right'}"></span></div>
                </div>
                <div class="panel-body">
                    ${message.message}
                </div>
            </div>
            <c:set var="id" scope="page" value="${message.ticket.id}"/>
        </c:forEach>
        <div>
            <form method="POST">
                <textarea name="message" required class="form-control"></textarea>
                <c:if test="${sessionScope.user.role.name() eq 'CLIENT'}">
                    <input type="hidden" name="sent" value="TRUE">
                </c:if>
                <input type="hidden" name="SEND" value="TRUE">
                <input type="hidden" name="ticket" value="${id}">
                <button class="btn btn-primary"><span class="fa fa-send-o"></span></button>
            </form>
        </div>
    </div>
</template:admin>