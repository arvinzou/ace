<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>路段</title>
</head>
<script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
<link rel="stylesheet"
      href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/layout.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/themes/default.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/custom.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css" href="css/style.css?v=${cfg.version}"/>
<script charset="utf-8"
        src="https://map.qq.com/api/js?v=2.exp&key=ALFBZ-5Z2CJ-TK6F7-KVINX-AX5L7-UFBXL&libraries=drawing,geometry"></script>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
</script>
<script>
    var id = '${param.id}';
</script>
<link href="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.css"
      rel="stylesheet" type="text/css"/>

<div id="Header">
    <img src="img/logo.png" alt="">常德市路长制信息管理平台
</div>


<div id="Map">

</div>

<div id="leftDiv" class="<%--leftIcon--%> centerIcon">
    <div class="content">
        <form id="fm">
            <div class="title">精确搜索</div>
            <div class="seach morginBottom40">
                <input id="tt" class="easyui-combotree" style=" width: 120px;height: 46px;border:0; !important;" name="areaCode">
                <input placeholder="按地址、路长模糊搜索" class="loadName" name="keyword" type="text">
                <button class="active_seach"></button>
            </div>
            <div class="title">道路级别</div>
            <div class="morginBottom40 chenckContent contentStyle" id="check-group-category">

            </div>

            <div class="title">时间范围</div>
            <div class="morginBottom40 timeContent contentStyle">
                <input type="text" size="16" name="startDate" readonly="" class="form-control inputStyle">
                <input type="text" size="16" name="endDate" readonly="" class="form-control inputStyle">
            </div>
            <div class="title">死亡人数</div>
            <div class="morginBottom40 chenckContent contentStyle">
                <input type="number"  size="16" name="downDeathNum" class="inputStyle form-control"
                       style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
                <input type="number" size="16" name="upDeathNum" class="inputStyle form-control"
                       style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
            </div>
            <div class="title">受伤人数</div>
            <div class="morginBottom40 chenckContent contentStyle">
                <input type="number" size="16" name="downInjuriesNum" class="inputStyle form-control"
                       style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
                <input type="number" size="16" name="upInjuriesNum" class="inputStyle form-control"
                       style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
            </div>
            <div class=" buttonStyle contentStyle">
                <button type="reset" class="reset">重置</button>
                <button type="submit" class="submit">确定</button>
            </div>
        </form>
    </div>
</div>

<script id="tpl-check-group" type="text/template">

    {@each data.list as item, index}
    {@if item.CODE}
    <button type="button" authority="false" class=" btn btn-default inputStyle" data-id="\${item.CODE}" >\${item.NAME}
    </button>
    {@else}
    <button type="button" authority="false" class="btn active_but btn-default inputStyle" data-id="">
        全部
    </button>
    {@/if}

    {@/each}

</script>

<div class=" accident_info">
    <div class="portlet-body" id="fm-preview">

    </div>
</div>


<script id="tpl-preview" type="text/template">

    <div>
        <p class="title">\${data.o.roadSectionName}</p>
        <p title="address">\${data.o.address}</p>
    </div>
    <ul>
        <li><span>所属路长</span> <span>\${data.o.roadManName}</span></li>
        <li><span>天气情况</span> <span>\${rsd(data.o.weather,'171')}</span></li>
        <li><span>车型</span> <span>\${rsd(data.o.vehicleType,'172')}</span></li>
        <li><span>事故时间</span> <span>\${data.o.accidentTime}</span></li>
        <li><span>死亡人数</span> <span>\${data.o.deadthToll}人</span></li>
        <li><span>受伤人数</span> <span>\${data.o.injuries}人</span></li>
    </ul>
</script>
<!--[if lt IE 9]>
<script src="${portalPath}/content/common/assets/global/plugins/respond.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/excanvas.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/js.cookie.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<script src="${portalPath}/content/common/js/init-rem.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
<link rel="stylesheet" type="text/css"
      href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
<script src="js/Concurrent.Thread.js"></script>
<script src="js/act.js?version=${cfg.version}"></script>

</body>
</html>
