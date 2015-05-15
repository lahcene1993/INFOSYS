<%@tag description="Bootstrap Alert" pageEncoding="UTF-8"%>
<%@attribute name="type"%>
<%@attribute name="title"%>
<%@attribute name="id" %>
<%@attribute name="icon" %>
<div id="${id}" class="box box-${type eq null ? 'default':type}">
    <div class="box-header">
        <h3 class="box-title"><span class="fa fa-${icon}"></span> ${title}</h3>
        <div class="box-tools pull-right">
            <button class="btn btn-default btn-sm" data-widget="collapse" data-toggle="tooltip" title="Collapse"><i class="fa fa-minus"></i></button>
            <button class="btn btn-default btn-sm" data-widget="remove" data-toggle="tooltip" title="Remove"><i class="fa fa-times"></i></button>
        </div>
    </div>
    <div class="box-body">
        <jsp:doBody/>
    </div><!-- /.box-body -->
    <div class="box-footer">
    </div><!-- /.box-footer-->
</div><!-- /.box -->