<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="${cfg.sys_name}"/>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>

    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
</head>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet light">

    <div class="portlet-body" id="courseSource">
        <div class="form-panel">
            <!--具体界面元素开始-->
            <form class="form-horizontal fm-page" id="fm-edit" role="form">

            </form>
        </div>
        <!--具体界面元素结束-->
    </div>
</div>

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>
<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group hide">
            <label class="col-md-2 control-label"> 主键 </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="id"
                       value="\${data.o.id}" maxlength="50">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                行政区划
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input class="form-control" id="district" name="district"
                       style="width:275px;﻿line-height: 30px;height: 30px;">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                分区编号
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="code"
                       value="\${data.o.code}" maxlength="19"
                       placeholder="请输入分区编号（20位有效整型数据)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                分区名称
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name"
                       value="\${data.o.name}" maxlength="50"
                       placeholder="请输入分区名称（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label"> 分区描述 </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="depict"
                       value="\${data.o.depict}" maxlength="200"
                       placeholder="请输入分区描述（建议字数在14个字以内，不超过200个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 control-label">
                详细地址
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="address" maxlength="200"
                       value="\${data.o.address}"
                       placeholder="请选择详细地址"
                       style="float:left;width:92%">
                <a href="javascript:window.open('${portalPath}/dynamic/common/map.jsp')"
                   style="float:right;line-height: 34px;">选择</a>
                <span class="help-block"></span>
                <%--纬度--%>
                <input name="latitude" value="\${data.o.latitude}" type="hidden"/>
                <%--经度--%>
                <input name="longitude" value="\${data.o.longitude}" type="hidden"/>
                <div class="error-address"></div>
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 control-label">
                第三方分区
            </label>
            <div class="col-md-10">
                <div class="radio-group-container">
                    <label>
                        <input type="radio" name="thirdTag" value="0" {@if data.o.thirdTag==0}checked{@/if}><span
                            style="padding:10px">否</span>
                    </label>
                    <label>
                        <input type="radio" name="thirdTag" value="1" {@if data.o.thirdTag==1}checked{@/if}><span
                            style="padding:10px">是</span>
                    </label>
                </div>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group hide">
            <label class="col-md-2 control-label">
                备注
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="remark"
                       value="\${data.o.remark}" maxlength="200"
                       placeholder="请输入备注（建议字数在14个字以内，不超过200个字)">
                <span class="help-block"></span>
            </div>
        </div>
    </div>
    <div class="form-actions">
        <div class="row">
            <div class="col-md-offset-3 col-md-7">
                <button class="btn   green" type="submit" style="width:30%">保存</button>
            </div>
        </div>
    </div>
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>

<style>
    .fm-page {
        max-width: 80%;
    }
</style>


<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/cropUpload.js?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>

</html>
