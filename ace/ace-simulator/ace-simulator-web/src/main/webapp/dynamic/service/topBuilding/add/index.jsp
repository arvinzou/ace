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
            <form class="form-horizontal" id="fm-add" role="form">
                <div class="form-body">
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            建筑编号
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="code" maxlength="50"
                                   placeholder="请输入建筑编号（不超过50个字符)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            建筑名称
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="name" maxlength="50"
                                   placeholder="请输入建筑名称（不超过50个字符)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            建筑物类型
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-10">
                            <select name="type" id="type" class="form-control">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            建筑描述
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="depict" maxlength="200"
                                   placeholder="请输入建筑描述（不超过200个字)">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            所在地
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="address" maxlength="200"
                                   placeholder="请输入所在地（不超过200个字)" style="float:left;width:92%">
                            <a href="javascript:window.open('${portalPath}/dynamic/common/map.jsp')"
                               style="float:right;line-height: 28px;">选择</a>
                            <span class="help-block"></span>
                            <input name="latitude" type="hidden"/>
                            <input name="longitude" type="hidden"/>
                            <div class="error-address"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            重点建筑标记
                        </label>
                        <div class="col-md-10">
                            <input type="radio" value="0" name="mainTag">不重要<span style="padding-right: 60px;"></span>
                            <input type="radio" value="1" name="mainTag">重要
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            建筑物状态
                        </label>
                        <div class="col-md-10">
                            <input type="radio" value="0" name="state">不在线<span style="padding-right: 60px;"></span>
                            <input type="radio" value="1" name="state">在线
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            所属站点
                            <span class="required" aria-required="true"> * </span>
                        </label>
                        <div class="col-md-10">
                            <select name="stationCode" id="areaList" class="form-control">

                            </select>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">
                            备注
                        </label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="remark" maxlength="200"
                                   placeholder="请输入备注（不超过200个字)">
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


<script id="type-tpl" type="text/template">
    {@each data as typeItem, index}
    <option value="\${typeItem.CODE}">\${typeItem.NAME}</option>
    {@/each}
</script>

<script id="area-tpl" type="text/template">
    {@each data as item, index}
    <option value="\${item.code}">\${item.name}</option>
    {@/each}
</script>


<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp"/>
</body>
<jsp:include page="/dynamic/common/footer.jsp"/>
<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
<script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>
</html>
