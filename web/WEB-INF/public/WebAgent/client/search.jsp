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
                <table class="table table-hover table-condensed table-responsive table-bordered  table-striped">
                    <thead>
                        <tr>
                            <td></td>
                            <td><language:language message="name"/></td>
                            <td><language:language message="email"/></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${entites}" var="entity">
                            <tr>
                                <td><span class="fa fa-bars"></span></td>
                                <td>${entity.nom}</td>
                                <td>${entity.email}</td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-expanded="true">
                                            
                                            <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu2">
                                            <li role="presentation">
                                                    <form class="form-mail" method="POST">
                                                        <input type="hidden" name="id" value="${entity.id}">
                                                        <input type="hidden" name="to" value="${entity.email}">
                                                        <input type="hidden" name="MAIL" value="TRUE">
                                                        <input type="hidden" name="NEW" value="TRUE">
                                                        <button class="btn btn-default btn-xs btn-block"><span class="fa fa-envelope"></span></button>
                                                    </form>
                                            </li>
                                            <li role="presentation">
                                                    <form class="form-show" method="POST">
                                                        <input type="hidden" name="id" value="${entity.id}">
                                                        <input type="hidden" name="SHOW" value="TRUE">
                                                        <button class="btn btn-primary btn-xs btn-block"><span class="fa fa-eye"></span> <language:language message="edit"/></button>
                                                    </form>
                                            </li>
                                            <li role="presentation">
                                                    <form class="form-delete" method="POST">
                                                        <input type="hidden" name="id" value="${entity.id}">
                                                        <input type="hidden" name="DELETE" value="TRUE">
                                                        <button class="btn btn-warning btn-xs btn-block"><span class="fa fa-remove"></span> <language:language message="remove"/></button>
                                                    </form>
                                            </li>
                                        </ul>
                                    </div>

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
