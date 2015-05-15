<%@tag description="Bootstrap Alert" pageEncoding="UTF-8"%>
<%@attribute name="type"%>
<%@attribute name="id" %>
<%@attribute name="icon" %>
<div id="${id}" class="alert alert-${type eq null ? 'danger':type}">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <span class="fa fa-${icon}"></span>
    <jsp:doBody/>
</div>