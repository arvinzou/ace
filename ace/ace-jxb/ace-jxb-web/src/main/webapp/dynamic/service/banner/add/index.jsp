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

                            标题
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="title" maxlength="50"
                                   placeholder="请输入标题（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            图片
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="hidden" name="imgUrl">
                            <div style="padding: 10px;">建议图片大小为480*270</div>
                            <img style="max-width:480px;cursor:pointer;" id="imgUrl" data-toggle="modal" data-xsize="480" data-ysize="270" data-cover="imgUrl"
                                 data-target="#img-uploader" src="${portalPath}/content/common/image/upload-default.jpg">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            资源地址
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="url" maxlength="200"
                                   placeholder="请输入资源地址（建议字数在14个字以内，不超过200个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            上线
                            <span class="required" aria-required="true"> * </span>
                            ﻿
                        </label>
                        <div class="col-md-10 radio-group-container">
                            <label>
                                <input type="radio" name="status" value="0"><span style="padding:10px">否</span>

                            </label>
                            <label>
                                <input type="radio" name="status" value="1" checked><span style="padding:10px">是</span>

                            </label>
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
<script type="text/javascript" src="${portalPath}/content/common/js/upload.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
