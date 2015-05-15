<%@tag description="Bootstrap Modal" pageEncoding="UTF-8"%>
<%@attribute name="title" %>
<%@attribute name="id" %>
<%@attribute name="icon" %>
<div class="modal fade" id="${id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel"><span class="fa fa-${icon}"></span> ${title}</h4>
            </div>
            <div class="modal-body">
                <jsp:doBody/>
            </div>
            <div class="modal-footer">
               
            </div>
        </div>
    </div>
</div>