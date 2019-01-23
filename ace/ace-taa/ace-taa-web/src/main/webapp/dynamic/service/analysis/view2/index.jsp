<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
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
    <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
          rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css"
          href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript">
        var contextPath = '${pageContext.request.contextPath}';
        var portalPath = '${portalPath}';
        var version = '${cfg.version}';
        var fastdfs_server = '${cfg.fastdfs_server}';
        var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
        var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    </script>
</head>
<body style="height: 100%; margin: 0">
<div id="Header">
    <img src="img/logo.png" alt="">常德市路长制信息管理平台
</div>


<div id="Map">

</div>

<div id="leftDiv" class="leftIcon transitionDiv">
    <div class="content">
        <form id="fm">
            <div class="title">精确搜索</div>
            <div class="seach morginBottom40">
                <input class="easyui-combotree" style=" width: 120px;height: 46px;border:0 !important;" name="areaCode"
                       data-options="url:'${portalPath}/system/selectProvinceTreeList.do',
					method:'get',
					label:'',
					labelPosition:'top',
					onSelect:function  (node) {
						 setParams('areaCode', node.id);
					}">
                <input class="loadName" name="roadName" type="text">
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
                <input type="text" size="16" name="downDeathNum" class="inputStyle form-control"
                       style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
                <input type="text" size="16" name="upDeathNum" class="inputStyle form-control"
                       style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
            </div>
            <div class="title">受伤人数</div>
            <div class="morginBottom40 chenckContent contentStyle">
                <input type="text" size="16" name="downInjuriesNum" class="inputStyle form-control"
                       style="border: 1px solid #efefef;" oninput="value=value.replace(/[^\d]/g,'')">
                <input type="text" size="16" name="upInjuriesNum" class="inputStyle form-control"
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
    <button type="button" authority="false" class="btn btn-default inputStyle"
            onclick="setParams('\${data.key}','\${item.CODE}');">\${item.NAME}
    </button>
    {@else}
    <button type="button" authority="false" class="btn btn-default inputStyle" onclick="setParams('\${data.key}','');">
        全部
    </button>
    {@/if}

    {@/each}

</script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>

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
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/init-rem.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>

<script type="text/javascript"
        src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>

<script type="text/javascript" src="js/act.js"></script>
</body>
</html>