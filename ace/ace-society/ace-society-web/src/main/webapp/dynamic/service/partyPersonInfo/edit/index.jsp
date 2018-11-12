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
</head>

<body>
<%--==============common jsp-prefix==============--%>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-prefix==============--%>


                                        <!-- BEGIN SAMPLE TABLE PORTLET-->
                                        <div class="portlet light">
                                            <div class="portlet-title">
                                                <div class="caption">
                                                    编辑个人信息
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
                                        <!-- END SAMPLE TABLE PORTLET-->

<%--=============common jsp-suffix===============--%>
<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
<%--==============common jsp-suffix==============--%>
</body>


<script id="tpl-fm" type="text/template">
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                姓名
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="realName" value="\{data.o.realName}" maxlength="50"
                       placeholder="请输入姓名（建议字数在14个字以内，不超过50个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                <span class="label-red">*</span>
                手机号
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="mobilePhone" value="\{data.o.mobilePhone}" maxlength="20"
                       placeholder="请输入手机号（建议字数在14个字以内，不超过20个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                政治面貌
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="politicalStatus" value="\{data.o.politicalStatus}"
                       maxlength="2" placeholder="请输入政治面貌（建议字数在14个字以内，不超过2个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                累计获取积分
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="accPoints" value="\{data.o.accPoints}" maxlength="10"
                       placeholder="请输入累计获取积分（建议字数在14个字以内，不超过10个字)">
                <span class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">
                可用积分
            </label>
            <div class="col-md-10">
                <input type="text" class="form-control" name="validPoints" value="\{data.o.validPoints}" maxlength="10"
                       placeholder="请输入可用积分（建议字数在14个字以内，不超过10个字)">
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
<script src="js/act.js?v=${cfg.version}"></script>
</html>
