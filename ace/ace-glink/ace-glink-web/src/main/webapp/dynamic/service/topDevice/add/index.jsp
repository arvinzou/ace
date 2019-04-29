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
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" type="text/css"/>
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
            <%--<div class="form-group">
                <label class="col-md-2 control-label">
                    设备编号
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="code" maxlength="50"
                           placeholder="请输入设备编号（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>--%>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    设备名称
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="name" maxlength="50"
                           placeholder="请输入设备名称（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    设备类型
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    <select name="type" id="type" class="form-control">
                        {@each data['178'] as item, index}
                        {@if item.CODE!=''}
                        <option value="\${item.CODE}">\${item.NAME}</option>
                        {@/if}
                        {@/each}
                    </select>
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    所属节点
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="nodeCode" maxlength="50" style="width: 500px;"
                           placeholder="请输入所属节点（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    上线时间
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="onlineDate" id="onlineDate" maxlength="50"
                           placeholder="请选择上线时间">
                    <span class="help-block"></span>
                </div>

            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    下线时间
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="offlineDate" maxlength="50"
                           placeholder="请选择下线时间" id="offlineDate" onchange="validateOffTime(this.value)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    生产厂商
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="prcBisFirm" maxlength="50"
                           placeholder="请输入生产厂商（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    生产日期
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="prcDate" maxlength="50"
                           placeholder="请选择生产日期" id="prcDate">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    理论使用寿命
                </label>
                <div class="col-md-8">
                    <input type="text" class="form-control" name="workingLife" maxlength="10" value="0"
                           placeholder="请输入理论使用寿命" onkeyup="this.value=this.value.replace(/\D/g,'')">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    备注
                </label>
                <div class="col-md-8">
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
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
        type="text/javascript"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<link rel="stylesheet" type="text/css"
<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
