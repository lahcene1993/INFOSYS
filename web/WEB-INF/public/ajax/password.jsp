<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags/bootstrap/" prefix="bs" %>
<%@taglib tagdir="/WEB-INF/tags/language/" prefix="language" %>
<bs:modal icon="warning">
    <form method="POST" name="form" action=""  class="row">
        <label></label>
        <input type="password" required name="oldpassword" class="form-control">
        <label></label>
        <input type="password" required onchange="form.confirmpassword.pattern=this.value" name="newpassword" class="form-control">
        <label></label>
        <input title="mot de pass et confirmation non identiques" required type="password" name="confirmpassword" class="form-control">
        <label></label>
        <button class="btn btn-success"> <span class="fa fa-save"></span></button>
        <button class="btn btn-warning"> <span class="fa fa-remove"></span></button>
    </form>
</bs:modal>
