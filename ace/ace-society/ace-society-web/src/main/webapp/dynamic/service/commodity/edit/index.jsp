<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="${cfg.sys_name}"/>
    <%--common css--%>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <%--custom css--%>
</head>

<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>

<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet light">
    <div class="portlet-title">
        <div class="caption">
            编辑爱心商品
        </div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body" id="courseSource">
        <div class="form-panel">
            <!--具体界面元素开始-->
            <form class="form-horizontal" id="fm-edit" role="form">

            </form>
        </div>
        <!--具体界面元素结束-->
    </div>
</div>
<!-- END SAMPLE TABLE PORTLET-->

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>

<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group hide">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                主键ID
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="id" value="\${data.o.id}">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                商品名称
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="commodityName" value="\${data.o.commodityName}"
                       maxlength="50" placeholder="请输入商品名称（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                商品分类
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="category" value="\${data.o.category}" maxlength="2"
                       placeholder="请输入商品分类（建议字数在14个字以内，不超过2个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                商品封面
            </label>
            <div class="col-md-10" style="text-align: left">
                <img class="cover"
                     id="coverUrl"
                     data-toggle="modal"
                     data-xsize="150" data-ysize="150"
                     data-cover="coverUrl"
                     data-target="#cropModal"
                     src="${pageContext.request.contextPath}/content/common/img/addImg1.png?v=${cfg.version}">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                商品简介
            </label>
            <div class="col-md-10" style="text-align: left">
                <textarea type="text" class="form-control" name="summary" value="\${data.o.summary}"
                          maxlength="2147483647"
                          placeholder="请输入商品简介（建议字数在14个字以内，不超过2147483647个字)">
                </textarea>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                购买所需积分
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="costPoints" value="\${data.o.costPoints}" maxlength="10"
                       placeholder="请输入购买所需积分（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                备注
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="remark" value="\${data.o.remark}" maxlength="200"
                       placeholder="请输入备注（建议字数在14个字以内，不超过200个字)">
                <span class="help-block"></span>
            </div>
        </div>
    </div>
    <div class="form-actions">
        <div class="row">
            <div class="col-md-offset-3 col-md-7">
                <button class="btn  btn-lg  green" type="submit" style="width:30%">保存</button>
            </div>
        </div>
    </div>
</script>
<style>
    .cover {
        width: 150px;
        height: 150px;
        background: black;
        object-fit: cover;
    }
</style>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
