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

                        区域节点编号
                                                    <span class="required" aria-required="true"> * </span>
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="areaNodeID"
                               value="\{data.o.areaNodeID}" maxlength="50"
                               placeholder="请输入区域节点编号（建议字数在14个字以内，不超过50个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        报警分类
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="alarmType"
                               value="\{data.o.alarmType}" maxlength="50"
                               placeholder="请输入报警分类（建议字数在14个字以内，不超过50个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        报警内容描述
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="alarmContent"
                               value="\{data.o.alarmContent}" maxlength="200"
                               placeholder="请输入报警内容描述（建议字数在14个字以内，不超过200个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        报警时间
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="alarmDateTime"
                               value="\{data.o.alarmDateTime}" maxlength="50"
                               placeholder="请输入报警时间（建议字数在14个字以内，不超过50个字)">
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
