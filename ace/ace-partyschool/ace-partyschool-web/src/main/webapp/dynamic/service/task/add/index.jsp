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
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" type="text/css"/>
    <link rel="stylesheet"
          href="${portalPath}/content/common/assets/global/plugins/select2/css/select2-bootstrap.min.css">
    <link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/select2/css/select2.css">
</head>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet light">

    <div class="portlet-body">
        <div class="form-panel" id="fm-add-panel">
            <!--具体界面元素开始-->
            <form class="form-horizontal" id="fm-add" role="form">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            测试名称
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" name="name" maxlength="50"
                                   placeholder="请输入任务名称（建议字数在14个字以内，不超过50个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            测试介绍
                        </label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" name="introduce" maxlength="200"
                                   placeholder="请输入测试介绍（建议字数在14个字以内，不超过200个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            测评方案
                        </label>
                        <div class="col-md-6">
                            <select style="width: 100%;height: 34px"
                                    class="js-example-basic-single js-example-basic-single1" name="testId">
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            开始时间
                        </label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" name="startTime"
                                   placeholder="请输入开始时间">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            结束时间
                        </label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" name="endTime"
                                   placeholder="请输入结束时间">
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

        </div>

    </div>
</div>



<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/select2/js/select2.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
