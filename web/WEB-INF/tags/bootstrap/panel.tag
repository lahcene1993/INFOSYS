<%@tag description="Bootstrap Panel" pageEncoding="UTF-8"%>
<%@attribute name="type"%>
<%@attribute name="title"%>
<%@attribute name="id" %>
<%@attribute name="icon" %>
<div id="${id}" class="panel panel-${type eq null ? 'default':type}">
    <div class="panel-heading">
        <div class="panel-title"><span class="fa fa-${icon}"></span> ${title}</div>
    </div>
    <div class="panel-body">
        <jsp:doBody/>
    </div>
    <div class="panel-footer">
    </div>
</div>
