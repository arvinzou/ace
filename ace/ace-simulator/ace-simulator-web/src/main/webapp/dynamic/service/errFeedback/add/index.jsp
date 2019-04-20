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
    <form class="form-horizontal fm-page" id="fm-add" role="form">
        <div class="form-body">
            <div class="form-group">
                <label class="col-md-2 control-label">
                    设备编号
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="deviceCode" maxlength="50"
                           placeholder="请输入设备编号（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    故障类型
                </label>
                <div class="col-md-10">
                    <select class="form-control select-ctrl" name="errType">
                        {@each data['180'] as item, index}
                        <option value="\${item.CODE}" \${index==1?'selected=selected':''}> \${item.NAME}</option>
                        {@/each}
                    </select>
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    故障等级
                </label>
                <div class="col-md-10">
                    <select class="form-control select-ctrl" name="errLevel">
                        {@each data['181'] as item, index}
                        <option value="\${item.CODE}" \${index==1?'selected=selected':''}> \${item.NAME}</option>
                        {@/each}
                    </select>
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    故障时间
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="errDate" maxlength=""
                           placeholder="请输入故障时间（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    故障内容
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="errContent" maxlength="200"
                           placeholder="请输入故障内容（建议字数在14个字以内，不超过200个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    故障回路数
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="errLoopNum" maxlength="10"
                           placeholder="请输入故障回路数（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    数据源编号
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="dataSrcCode" maxlength="50"
                           placeholder="请输入数据源编号（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group hide">
                <label class="col-md-2 control-label">
                    接口返回内容
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="apiRst" maxlength="300"
                           placeholder="请输入接口返回内容（建议字数在14个字以内，不超过300个字)">
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

<style>
    .select-ctrl {
        max-width: 30%;
    }

    .fm-page {
        max-width: 80%;
    }
</style>
</html>
