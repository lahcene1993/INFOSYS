<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<bs:modal id="client">
    <form method="POST" action="" class="row">
        <div class="col-xs-12">
            <label><language:language message="product"/></label>
            <input type="text" required="required" value="${entity.libele}" name="libele" class="form-control">
            <label><language:language message="description"/></label>
            <input type="text" value="${entity.description}" name="description" class="form-control">
            <label><language:language message="price"/></label>
            <input type="text" pattern="[0-9]*[.]?[0-9]+" required="required" value="${entity.prix}" name="prix" class="form-control">
            <label><language:language message="quantity"/></label>
            <input type="text" pattern="[0-9]*" required="required" value="${entity.quantite}" name="quantite" class="form-control">
            <label><language:language message="category"/></label>
            <select name="category" required="required" class="form-control">
                <c:forEach var="item" items="${categories}">
                    <option value="${item.id}" ${item.id eq entity.category.id ? 'selected':''}>${item.nom}</option>
                </c:forEach>
            </select>
        </div>
        <input type="hidden" name="SAVE" value="TRUE">
        <input type="hidden" name="version" value="${entity.version}">
        <input type="hidden" name="id" value="${entity.id}">
        <div class="col-xs-12">
            <br>
            <button type="submit" class="btn btn-success btn-block btn-xs"><span class="fa fa-plus-square-o"></span></button>
        </div>
    </form>
</bs:modal>