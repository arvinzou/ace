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
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">

</head>

<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>

<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet light">
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
                场地名称
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="commodityName" value="\${data.o.commodityName}"
                       maxlength="50" placeholder="请输入场地名称（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <%--<div class="form-group">--%>
        <%--<label class="col-md-2 control-label">--%>
        <%--场地分类--%>
        <%--</label>--%>
        <%--<div class="col-md-10">--%>
        <%--<input type="text" class="form-control" name="category" value="\${data.o.category}" maxlength="2"--%>
        <%--placeholder="请输入场地分类（建议字数在14个字以内，不超过2个字)">--%>
        <%--<span class="help-block"></span>--%>
        <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <label class="col-md-2 control-label">
                场地封面
            </label>
            <div class="col-md-10" style="text-align: left">
                {@if data.o.commodityCover!=undefined && data.o.commodityCover!=null && data.o.commodityCover!=''}
                <img class="cover"
                     id="commodityCover"
                     data-toggle="modal"
                     data-xsize="187" data-ysize="155"
                     data-cover="commodityCover"
                     data-target="#cropModal"
                     src="\${data.o.commodityCover}">
                <span class="help-block"></span>
                {@else}
                <img class="cover"
                     id="commodityCover"
                     data-toggle="modal"
                     data-xsize="187" data-ysize="155"
                     data-cover="commodityCover"
                     data-target="#cropModal"
                     src="${pageContext.request.contextPath}/content/common/img/addImg1.png?v=${cfg.version}">
                <span class="help-block"></span>
                {@/if}
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                场地简介
            </label>
            <div class="col-md-10" style="text-align: left">
                <textarea type="text" class="form-control" name="summary" value="\${data.o.summary}"
                          maxlength="2147483647"
                          placeholder="请输入场地简介（建议字数在14个字以内，不超过2147483647个字)">
                </textarea>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                爱心币
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
                <button class="btn  btn-lg  green" type="submit" style="width:20%">保存</button>
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
<%----%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<%--custom js--%>
<script type="text/javascript"
        src="/portal/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/common/js/cropUpload.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>

<script src="js/act.js?v=${cfg.version}"></script>
</html>
