<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Bootstrap Dashboard" pageEncoding="UTF-8"%>
<%@taglib  tagdir="/WEB-INF/tags/bootstrap/" prefix="b" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core" %>
<%@taglib tagdir="/WEB-INF/tags/ui/" prefix="ui" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<c:if test="${sessionScope.user.role.name() ne 'MANAGER'}">
    <c:redirect url="logout.html"></c:redirect>
</c:if>
<template:dashboard>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
            </div>
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="${requestScope['javax.servlet.forward.servlet_path'] eq '/client.sma'? 'active':''}">
                    <a href="client.sma">
                        <i class="fa fa-users"></i> <span><language:language message="costumers"></language:language></span>
                        </a>
                    </li>
                    <li class="${requestScope['javax.servlet.forward.servlet_path'] eq '/fournisseur.sma'? 'active':''}">
                    <a href="fournisseur.sma">
                        <i class="fa fa-users"></i> <span><language:language message="providers"></language:language></span>
                        </a>
                    </li>
                    <li class="${requestScope['javax.servlet.forward.servlet_path'] eq '/product.sma'? 'active':''}">
                    <a href="product.sma">
                        <i class="fa fa-cubes"></i> <span><language:language message="products"></language:language></span>
                        </a>
                    </li>
                    <li class="${requestScope['javax.servlet.forward.servlet_path'] eq '/stock.sma'? 'active':''}">
                    <a href="stock.sma">
                        <i class="fa fa-file-archive-o"></i> <span><language:language message="stock"></language:language></span>
                        </a>
                    </li>
                    <li class="${requestScope['javax.servlet.forward.servlet_path'] eq '/category.sma'? 'active':''}">
                    <a href="category.sma">
                        <i class="fa fa-folder-open-o"></i> <span><language:language message="category"></language:language></span>
                        </a>
                    </li>
                    <li class="${requestScope['javax.servlet.forward.servlet_path'] eq '/facture.sma'? 'active':''}">
                    <a href="facture.sma">
                        <i class="fa fa-files-o"></i> <span><language:language message="orders"></language:language></span>
                        </a>
                    </li>
                </ul>
            </section>
            <!-- /.sidebar -->
        </aside>
        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side">
            <!-- Content Header (Page header) -->
            <section class="content-header">
            </section>
            <!-- Main content -->
            <section class="content">
            <core:if test="${requestScope.error ne null}">
                <b:alert type="danger">
                    <p>${requestScope.error}</p>
                    <br>
                </b:alert>
            </core:if>
            <core:if test="${requestScope.success  ne null}">
                <b:alert type="success">
                    <p>${requestScope.success}</p>
                    <br>
                </b:alert>
            </core:if>
            <core:if test="${requestScope.info ne null}">
                <b:alert type="info">
                    <p>${requestScope.info}</p>
                    <br>
                </b:alert>
            </core:if>
            <core:if test="${requestScope.warning  ne null}">
                <b:alert type="warning">
                    <p>${requestScope.warning}</p>
                    <br>
                </b:alert>
            </core:if>
            <div class="container-fluid">
                <div class="row">
                    <jsp:doBody/>
                </div>
            </div>
        </section><!-- /.content -->
    </aside><!-- /.right-side -->
</template:dashboard>