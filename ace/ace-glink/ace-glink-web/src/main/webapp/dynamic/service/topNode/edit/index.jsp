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
        <div class="form-panel fm-page">
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
        <input type="text" class="form-control hidden" name="id" value="\${data.o.id}">
        <input type="text" class="form-control hidden" name="status" value="\${data.o.status}">
        <input type="text" class="form-control hidden" name="code" value="\${data.o.code}">
         <div class="form-group">
             <label class="col-md-2 control-label">
                 节点编号
                 <span class="required" aria-required="true"> * </span>
             </label>
             <div class="col-md-10">
                 <input type="text" class="form-control" name="code"
                        value="\${data.o.code}" maxlength="50"
                        placeholder="请输入节点编号（建议字数在14个字以内，不超过50个字)">
                 <span class="help-block"></span>
             </div>
         </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                节点名称
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name"
                       value="\${data.o.name}" maxlength="50"
                       placeholder="请输入节点名称（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                节点描述
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="depict"
                       value="\${data.o.depict}" maxlength="200"
                       placeholder="请输入节点描述（建议字数在14个字以内，不超过200个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                详细地址
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="address"
                       value="\${data.o.address}" maxlength="200"
                       placeholder="请输入详细地址（建议字数在14个字以内，不超过200个字)" style="float:left;width:92%">
                <a href="javascript:window.open('${portalPath}/dynamic/common/map.jsp')"
                   style="float:right;line-height: 28px;">选择</a>
                <span class="help-block"></span>
                <input name="latitude" value="\${data.o.latitude}" type="hidden"/>
                <input name="longitude" value="\${data.o.longitude}" type="hidden"/>
                <input name="areaCode" type="hidden"/>
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                IPV4地址
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="ipv4"
                       value="\${data.o.ipv4}" maxlength="20"
                       placeholder="请输入IPV4地址（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                IPV6地址
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="ipv6"
                       value="\${data.o.ipv6}" maxlength="20"
                       placeholder="请输入IPV6地址（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                端口号
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="port"
                       value="\${data.o.port}" maxlength="10"
                       placeholder="请输入端口号（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                分辨率-宽
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="resolutionWidth"
                       value="\${data.o.resolutionWidth}" maxlength="19"
                       placeholder="请输入分辨率-宽（建议字数在14个字以内，不超过19个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                分辨率-高
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="resolutionHeight"
                       value="\${data.o.resolutionHeight}" maxlength="19"
                       placeholder="请输入分辨率-高（建议字数在14个字以内，不超过19个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                mac地址
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="macAddr"
                       value="\${data.o.macAddr}" maxlength="20"
                       placeholder="请输入mac地址（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                控制器数量
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="ctrlNum"
                       value="\${data.o.ctrlNum}" maxlength="10"
                       placeholder="请输入控制器数量（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                建筑物编号
            </label>
            <div class="col-md-10">
                <input STYLE="width: auto" type="text" class="form-control" name="buildingCode"
                       value="\${data.o.buildingCode}" maxlength="50"
                       placeholder="请输入建筑物编号（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div> <div class="form-group">
            <label class="col-md-2 control-label">
                站点编号
            </label>
            <div class="col-md-10">
                <input STYLE="width: auto" type="text" class="form-control" name="stationCode"
                       value="\${data.o.stationCode}" maxlength="50"
                       placeholder="请输入站点编号（建议字数在14个字以内，不超过50个字)">
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
<script src="js/act.js?v=${cfg.version}"></script>

<%--easyui--%>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
</html>
