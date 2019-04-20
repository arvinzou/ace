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
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 control-label">

                定时任务序号
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="timerID"
                       value="\{data.o.timerID}" maxlength="10"
                       placeholder="请输入定时任务序号（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期1
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d1"
                       value="\{data.o.d1}" maxlength="10"
                       placeholder="请输入日期1（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期2
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d2"
                       value="\{data.o.d2}" maxlength="10"
                       placeholder="请输入日期2（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期3
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d3"
                       value="\{data.o.d3}" maxlength="10"
                       placeholder="请输入日期3（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期4
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d4"
                       value="\{data.o.d4}" maxlength="10"
                       placeholder="请输入日期4（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期5
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d5"
                       value="\{data.o.d5}" maxlength="10"
                       placeholder="请输入日期5（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期6
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d6"
                       value="\{data.o.d6}" maxlength="10"
                       placeholder="请输入日期6（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期7
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d7"
                       value="\{data.o.d7}" maxlength="10"
                       placeholder="请输入日期7（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期8
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d8"
                       value="\{data.o.d8}" maxlength="10"
                       placeholder="请输入日期8（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期9
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d9"
                       value="\{data.o.d9}" maxlength="10"
                       placeholder="请输入日期9（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期10
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d10"
                       value="\{data.o.d10}" maxlength="10"
                       placeholder="请输入日期10（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期11
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d11"
                       value="\{data.o.d11}" maxlength="10"
                       placeholder="请输入日期11（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期12
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d12"
                       value="\{data.o.d12}" maxlength="10"
                       placeholder="请输入日期12（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期13
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d13"
                       value="\{data.o.d13}" maxlength="10"
                       placeholder="请输入日期13（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期14
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d14"
                       value="\{data.o.d14}" maxlength="10"
                       placeholder="请输入日期14（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期15
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d15"
                       value="\{data.o.d15}" maxlength="10"
                       placeholder="请输入日期15（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期16
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d16"
                       value="\{data.o.d16}" maxlength="10"
                       placeholder="请输入日期16（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期17
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d17"
                       value="\{data.o.d17}" maxlength="10"
                       placeholder="请输入日期17（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期18
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d18"
                       value="\{data.o.d18}" maxlength="10"
                       placeholder="请输入日期18（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期19
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d19"
                       value="\{data.o.d19}" maxlength="10"
                       placeholder="请输入日期19（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期20
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d20"
                       value="\{data.o.d20}" maxlength="10"
                       placeholder="请输入日期20（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期21
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d21"
                       value="\{data.o.d21}" maxlength="10"
                       placeholder="请输入日期21（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期22
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d22"
                       value="\{data.o.d22}" maxlength="10"
                       placeholder="请输入日期22（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期23
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d23"
                       value="\{data.o.d23}" maxlength="10"
                       placeholder="请输入日期23（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期24
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d24"
                       value="\{data.o.d24}" maxlength="10"
                       placeholder="请输入日期24（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期25
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d25"
                       value="\{data.o.d25}" maxlength="10"
                       placeholder="请输入日期25（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期26
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d26"
                       value="\{data.o.d26}" maxlength="10"
                       placeholder="请输入日期26（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期27
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d27"
                       value="\{data.o.d27}" maxlength="10"
                       placeholder="请输入日期27（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期28
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d28"
                       value="\{data.o.d28}" maxlength="10"
                       placeholder="请输入日期28（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期29
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d29"
                       value="\{data.o.d29}" maxlength="10"
                       placeholder="请输入日期29（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期30
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d30"
                       value="\{data.o.d30}" maxlength="10"
                       placeholder="请输入日期30（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                日期31
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="d31"
                       value="\{data.o.d31}" maxlength="10"
                       placeholder="请输入日期31（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                备注
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="remark"
                       value="\{data.o.remark}" maxlength="100"
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
