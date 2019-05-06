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
        <input type="text" class="form-control hidden" name="id" value="\${data.o.id}">
        <div class="form-group">
            <label class="col-md-2 control-label">
                策略编号
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="code"
                       value="\${data.o.code}" maxlength="50"
                       placeholder="请输入策略编号（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                策略名称
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="name"
                       value="\${data.o.name}" maxlength="50"
                       placeholder="请输入策略名称（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                策略描述
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="depict"
                       value="\${data.o.depict}" maxlength="200"
                       placeholder="请输入策略描述（建议字数在14个字以内，不超过200个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <%--<div class="form-group">--%>
            <%--<label class="col-md-2 control-label">--%>
                <%--策略状态--%>
            <%--</label>--%>
            <%--<div class="col-md-10">--%>
                <%--<input type="text" class="form-control" name="state"--%>
                       <%--value="\${data.o.state}" maxlength="1"--%>
                       <%--placeholder="请输入策略状态（建议字数在14个字以内，不超过1个字)">--%>
                <%--<span class="help-block"></span>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="form-group">
            <label class="col-md-2 control-label">
                分区编号
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="areaCode"
                       value="\${data.o.areaCode}" maxlength="50"
                       placeholder="请输入分区编号（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <%--<div class="form-group">--%>
            <%--<label class="col-md-2 control-label">--%>
                <%--所属站点--%>
            <%--</label>--%>
            <%--<div class="col-md-10">--%>
                <%--<input type="text" class="form-control" name="stationCode"--%>
                       <%--value="\${data.o.stationCode}" maxlength="50"--%>
                       <%--placeholder="请输入所属站点（建议字数在14个字以内，不超过50个字)">--%>
                <%--<span class="help-block"></span>--%>
            <%--</div>--%>
        <%--</div>--%>
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
