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
                    定时任务序号
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="timerID" maxlength="10"
                           placeholder="请输入定时任务序号（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份1
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m1" maxlength="10"
                           placeholder="请输入月份1（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份2
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m2" maxlength="10"
                           placeholder="请输入月份2（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份3
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m3" maxlength="10"
                           placeholder="请输入月份3（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份4
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m4" maxlength="10"
                           placeholder="请输入月份4（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份5
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m5" maxlength="10"
                           placeholder="请输入月份5（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份6
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m6" maxlength="10"
                           placeholder="请输入月份6（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份7
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m7" maxlength="10"
                           placeholder="请输入月份7（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份8
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m8" maxlength="10"
                           placeholder="请输入月份8（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份9
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m9" maxlength="10"
                           placeholder="请输入月份9（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份10
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m10" maxlength="10"
                           placeholder="请输入月份10（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份11
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m11" maxlength="10"
                           placeholder="请输入月份11（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份12
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="m12" maxlength="10"
                           placeholder="请输入月份12（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    备注
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="remark" maxlength="100"
                           placeholder="请输入备注（建议字数在14个字以内，不超过100个字)">
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
<script src="js/act.js?v=${cfg.version}"></script>
</html>
