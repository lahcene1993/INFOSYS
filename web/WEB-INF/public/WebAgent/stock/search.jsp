<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<div id="ajax">
    <c:choose>
        <c:when test="${empty entites}">
            <bs:alert type="danger" icon="trash">
                <br>
                <language:language message="Not Found !!!!!!!"/>
            </bs:alert>
        </c:when> 
        <c:otherwise>
            <bs:panel>
                <table class="table table-hover table-condensed table-responsive table-bordered table-striped">
                    <thead>
                        <tr>
                            <td></td>
                            <td><language:language message="product"/></td>
                            <td><language:language message="quantity"/></td>
                            <td><language:language message="price"/></td>
                            <td><language:language message="date"/></td>
                            <td><language:language message="provider"/></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${entites}" var="entity">
                            <tr>
                                <td><span class="fa fa-bars"></span></td>
                                <td>${entity.product.libele}</td>
                                <td>${entity.quantite}</td>
                                <td>${entity.prix}</td>
                                <td><f:formatDate value="${entity.date}" pattern="yyyy-MM-dd"/></td>
                                <td>${entity.fournisseur.nom}</td>
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
                                        <li class="pagination-sm"><a href="?page=${i}" >${i}</a></li>
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
