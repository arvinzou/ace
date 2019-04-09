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

    <div class="portlet-body">
        <div class="form-panel" id="fm-add-panel">
            <!--具体界面元素开始-->

        </div>

    </div>
</div>


<script id="tpl-fm-add" type="text/template">
    <form class="form-horizontal" id="fm-add" role="form">
        <div class="form-body">
            <div class="form-group">
                <label class="col-md-2 control-label">
                    分区编码
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="subareaCode" id="subareaCode" maxlength="50"
                           placeholder="请选择分区编码" style="width:450px">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    站点名称
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="name" maxlength="50"
                           placeholder="请输入站点名称（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    详细地址
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="address" maxlength="50" readonly
                           placeholder="请选择详细地址" style="float:left;width:80%">
                    <a href="javascript:window.open('${portalPath}/dynamic/common/map.jsp')"
                       style="float:right;line-height: 28px;">选择</a>
                    <span class="help-block"></span>
                    <input name="latitude" type="hidden"/>
                    <input name="longitude" type="hidden"/>
                    <span class="help-block"></span>
                </div>

            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    站点描述
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="depict" maxlength="200"
                           placeholder="请输入站点描述（建议字数在14个字以内，不超过200个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    备注
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="remark" maxlength="200"
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
    </form>


</script>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
