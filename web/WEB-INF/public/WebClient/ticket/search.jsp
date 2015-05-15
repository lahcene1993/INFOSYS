<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<div id="ajax">
    <c:choose>
        <c:when test="${empty entites}">
            <bs:alert type="danger" icon="trash">
                <br>
                <language:language message="not_found"/>
            </bs:alert>
        </c:when> 
        <c:otherwise>
            <bs:panel>
                <table class="table table-hover table-condensed">
                    <thead>
                        <tr>
                            <td></td>
                            <td><language:language message="name"/></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${entites}" var="entity">
                            <tr>
                                <td><a class="badge">${entity.id}</a></td>
                                <td>${entity.name}</td>
                                <td style="width: 100px;">
                                    <form class="form-show" onsubmit="return false;" method="POST">
                                        <input type="hidden" name="id" value="${entity.id}">
                                        <input type="hidden" name="SHOW" value="TRUE">
                                        <button class="btn btn-primary btn-xs btn-block"><span class="fa fa-eye"></span> <language:language message="edit"/></button>
                                    </form>
                                </td>
                                <td style="width: 100px;">
                                    <form class="form-delete" onsubmit="" method="POST">
                                        <input type="hidden" name="id" value="${entity.id}">
                                        <input type="hidden" name="DELETE" value="TRUE">
                                        <button class="btn btn-warning btn-xs btn-block"><span class="fa fa-remove"></span> <language:language message="remove"/></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="5">
                                <ul class="list-inline pagination">
                                    <c:forEach var="i" begin="1" end="${requestScope.count+1}" step="1">
                                        <li class="pagination-sm"><a href="#" >${i}</a></li>
                                    </c:forEach>
                                </ul>  
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </bs:panel>
        </c:otherwise>
    </c:choose>
</div>
