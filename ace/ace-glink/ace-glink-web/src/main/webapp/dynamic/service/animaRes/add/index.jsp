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
                            节目编号
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="code" maxlength="20"
                                   placeholder="请输入节目编号（建议字数在14个字以内，不超过20个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            节目名称
                                                            <span class="required" aria-required="true"> * </span>
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="name" maxlength="50"
                                   placeholder="请输入节目名称（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            节目类型
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="type" maxlength="2"
                                   placeholder="请输入节目类型（建议字数在14个字以内，不超过2个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            播放时长
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="duration" maxlength="10"
                                   placeholder="请输入播放时长（建议字数在14个字以内，不超过10个字)" onblur="checkNumber(this);">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            播放地址
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="playUrl" maxlength="300"
                                   placeholder="请输入播放地址（建议字数在14个字以内，不超过300个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            预览地址
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="prePlayUrl" maxlength="300"
                                   placeholder="请输入预览地址（建议字数在14个字以内，不超过300个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            效果图地址
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="preImgUrl" maxlength="300"
                                   placeholder="请输入效果图地址（建议字数在14个字以内，不超过300个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            分辨率-宽
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="rsoWidth" maxlength="19"
                                   placeholder="请输入分辨率-宽（建议字数在14个字以内，不超过19个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            分辨率-高
                                                    </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="rsoHeight" maxlength="19"
                                   placeholder="请输入分辨率-高（建议字数在14个字以内，不超过19个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            备注
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
