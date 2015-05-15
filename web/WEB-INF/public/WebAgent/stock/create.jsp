<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<bs:modal id="client" icon="cart">
    <form id="form-stock" class="container-fluid">
        <div class="col-md-6">
            <label><language:language message="provider" /></label>
            <select  name="fournisseur" required class="form-control">
                <c:forEach items="${requestScope.fournisseurs}" var="fournisseur">
                    <option value="${fournisseur.id}">${fournisseur.nom}</option>
                </c:forEach>
            </select>
            <label><language:language message="date" /></label>
            <input type="date" required value="<f:formatDate value="${sessionScope.stock.date}" pattern="yyyy-MM-dd"/>" pattern="(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))" name="date" class="form-control input-sm"> 

        </div>
        <div class="col-md-6 well well-sm">
            <datalist id="products">
                <c:forEach items="${requestScope.products}" var="product">
                    <option value="${product.libele}">${product.libele}</option>
                </c:forEach>
            </datalist>
            <div class="">
                <input type="text" required list="products" placeholder="<language:language message="product" />" name="product" class="form-control input-sm">
                <input type="text" required pattern="[0-9]*" placeholder="<language:language message="quantity" />" name="quantite" class="form-control input-sm">
                <input type="text" required pattern="[-+]?[0-9]*[.]?[0-9]+" placeholder="<language:language message="price" />" name="prix" class="form-control input-sm">
                <input type="hidden" name="STOCK" value="TRUE">
               <input type="hidden" name="ADD" value="TRUE"> 
                <br>
                <button type="submit" class="btn btn-success input-sm"><span class="fa fa-plus"></span></button>
            </div>
        </div>
    </form>
        <label></label>
            <table class="table table-striped table-bordered table-condensed table-hover">
                <thead>
                    <tr>
                        <td><language:language message="product"/></td>
                        <td><language:language message="quantity"/></td>
                        <td><language:language message="price"/></td>
                        <td><language:language message="provider"/></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody id="table-body">
                    <c:forEach items="${sessionScope.stock.entrySet()}" var="entry">
                        <tr>
                            <td>${entry.value.product.libele}</td>
                            <td>${entry.value.quantite}</td>
                            <td>${entry.value.prix}</td>
                            <td>${entry.value.fournisseur.nom}</td>
                            <td>
                                <form method="POST" class="form-remove">
                                    <button type="submit" class="btn btn-danger btn-xs btn-block">
                                        <span class="fa fa-remove"></span> <language:language message="remove"/>
                                    </button>
                                    <input type="hidden" name="STOCK"  value="TRUE">
                                    <input type="hidden" name="REMOVE" value="TRUE">
                                    <input type="hidden" name="product" value="F${entry.value.fournisseur.id}P${entry.value.product.id}D${entry.value.date}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
       <form method="POST" action="" class="row">
        <input type="hidden" name="SAVE" value="TRUE">
        
        <input type="hidden" name="id" value="${entity.id}">
        <button class="btn btn-primary btn-xs" ><span></span> <language:language message="save"/> </button>
    </form>
</bs:modal>