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
        <div class="form-group hide">
            <label class="col-md-2 control-label">
                <input type="hidden" class="form-control" name="id" value="\${data.o.id}"/>
                <input type="hidden" class="form-control" name="status" value="\${data.o.status}"/>
                <input type="hidden" class="form-control" name="createDate" value="\${data.o.createDate}"/>
                <input type="hidden" class="form-control" name="createUserId" value="\${data.o.createUserId}"/>
                <input type="hidden" class="form-control" name="createUserName" value="\${data.o.createUserName}"/>
                设备编号
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="code"
                       value="\${data.o.code}" maxlength="50"
                       placeholder="请输入设备编号（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                设备名称
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="name"
                       value="\${data.o.name}" maxlength="50"
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
                    {@each data.dict['178'] as item, index}
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

                所属节点
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="nodeCode"
                       value="\${data.o.nodeCode}" maxlength="50" style="width: 450px;"
                       placeholder="请选择所属节点">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                上线时间
            </label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="onlineDate" id="onlineDate"
                       value="\${data.o.onlineDate}" maxlength="50"
                       placeholder="请输入上线时间">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                下线时间
            </label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="offlineDate" id="offlineDate"
                       value="\${data.o.offlineDate}" maxlength="50"
                       placeholder="请输入下线时间">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                生产厂商
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="prcBisFirm"
                       value="\${data.o.prcBisFirm}" maxlength="50"
                       placeholder="请输入生产厂商">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                生产日期
            </label>
            <div class="col-md-5">
                <input type="text" class="form-control" name="prcDate" id="prcDate"
                       value="\${data.o.prcDate}" maxlength="50"
                       placeholder="请输入生产日期">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                理论使用寿命
            </label>
            <div class="col-md-8">
                <input type="text" class="form-control" name="workingLife"
                       value="\${data.o.workingLife}" maxlength="10"
                       placeholder="请输入理论使用寿命" onkeyup="this.value=this.value.replace(/\D/g,'')">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                备注
            </label>
            <div class="col-md-8">
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
