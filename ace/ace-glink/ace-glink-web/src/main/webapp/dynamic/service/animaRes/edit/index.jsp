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
            <form class="form-horizontal" id="fm-edit" role="form">

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
    <input type="text" class="form-control hidden" name="id" value="\${data.o.id}">
    <input type="text" class="form-control hidden" name="status" value="\${data.o.status}">
    <div class="form-body">
        <div class="form-group hide">
            <label class="col-md-2 control-label">
                节目编号
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="code"
                       value="\${data.o.code}" maxlength="20"
                       placeholder="请输入节目编号（不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                节目名称
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name"
                       value="\${data.o.name}" maxlength="50"
                       placeholder="请输入节目名称（不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                节目类型
            </label>
            <div class="col-md-10">
                <select name="type" id="type" class="form-control">
                    {@each data.dict['179'] as item, index}
                    {@if item.CODE!=''}
                    {@if item.CODE == data.o.type}
                    <option value="\${item.CODE}" selected=selected>\${item.NAME}</option>
                    {@else}
                    <option value="\${item.CODE}">\${item.NAME}</option>
                    {@/if}
                    {@/if}
                    {@/each}
                </select>

            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                播放时长
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="duration"
                       value="\${data.o.duration}" maxlength="10"
                       placeholder="请输入播放时长（单位：s)" onblur="checkNumber(this);">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                播放地址
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="playUrl" id="playUrl"
                       value="\${data.o.playUrl}" maxlength="300"
                       placeholder="请输入播放地址（建议字数在14个字以内，不超过300个字)"  style="width: 80%;float: left;margin-right: 10px;">
                <a href="javascript:void(0);" id="uploadPlayUrl" style="font-size: 14px !important;"
                   class="btn green commonCourse" style="float: left;">上传资源</a>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                预览地址
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="prePlayUrl" id="prePlayUrl"
                       value="\${data.o.prePlayUrl}" maxlength="300"
                       placeholder="请输入预览地址（建议字数在14个字以内，不超过300个字)" style="width: 80%;float: left;margin-right: 10px;">
                <a href="javascript:void(0);" id="uploadPrePlayUrl" style="font-size: 14px !important;"
                   class="btn green commonCourse" style="float: left;">上传资源</a>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                效果图地址
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="preImgUrl" id="preImgUrl"
                       value="\${data.o.preImgUrl}" maxlength="300"
                       placeholder="请输入效果图地址（建议字数在14个字以内，不超过300个字)" style="width: 80%;float: left;margin-right: 10px;">
                <a href="javascript:void(0);" id="uploadPreImgUrl" style="font-size: 14px !important;"
                   class="btn green commonCourse" style="float: left;">上传资源</a>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                分辨率-宽
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="rsoWidth"
                       value="\${data.o.rsoWidth}" maxlength="19"
                       placeholder="请输入分辨率-宽（建议字数在14个字以内，不超过19个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                分辨率-高
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="rsoHeight"
                       value="\${data.o.rsoHeight}" maxlength="19"
                       placeholder="请输入分辨率-高（建议字数在14个字以内，不超过19个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
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

<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/js/cropUpload.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
