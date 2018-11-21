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
            <form class="form-horizontal" id="fm-edit" role="form">

            </form>
        </div>
        <!--具体界面元素结束-->
    </div>
</div>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>

</body>
<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 control-label">

                机构全称
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="departmentName" value="\${data.o.departmentName}"
                       placeholder="机构全称（建议字数在8个字以内，不超过20个字)">
                <div class="error-departmentName"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                机构简称
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="shortName" value="\${data.o.shortName}"
                       placeholder="机构简称（建议字数在5个字以内，不超过20个字)">
                <div class="error-shortName"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                LOGO
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="hidden" name="logo" value="\${data.o.logo}">
                <div style="padding: 10px;">建议图片大小为88*88</div>
                <img style="max-width:480px;cursor:pointer;" id="logo" data-toggle="modal" data-xsize="88"
                     data-ysize="88" data-cover="logo"
                     data-target="#img-uploader"
                     src="{@if data.o.logo}\${data.o.logo}{@else}${portalPath}/content/common/image/upload-default.jpg{@/if}">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">

                图片水印1
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="hidden" name="watermark1" value="\${data.o.watermark1}">
                <div style="padding: 10px;">建议图片大小为157*44</div>
                <img style="max-width:480px;cursor:pointer;" id="watermark1" data-toggle="modal" data-xsize="157"
                     data-ysize="44" data-cover="watermark1"
                     data-target="#img-uploader"
                     src="{@if data.o.watermark1}\${data.o.watermark1}{@else}${portalPath}/content/common/image/upload-default.jpg{@/if}">

            </div>
        </div>

        <div class="form-group">
            <label class="col-md-2 control-label">

                图片水印2
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="hidden" name="watermark2" value="\${data.o.watermark2}">
                <div style="padding: 10px;">建议图片大小为157*44</div>
                <img style="max-width:480px;cursor:pointer;" id="watermark2" data-toggle="modal" data-xsize="157"
                     data-ysize="44" data-cover="watermark2"
                     data-target="#img-uploader"
                     src="{@if data.o.watermark2}\${data.o.watermark2}{@else}${portalPath}/content/common/image/upload-default.jpg{@/if}">

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
</script>

<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/upload.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
