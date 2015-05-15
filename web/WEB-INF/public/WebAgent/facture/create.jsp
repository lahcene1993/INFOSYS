<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<div id="ajax" class="container-fluid well well-lg">
    <div class="container-fluid">
        ${facture}
    </div>
    <datalist id="produits">
        <c:forEach items="${produits}" var="item">
            <option itemid="${item.id}" itemprop="${item.quantite}">${item.libele}</option>
        </c:forEach>
    </datalist>
    <fieldset>
        <legend>Client</legend>
        <form id="form-order" class="form" method="POST">
            <select  class="input-sm form-control" required name="client">
                <c:forEach items="${clients}" var="item">
                    <option ${facture.client.id eq item.id ? 'selected':''} value="${item.id}">${item.nom}</option>
                </c:forEach>
            </select>
            <select  class="input-sm form-control" required name="mode">
                <c:forEach items="${modes}" var="item">
                    <option ${facture.mode.id eq item.id ? 'selected':''} value="${item.id}">${item.name}</option>
                </c:forEach>
            </select>
            <select  class="input-sm form-control" required name="tax">
                <c:forEach items="${taxes}" var="item">
                    <option ${facture.tax.id eq item.id ? 'selected':''} value="${item.id}">${item.value}</option>
                </c:forEach>
            </select>
            <select  class="input-sm form-control" required name="type">
                <c:forEach items="${types}" var="item">
                    <option ${facture.type.ordinal() eq item.ordinal() ? 'selected':''} value="${item.ordinal()}">${item.name()}</option>
                </c:forEach>
            </select>
            <input type="date" required value="<f:formatDate value="${facture.date}" pattern="yyyy-MM-dd" />" name="date" class="input-sm"/>
            <input type="hidden" name="SET" value="TRUE">
            <input type="submit" >
        </form>
    </fieldset>
    <form method="POST" class="form">
        <table class="table">
            <thead>
                <tr>
                    <td><language:language message="product"/></td>
                    <td><language:language message="price"/></td>
                    <td><language:language message="quantity"/></td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><input required list="produits" name="product" class="input-sm" placeholder=""/></td>
                    <td><input required name="prix" pattern="[0-9]*[.]?[0-9]+"  class="input-sm" placeholder=""/></td>
                    <td><input required name="quantite" pattern="[0-9]*" class="input-sm" placeholder=""/></td>
                    <td><button  class="btn btn-default"><span class="fa fa-plus"></span></button></td>
                </tr>
            </tbody>
        </table>
        <input type="hidden" name="ADD"  value="TRUE">
    </form>
    <table class="table table-striped table-bordered table-condensed table-hover">
        <thead>
            <tr>
                <td>Produit</td>
                <td>Prix</td>
                <td>Quantite</td>
                <td></td>
                <td></td>
            </tr>
        </thead>
        <tbody id="table-body">
            <c:forEach items="${facture.items}" var="item">
                <tr>
                    <td>${item.product.libele}</td>
                    <td>${item.prix}</td>
                    <td>${item.quantite}</td>
                    <td></td>
                    <td>
                        <form method="POST" action="" class="form row">
                            <input type="hidden" name="REMOVE" value="TRUE">
                            <input type="hidden" name="libele" value="${item.product.libele}">
                            <button class="btn btn-success btn-xs btn-block" ><span class="fa fa-remove"></span>  </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <form method="POST" action="" class="form row">
        <input type="hidden" name="SAVE" value="TRUE">
        <button class="btn btn-success btn-xs btn-block" ><span class="fa fa-outdent"></span>  </button>
    </form>
    <form method="POST" action="" class="form row">
        <input type="hidden" name="CLEAR" value="TRUE">
        <button class="btn btn-danger btn-xs btn-block" ><span class="fa fa-close"></span>  </button>
    </form>
</div>