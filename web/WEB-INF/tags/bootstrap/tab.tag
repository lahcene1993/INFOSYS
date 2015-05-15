<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="Bootstrap Tab" pageEncoding="UTF-8"%>
<%@attribute name="data" type="java.util.HashMap"%>
<ul id="myTab" class="nav nav-tabs">
    <c:forEach var="entry" items="${data.entrySet()}" varStatus="step">
        <li class="${step.first ?'active':''}"><a href="#${entry.key}" data-toggle="tab">${entry.key}</a>
        </li>
    </c:forEach>
</ul>
<div id="myTabContent" class="tab-content">
    <c:forEach var="entry" items="${data.entrySet()}" varStatus="step" >
        <div class="tab-pane fade in ${step.first?'active':''}" id="${entry.key}">
            <i class="fa fa-gear pull-left fa-4x"></i>
            ${entry.value}
        </div>
    </c:forEach>
</div>