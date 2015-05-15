<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/template/" prefix="template" %>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<template:agent>
    <div class="col-md-12">
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="fa fa-cube"></i></span>
                <div class="info-box-content">
                    <span class="info-box-text"><language:language message="products"/></span>
                    <span class="info-box-number">${state.products}</span>
                </div><!-- /.info-box-content -->
            </div><!-- /.info-box -->
        </div><!-- /.col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-red"><i class="fa fa-users"></i></span>
                <div class="info-box-content">
                    <span class="info-box-text"><language:language message="costumers"/></span>
                    <span class="info-box-number">${state.clients}</span>
                </div><!-- /.info-box-content -->
            </div><!-- /.info-box -->
        </div><!-- /.col -->

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-green"><i class="fa fa-users"></i></span>
                <div class="info-box-content">
                    <span class="info-box-text"><language:language message="providers"/></span>
                    <span class="info-box-number">${state.fournisseurs}</span>
                </div><!-- /.info-box-content -->
            </div><!-- /.info-box -->
        </div><!-- /.col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-yellow"><i class="fa fa-file-word-o"></i></span>
                <div class="info-box-content">
                    <span class="info-box-text"><language:language message="sales"/></span>
                    <span class="info-box-number">${state.factures+state.livraisons+state.commandes+state.devis}</span>
                </div><!-- /.info-box-content -->
            </div><!-- /.info-box -->
        </div><!-- /.col -->
    </div>

    <div class="col-md-12">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">Rapport Annuel</h3>
                <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                    <button class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                </div>
            </div><!-- /.box-header -->
            <div  class="box-body">
                <div class="row">
                    <div class="col-xs-6">
                        <div id="canvas-holder">
			<canvas id="chart-area" width="300" height="300"/>
		</div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- ./box-body -->
            <div style="display: none;" class="box-footer">
            </div><!-- /.box-footer -->
        </div><!-- /.box -->
    </div>
                                    
     
<script type="text/javascript" src="<c:url value="/assets/js/Chart.js"/>"></script>
    <script>

		var randomScalingFactor = function(){ return Math.round(Math.random()*100)};
		var randomColorFactor = function(){ return Math.round(Math.random()*255)};

		var pieData = [
				{
					value: ${state.factures},
					color:"#F7464A",
					highlight: "#FF5A5E",
					label: "Factures"
				},
				{
					value: ${state.livraisons},
					color: "#46BFBD",
					highlight: "#5AD3D1",
					label: "Livraisons"
				},
				{
					value: ${state.devis},
					color: "#FDB45C",
					highlight: "#FFC870",
					label: "Devis"
				},
				{
					value: ${state.commandes},
					color: "#949FB1",
					highlight: "#A8B3C5",
					label: "Commandes"
				}
			];

			window.onload = function(){
				var ctx = document.getElementById("chart-area").getContext("2d");
				window.myPie = new Chart(ctx).Pie(pieData);
			};

			$('#randomizeData').click(function(){
				$.each(pieData, function(i, piece){
					pieData[i].value = randomScalingFactor();
			    	pieData[i].color = 'rgba(' + randomColorFactor() + ',' + randomColorFactor() + ',' + randomColorFactor() + ',.7)';
				});
		    	window.myPie.update();
		    });



	</script>                          
</template:agent>