<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<bs:modal icon="envelope">
    <form method="POST" action="" class="form-mail row">
        <label></label>
        <input type="text" required name="from" value="${user.email}" class="form-control">
        <label></label>
        <input type="text" required name="to" value="${param.to}" class="form-control">
        <label></label>
        <input type="text" required name="subject" class="form-control">
        <label></label>
        <textarea name="body" required class="form-control ckeditor" id="editor1"> </textarea>
        <script>
            $('textarea').ckeditor();
        </script>
        <label></label>
        <input type="hidden" name="MAIL" value="TRUE">
        <input type="hidden" name="SEND" value="TRUE">
        <button class="btn btn-success"> <span class="fa fa-send-o"></span></button>
    </form>
</bs:modal>
