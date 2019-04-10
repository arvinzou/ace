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
    <form class="form-horizontal" id="fm-add" role="form">
        <div class="form-body">
            <div class="form-group">
                <label class="col-md-2 control-label">
                    策略编号
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="code" maxlength="50"
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
                    <input type="text" class="form-control" name="name" maxlength="50"
                           placeholder="请输入策略名称（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    策略描述
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="depict" maxlength="200"
                           placeholder="请输入策略描述（建议字数在14个字以内，不超过200个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    策略状态
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="state" maxlength="1"
                           placeholder="请输入策略状态（建议字数在14个字以内，不超过1个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    行政区划
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="district" maxlength="50"
                           placeholder="请输入行政区划（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    所属站点
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="stationCode" maxlength="50"
                           placeholder="请输入所属站点（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    模式
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="pattern" maxlength="1"
                           placeholder="请输入模式（建议字数在14个字以内，不超过1个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    按周执行（0否1是）[日程模式]
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="isWeek" maxlength="10"
                           placeholder="请输入按周执行（0否1是）[日程模式]（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    星期数组[日程模式]
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="weeks" maxlength="50"
                           placeholder="请输入星期数组[日程模式]（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    按月执行(0否1整月)[日程模式]
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="isMonth" maxlength="10"
                           placeholder="请输入按月执行(0否1整月)[日程模式]（建议字数在14个字以内，不超过10个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    月份数组(pattern为1)[日程模式]
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="months" maxlength="50"
                           placeholder="请输入月份数组(pattern为1)[日程模式]（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    开始日期[假日模式]
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="startDate" maxlength="20"
                           placeholder="请输入开始日期[假日模式]（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    技术日期[假日模式]
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="stopDate" maxlength="20"
                           placeholder="请输入技术日期[假日模式]（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    具体的特殊日期[事件模式]
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="specialDate" maxlength="20"
                           placeholder="请输入具体的特殊日期[事件模式]（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    策略的开始时间
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="startTime" maxlength="21"
                           placeholder="请输入策略的开始时间（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    策略的结束时间
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="stopTime" maxlength="21"
                           placeholder="请输入策略的结束时间（建议字数在14个字以内，不超过个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    分区已编辑完毕的策略编号（或名称）
                </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="strategy" maxlength="50"
                           placeholder="请输入分区已编辑完毕的策略编号（或名称）（建议字数在14个字以内，不超过50个字)">
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
