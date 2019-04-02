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
            <div class="form-group">
                <label class="col-md-2 control-label">
                    事故发生地点
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="address" value="\${data.o.address}" readonly
                           maxlength="200"
                           placeholder="请选择事故发生地点" style="float:left;width:90%">

                    <a href="javascript:window.open('${portalPath}/dynamic/common/map.jsp')"
                       style="float:right;line-height: 34px;">选择</a>
                    <span class="help-block"></span>
                    <input name="latitude" type="hidden" value="\${data.o.latitude}"/>
                    <input name="longitude" type="hidden" value="\${data.o.longitude}"/>
                    <input name="areaCode" type="hidden" value="\${data.o.areaCode}"/>
                    <input name="id" type="hidden" value="\${data.o.id}"/>
                    <div class="error-address"></div>
                </div>
            </div>


            <div class="form-group">
                <label class="col-md-2 control-label">
                    天气
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-5">
                    <div class="radio-group-container">
                        {@each data.dict['171'] as item, index}
                        {@if item.CODE!=''}
                        <label class="mt-radio mt-radio-outline">
                            <input type="radio" name="weather" value="\${item.CODE}"
                                   {@if item.CODE==data.o.weather}checked{@/if}
                            >\${item.NAME}
                            <span></span>
                        </label>
                        {@/if}
                        {@/each}
                    </div>
                    <div class="error-weather"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    车型
                    <span class="required" aria-required="true"> * </span>
                </label>
                <div class="col-md-6">
                    <div class="checkbox-group-container">
                        {@each data.dict['172'] as item, index}
                        {@if item.CODE!=''}
                        <label class="mt-checkbox mt-checkbox-outline" style="width: 150px;">
                            <input type="checkbox" name="vehicleType" value="\${item.CODE}"
                                   \${isChecked(item.CODE,data.vehicleType)}>\${item.NAME}
                            <span></span>
                        </label>
                        {@/if}
                        {@/each}
                    </div>
                    <div class="error-vehicleType"></div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    事故时间
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="accidentTime"
                           onchange="validateAccTime(this.value)"
                           value="\${data.o.accidentTime}">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    所属路段
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" style="width:450px" name="roadSectionId"
                           value="\${data.o.roadSectionId}" maxlength="50" placeholder="请输入所属路段（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    所属路长
                </label>
                <div class="col-md-5">
                    <input type="text" id="roadManId" class="form-control" style="width:450px" name="roadManId"
                           value="\${data.o.roadManId}" maxlength="50" placeholder="请输入所属路长（建议字数在14个字以内，不超过50个字)">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    死亡人数
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="deadthToll" value="\${data.o.deadthToll}"
                           maxlength="10" placeholder="">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    受伤人数
                </label>
                <div class="col-md-5">
                    <input type="text" class="form-control" name="injuries" value="\${data.o.injuries}" maxlength="10"
                           placeholder="">
                    <span class="help-block"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    事故原因
                </label>
                <div class="col-md-5">
                    <div class="checkbox-group-container">
                        {@each data.dict['173'] as item, index}
                        {@if item.CODE!=''}
                        <label class="mt-checkbox mt-checkbox-outline">
                            <input type="checkbox" name="cause" value="\${item.CODE}"
                                   \${isChecked(item.CODE,data.cause)}>\${item.NAME}
                            <span></span>
                        </label>
                        {@/if}
                        {@/each}
                    </div>
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
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
        type="text/javascript"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
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
