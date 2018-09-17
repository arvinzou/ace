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
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
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
            创建方案提议
        </div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body" id="courseSource">
        <div class="form-panel">
            <!--具体界面元素开始-->
            <form class="form-horizontal" id="fm-add" role="form">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            <span class="label-red">*</span>议题标题
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="title" maxlength="50"
                                   placeholder="请输入议题标题（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            <span class="label-red">*</span>议题描述
                        </label>
                        <div class="col-md-10">
                            <textarea name="content" id="introduce" class="introduction"
                                      placeholder="请输入议题描述"></textarea>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            <span class="label-red">*</span>奖励积分
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="rewardPoints" maxlength="10"
                                   placeholder="请输入奖励积分">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">备注
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="remark" maxlength="200"
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
<!--公共部分开始-->
<jsp:include page="/dynamic/common/footer.jsp"/>
<!--公共部分结束-->
<!--私有部分开始-->
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/module.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/hotkeys.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/uploader.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/simditor/scripts/simditor.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>

<!--私有部分结束-->
</html>
