<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<div id="ajax" class="container-fluid well well-lg">
    <!-- Main content -->
    <section class="content invoice">
        <div class="row center-block">
            <h1 class="text-center">
              ${entity.type}  
            </h1>
            
        </div>
        <!-- info row -->
        <div class="row invoice-info">
            <div class="col-sm-4 invoice-col">
                <address>
                    <strong><language:language message="costumer"/> : ${entity.client}</strong><br>
                    <strong><language:language message="date"/> : <f:formatDate value="${entity.date}" pattern="yyyy-MM-dd"/></strong><br>
                    <strong>Code : ${entity.id}</strong><br>
                </address>
            </div><!-- /.col -->
        </div><!-- /.row -->
        <!-- Table row -->
        <div class="row">
            <div class="col-xs-12 table-responsive">
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <td><language:language message="product"/></td>
                            <td><language:language message="price"/></td>
                            <td><language:language message="quantity"/></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${entity.items}" var="item">
                            <tr>
                                <td>${item.product}</td>
                                <td>${item.prix}</td>
                                <td>${item.quantite}</td>
                                <td>${item.quantite * item.prix}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div><!-- /.col -->
        </div><!-- /.row -->

        <div class="row">
            <!-- accepted payments column -->
            <div class="col-xs-6"></div><!-- /.col -->
            <div class="col-xs-6">
                <div class="table-responsive">
                    <table class="table">
                        <tr>
                            <th style="width:50%"><language:language message="subtotal" /></th>
                            <td>${entity.total}</td>
                        </tr>
                        <tr>
                            <th><language:language message="tva" /> (${entity.tax}%)</th>
                            <td>${(entity.tax.value*entity.total)/100}</td>
                        </tr>
                        <tr>
                            <th><language:language message="total" /></th>
                            <td>${(entity.tax.value*entity.total)/100 + entity.total}</td>
                        </tr>
                    </table>
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->

        <!-- this row will not appear when printing -->
        <div class="row">
            <div class="col-xs-12">
                <form method="POST" action="" target="">
                    <input type="hidden" name="id" value="${entity.id}">
                    <input type="hidden" name="type" value="${entity.type}">
                    <input type="hidden" name="PDF" value="TRUE">
                    <button type="button" class="btn btn-default" onclick="window.print();"><i class="fa fa-print"></i> Print</button>
                    <button class="btn btn-primary pull-right" style="margin-right: 5px;"><i class="fa fa-download"></i></button>
                </form>
            </div>
        </div>
    </section><!-- /.content -->
</div>