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
            <form class="form-horizontal " id="fm-edit" role="form">

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

                行政区划
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" hidden value="\${data.o.id}" name="id">
                <input type="text" class="form-control" name="district"
                       value="\${data.o.district}" maxlength="50"
                       placeholder="请输入行政区划（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                分类
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="linkType"
                       value="\${data.o.linkType}" maxlength="10"
                       placeholder="请输入分类（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                节点/站点编码
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="linkCode"
                       value="\${data.o.linkCode}" maxlength="50"
                       placeholder="请输入节点/站点编码（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                策略编码
                <span class="required" aria-required="true"> * </span>
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="code"
                       value="\${data.o.code}" maxlength="100"
                       placeholder="请输入策略编码（建议字数在14个字以内，不超过100个字)">
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
                <input type="text" class="form-control" name="decipt"
                       value="\${data.o.decipt}" maxlength="100"
                       placeholder="请输入策略描述（建议字数在14个字以内，不超过100个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                策略模式
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="mode"
                       value="\${data.o.mode}" maxlength="20"
                       placeholder="请输入策略模式（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                执行周期(数组)
            </label>
            <div class="col-md-10">
                <ul class="inputClick">
                    <li>
                        <input value="1" \${isChecked(data.o.weeks,1)} class="weeks" id="w1" type="checkbox" name="weeks">
                        <label for="w1">星期一</label>
                    </li>
                    <li>
                        <input value="2" \${isChecked(data.o.weeks,2)} class="weeks" id="w2" type="checkbox" name="weeks">
                        <label for="w2">星期二</label>
                    </li>
                    <li>
                        <input value="3" \${isChecked(data.o.weeks,3)} class="weeks" id="w3" type="checkbox" name="weeks">
                        <label for="w3">星期三</label>
                    </li>
                    <li>
                        <input value="4" \${isChecked(data.o.weeks,4)} class="weeks" id="w4" type="checkbox" name="weeks">
                        <label for="w4">星期四</label>
                    </li>
                    <li>
                        <input value="5" \${isChecked(data.o.weeks,5)} class="weeks" id="w5" type="checkbox" name="weeks">
                        <label for="w5">星期五</label>
                    </li>
                    <li>
                        <input value="6" \${isChecked(data.o.weeks,6)} class="weeks" id="w6" type="checkbox" name="weeks">
                        <label for="w6">星期六</label>
                    </li>
                    <li>
                        <input value="7" \${isChecked(data.o.weeks,7)} class="weeks" id="w7" type="checkbox" name="weeks">
                        <label for="w7">星期日</label>
                    </li>
                </ul>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                开始执行时间
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="startDate"
                       value="\${data.o.startDate}" maxlength="20"
                       placeholder="请输入开始执行时间（建议字数在14个字以内，不超过个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                结束执行时间
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="endDate"
                       value="\${data.o.endDate}" maxlength="20"
                       placeholder="请输入结束执行时间（建议字数在14个字以内，不超过个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">

                备注
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="remark"
                       value="\${data.o.remark}" maxlength="100"
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

<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>


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
