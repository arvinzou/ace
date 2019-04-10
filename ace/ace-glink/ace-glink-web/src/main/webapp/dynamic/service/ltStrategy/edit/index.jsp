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

                        策略编号
                                                    <span class="required" aria-required="true"> * </span>
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="code"
                               value="\{data.o.code}" maxlength="50"
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
                               value="\{data.o.name}" maxlength="50"
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
                               value="\{data.o.depict}" maxlength="200"
                               placeholder="请输入策略描述（建议字数在14个字以内，不超过200个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        策略状态
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="state"
                               value="\{data.o.state}" maxlength="1"
                               placeholder="请输入策略状态（建议字数在14个字以内，不超过1个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        行政区划
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="district"
                               value="\{data.o.district}" maxlength="50"
                               placeholder="请输入行政区划（建议字数在14个字以内，不超过50个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        所属站点
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="stationCode"
                               value="\{data.o.stationCode}" maxlength="50"
                               placeholder="请输入所属站点（建议字数在14个字以内，不超过50个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        模式
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="pattern"
                               value="\{data.o.pattern}" maxlength="1"
                               placeholder="请输入模式（建议字数在14个字以内，不超过1个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        按周执行（0否1是）[日程模式]
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="isWeek"
                               value="\{data.o.isWeek}" maxlength="10"
                               placeholder="请输入按周执行（0否1是）[日程模式]（建议字数在14个字以内，不超过10个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        星期数组[日程模式]
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="weeks"
                               value="\{data.o.weeks}" maxlength="50"
                               placeholder="请输入星期数组[日程模式]（建议字数在14个字以内，不超过50个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        按月执行(0否1整月)[日程模式]
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="isMonth"
                               value="\{data.o.isMonth}" maxlength="10"
                               placeholder="请输入按月执行(0否1整月)[日程模式]（建议字数在14个字以内，不超过10个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        月份数组(pattern为1)[日程模式]
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="months"
                               value="\{data.o.months}" maxlength="50"
                               placeholder="请输入月份数组(pattern为1)[日程模式]（建议字数在14个字以内，不超过50个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        开始日期[假日模式]
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="startDate"
                               value="\{data.o.startDate}" maxlength=""
                               placeholder="请输入开始日期[假日模式]（建议字数在14个字以内，不超过个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        技术日期[假日模式]
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="stopDate"
                               value="\{data.o.stopDate}" maxlength=""
                               placeholder="请输入技术日期[假日模式]（建议字数在14个字以内，不超过个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        具体的特殊日期[事件模式]
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="specialDate"
                               value="\{data.o.specialDate}" maxlength=""
                               placeholder="请输入具体的特殊日期[事件模式]（建议字数在14个字以内，不超过个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        策略的开始时间
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="startTime"
                               value="\{data.o.startTime}" maxlength=""
                               placeholder="请输入策略的开始时间（建议字数在14个字以内，不超过个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        策略的结束时间
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="stopTime"
                               value="\{data.o.stopTime}" maxlength=""
                               placeholder="请输入策略的结束时间（建议字数在14个字以内，不超过个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        分区已编辑完毕的策略编号（或名称）
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="strategy"
                               value="\{data.o.strategy}" maxlength="50"
                               placeholder="请输入分区已编辑完毕的策略编号（或名称）（建议字数在14个字以内，不超过50个字)">
                        <span class="help-block"></span>
                    </div>
                </div>
                            <div class="form-group">
                    <label class="col-md-2 control-label">

                        备注
                                            </label>
                    <div class="col-md-10">
                        <input type="text" class="form-control" name="remark"
                               value="\{data.o.remark}" maxlength="200"
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
