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
            <form class="form-horizontal" id="fm-add" role="form">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            父ID
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="pid" maxlength="50"
                                   placeholder="请输入父ID（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            姓名
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="name" maxlength="50"
                                   placeholder="请输入姓名（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            手机号
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="mobile" maxlength="20"
                                   placeholder="请输入手机号（建议字数在14个字以内，不超过20个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            身份证
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="idCard" maxlength="18"
                                   placeholder="请输入身份证（建议字数在14个字以内，不超过18个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            政治面貌
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="political" maxlength="20"
                                   placeholder="请输入政治面貌（建议字数在14个字以内，不超过20个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            单位
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="workUnitName" maxlength="50"
                                   placeholder="请输入单位（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            职务
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="postName" maxlength="50"
                                   placeholder="请输入职务（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            班级
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="classId" maxlength="20"
                                   placeholder="请输入班级（建议字数在14个字以内，不超过20个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            备注
                            ﻿
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
                            <button class="btn    green" type="submit" style="width:30%">保存</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>


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
