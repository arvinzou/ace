<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%
session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list = [${cfg.default_page_list}];
</script>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>基本信息</title>
    <script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/layout.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/themes/default.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/custom.min.css?v=${cfg.version}"/>

    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
</head>




<body>
<div class="portlet light">

    <div class="portlet-body" id="courseSource">
        <div class="form-panel">
            <!--具体界面元素开始-->
            <form class="form-horizontal" id="fm-add" role="form">
                <input type="hidden" name="noticeId" value="">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            标题
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="title" maxlength="100" placeholder="请输入标题（建议字数在28个字以内，不超过50个字)">
                            <div class="error-title"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            内容
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-8">
                            <textarea rows="10" cols="100" class="form-control" name="content" maxlength="500"></textarea>
                            <div class="error-content"></div>


                        </div>
                    </div>



                    <div class="form-group">

                    <label class="col-md-2 control-label">

                        图片
                    </label>

                    <div class="col-md-8">
                        <div class="image-boxs my-gallery">


                        </div>

                    </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label"></label>
                        <div class="col-md-8">
                            <img style="max-width:480px;cursor:pointer;" id="imageSrc" data-toggle="modal" data-xsize="480" data-ysize="270" data-cover="imageSrc"
                                 data-target="#img-uploader" data-multi="true" src="${portalPath}/content/common/image/add.png">
                        </div>


                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            企业/单位
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="companyName" maxlength="50" placeholder="请输入企业/单位名称">
                            <div class="error-companyName"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            联系人
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="submitName" maxlength="20" placeholder="请输入联系人姓名">
                            <div class="error-submitName"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            联系电话
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="tel" maxlength="20" placeholder="请输入联系电话">
                            <div class="error-tel"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">

                            验证码
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-8">
                            <img id="imageF"
                                 src="${portalPath}/captcha/image.do" style="max-height:40px;padding-bottom:10px"/>
                            <input type="text" class="form-control" name="captcha" maxlength="4" placeholder="请输入验证码">

                        </div>
                    </div>



                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-7">
                            <button class="btn  green" type="submit" style="width:30%">保存</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!--具体界面元素结束-->
    </div>
</div>


<script id="tpl-view-page" type="text/template">

</script>

<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/js.cookie.min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/init-rem.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/localization/messages_zh.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/upload.js"></script>
<script src="js/act.js"></script>
</body>
</html>