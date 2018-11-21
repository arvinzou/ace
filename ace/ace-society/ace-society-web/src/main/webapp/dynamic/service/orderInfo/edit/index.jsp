<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}" name="${cfg.sys_name}"/>
    <%--common css--%>
    <jsp:include page="/dynamic/common/header.jsp"/>
    <link rel="stylesheet" href="css/style.css">
    <%--custom css--%>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
    <link rel="stylesheet"
          href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
</head>

<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>

<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet light">
    <div class="portlet-title hide">
        <div class="caption">
            编辑订单管理
        </div>
        <div class="actions">

        </div>
    </div>
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

<%--==============script piece==============--%>
<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 control-label">
                订单编号
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="orderNo" value="\{data.o.orderNo}" maxlength="50"
                       placeholder="请输入订单编号（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                客户主键
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="userId" value="\{data.o.userId}" maxlength="50"
                       placeholder="请输入客户主键（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                付款方式
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="payType" value="\{data.o.payType}" maxlength="2"
                       placeholder="请输入付款方式（建议字数在14个字以内，不超过2个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                付款金额
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="payAmount" value="\{data.o.payAmount}" maxlength="10"
                       placeholder="请输入付款金额（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                付款时间
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="payDate" value="\{data.o.payDate}" maxlength=""
                       placeholder="请输入付款时间（建议字数在14个字以内，不超过个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                收货类型1-自取2-配送
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="receiveType" value="\{data.o.receiveType}" maxlength="2"
                       placeholder="请输入收货类型1-自取2-配送（建议字数在14个字以内，不超过2个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                收货人姓名
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="receiveName" value="\{data.o.receiveName}" maxlength="50"
                       placeholder="请输入收货人姓名（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                收货人电话
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="receivePhone" value="\{data.o.receivePhone}"
                       maxlength="20" placeholder="请输入收货人电话（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                收货地址-国家
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="country" value="\{data.o.country}" maxlength="20"
                       placeholder="请输入收货地址-国家（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                收货地址-省
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="province" value="\{data.o.province}" maxlength="20"
                       placeholder="请输入收货地址-省（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                收货地址-市
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="city" value="\{data.o.city}" maxlength="20"
                       placeholder="请输入收货地址-市（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                收货地址-区/县
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="district" value="\{data.o.district}" maxlength="20"
                       placeholder="请输入收货地址-区/县（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                收货地址-详细地址
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="address" value="\{data.o.address}" maxlength="100"
                       placeholder="请输入收货地址-详细地址（建议字数在14个字以内，不超过100个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                订单状态
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="orderState" value="\{data.o.orderState}" maxlength="2"
                       placeholder="请输入订单状态（建议字数在14个字以内，不超过2个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                备注
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="remark" value="\{data.o.remark}" maxlength="200"
                       placeholder="请输入备注（建议字数在14个字以内，不超过200个字)">
                <span class="help-block"></span>
            </div>
        </div>
    </div>
    <div class="form-actions">
        <div class="row">
            <div class="col-md-offset-3 col-md-7">
                <button class="btn  btn-lg  green" type="submit" style="width:30%">保存</button>
            </div>
        </div>
    </div>
</script>

<%--==============common footer==============--%>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/common/js/jqPaginator.js?v=${cfg.version}"></script>
<%--custom js--%>
<script type="text/javascript"
        src="/portal/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/common/js/cropUpload.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>

<script src="js/act.js?v=${cfg.version}"></script>
</html>
