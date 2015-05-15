<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="Bootstrap Accordion" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="b" %>
<%@attribute name="data" type="java.util.HashMap"%>
<%@attribute name="type"%>
<%@attribute name="title"%>
<b:panel type="${type}" title="${title}">
    <div class="panel-group" id="accordion">
        <c:forEach var="entry" items="${data.entrySet()}" varStatus="step" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a class="" data-toggle="collapse" data-parent="#accordion" href="#${entry.key}">${entry.key}</a>
                    </h4>
                </div>
                <div id="${entry.key}" class="panel-collapse collapse ${step.first?'in':''}">
                    <div class="panel-body">
                        ${entry.value}
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</b:panel>
