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
</head>

<body>

<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>


<!-- BEGIN SAMPLE TABLE PORTLET-->
<div class="portlet light">

    <div class="portlet-body" id="courseSource">
        <div class="form-panel">
            <!--具体界面元素开始-->
            <form class="form-horizontal" id="fm-add" role="form">
                <div class="form-body">
                    <h4>评测内容</h4>
                    <div class="form-group">
                        <div class="row">
                            <label class="col-md-2 control-label">
                                名称
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="name" maxlength="30"
                                       placeholder="请输入名称（建议字数在20个字以内,不要超过30个字)">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                        <label class="col-md-2 control-label">
                            介绍
                        </label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" name="introduce"
                                   maxlength="2147483647"
                                   placeholder="请输入介绍（建议字数在100个字以内，不超过200个字)">
                            <span class="help-block"></span>
                        </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="row">
                            <label class="col-md-2 control-label">
                                超时设定
                                <span class="required" aria-required="true"> * </span>
                            </label>
                            <div class="col-md-6">
                                <input type="text" class="form-control" name="timeout"
                                       maxlength="10"
                                       placeholder="请输入超时设定（填写数字，单位：分钟)">
                                <span class="help-block"></span>
                            </div>
                        </div>
                    </div>
                    <h4>评测指标 <span id="totlascore"></span></h4>
                    <div id="evaluatingRst">
                        <div class="form-group">
                            <div class="row">
                                <label class="col-md-2 control-label">
                                    指标名称
                                </label>
                                <div class="col-md-4">
                                    <input type="text" class="form-control" name="evaluationIndex[0].name"
                                           maxlength="6"
                                           placeholder="请输入评测指标（建议字数在6个字以内)">
                                    <span class="help-block"></span>
                                </div>


                                <label class="col-md-1 control-label">
                                    指标分值
                                </label>
                                <div class="col-md-1">
                                    <input type="text" class="form-control scores" name="evaluationIndex[0].score"
                                           maxlength="10"
                                           placeholder="分值">
                                    <span class="help-block"></span>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-md-2 control-label">
                                    指标内容
                                </label>
                                <div class="col-md-6">
                                    <textarea name="" id="" cols="30" rows="3"></textarea>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-3 col-md-offset-1">
                            <button type="button" class="btn green addOption">添加</button>
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-7">
                            <button class="btn    green" type="submit" style="width:30%">保存</button>
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
<script src="js/act.js?v=${cfg.version}"></script>
</html>
