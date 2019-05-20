<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>GIS</title>
</head>
<script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}"/>
<%--<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css?v=${cfg.version}"/>--%>
<%--<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}"/>--%>
<%--<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/layout.min.css?v=${cfg.version}"/>--%>
<%--<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/themes/default.min.css?v=${cfg.version}"/>--%>
<%--<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}"/>--%>
<%--<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/custom.min.css?v=${cfg.version}"/>--%>
<link rel="stylesheet" type="text/css" href="css/style.css?v=${cfg.version}"/>
<script charset="UTF-8" src="${pageContext.request.contextPath}/content/common/plugins/bmap_v3/map_load.js"></script>
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
<%--<link rel="stylesheet" href="css/swiper.min.css">--%>

<div id="Header">
    <img src="img/logo.png" alt="">智慧照明故障监控平台
</div>


<div id="Map">

</div>

<%--<div class="searchBar">--%>
    <%--<div class="rst"><span class="area">全部</span> <span class="station">>><span class="text">全部</span></span></div>--%>
    <%--<div class="select">--%>
        <%--<div class="options">--%>
            <%--<form>--%>
                <%--<p>分区</p>--%>
                <%--<div class="area ulstyle">--%>
                    <%--<ul id="areaList">--%>

                    <%--</ul>--%>
                <%--</div>--%>
                <%--<p>站点</p>--%>
                <%--<div class="station ulstyle">--%>
                    <%--<ul id="stationList" class="stationList">--%>

                    <%--</ul>--%>
                <%--</div>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<div class="rightBar">
    <div class="statusBar">
        <div class="data data1">全部</div>
        <div class="data data2">正常
            <span>2300</span>
        </div>
        <div class="data data3">故障
            <span>2300</span>
        </div>
    </div>

    <div class="detailBar">
        <div class="btns">
            <div class="b active b1"><span>建筑物信息</span></div>
            <div class="b b2"><span>故障信息</span></div>
            <div class="bg"></div>
        </div>
        <div class="content">
            <div class="swriper">
                <div class="swiper-wrapper">
                    <div class="swiper-slide table">
                        <div class="tftables">
                            <table class="tftable" border="1">
                                <tr>
                                    <th>分控器编号</th>
                                    <th>通道编号</th>
                                    <th>灯组编号</th>
                                    <th>故障时间</th>
                                </tr>
                            </table>
                        </div>
                        <div class="tableList">
                            <table class="tftable" border="1" id="guzhang">

                            </table>

                        </div>
                        <div class="status">
                            <span class="red color"></span> <span class="text">严重</span>
                            <span class="orange color"></span> <span class="text">一般问题</span>
                            <span class="yellow color"></span> <span class="text">轻度问题</span>
                        </div>

                    </div>
                    <div class="swiper-slide build" id="buildInfo">

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<div class=" accident_info">
    <div class="portlet-body" id="fm-preview">

    </div>
</div>


<script id="tpl-buildInfo" type="text/template">
    <p>当前正在播放场景/\${data.isPlaying==1?'已暂停':'播放中'}</p>
    <p>\${data.sceneNum}</p>
    <video class="video" src="\${data.coverURL}">
    </video>
    <div class="list">
        <p>建筑物名称：\${data.buildingName}</p>
        <p>建筑物类型：联机建筑</p>
        <p>所在地：\${data.address}</p>
        <p>建筑物编号：\${data.buildingNo}</p>
        <p>是否重点建筑：重点建筑</p>
        <p> 建筑物状态：\${data.status==1?'在线':'离线'}</p>
        <p>回路故障数量:6</p>
        <p>设备总数：\${data.lampCount}</p>
        <p>控制器数量：\${data.ctrlCount}</p>
    </div>
</script>

<script id="tpl-guzhang" type="text/template">

    <tr>
        <th>分控器编号</th>
        <th>通道编号</th>
        <th>灯组编号</th>
        <th>故障时间</th>
    </tr>
    {@each data.le as item, index}
    <tr>
        <th>\${item.controller}</th>
        <th>\${item.channelNo}</th>
        <th>\${item.lampNo}</th>
        <th>\${parseTime(item.createDate)}</th>
    </tr>
    {@/each}
</script>



<script id="tpl-areaList" type="text/template">
    {@each data as item, index}
    <li>
        <input onchange="areaChange(this)" data-name="\${item.name}" data-code="\${item.code}"
               data-la="\${item.latitude}" name="q" data-lo="\${item.longitude}" \${index==0?'checked':''}
               id="a\${index}"
               type="radio">
        <label for="a\${index}">\${item.name}</label>
    </li>
    {@/each}
</script>



<script id="tpl-stationList" type="text/template">
    <li>
        <input onchange="stationChange(this)" data-name="全部" data-code=""
               data-la="" data-lo="" name="stationCode" checked id="s" type="radio">
        <label for="s">全部</label>
    </li>
    {@each data as item, index}
    <li>
        <input onchange="stationChange(this)" id="s\${index}" data-name="\${item.name}"
               data-code="\${item.code}"
               data-la="\${item.latitude}" data-lo="\${item.longitude}" name="stationCode"
               type="radio">
        <label for="s\${index}">\${item.name}</label>
    </li>
    {@/each}
</script>

<!--[if lt IE 9]>
<!--<script src="${portalPath}/content/common/assets/global/plugins/respond.min.js"></script>-->
<%--<script src="${portalPath}/content/common/assets/global/plugins/excanvas.min.js"></script>--%>
<%--<script src="${portalPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>--%>
<![endif]-->
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"
        type="text/javascript"></script>
<%--<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=${cfg.version}" type="text/javascript"></script>--%>
<%--<script src="${portalPath}/content/common/assets/global/plugins/js.cookie.min.js?v=${cfg.version}"--%>
        <%--type="text/javascript"></script>--%>
<%--<script src="${portalPath}/content/common/js/init-rem.js?v=${cfg.version}" type="text/javascript"></script>--%>
<%--<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>--%>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

<%--<script src="js/swiper.min.js"></script>--%>

<%--<link rel="stylesheet" type="text/css"--%>
      <%--href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">--%>
<%--<link rel="stylesheet" type="text/css"--%>
      <%--href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">--%>
<%--<script type="text/javascript"--%>
        <%--src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>--%>
<%--<script type="text/javascript"--%>
        <%--src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>--%>
<%--<script type="text/javascript"--%>
        <%--src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.js"></script>--%>
<%--<script type="text/javascript"--%>
        <%--src="${pageContext.request.contextPath}/content/common/js/datetimepicker/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>--%>
<script src="js/Concurrent.Thread.js"></script>
<script src="js/act.js?version=${cfg.version}"></script>

<script>
    //
//    var map = new BMap.Map("Map", {});//限定缩放比例，13-15
//    var point = new BMap.Point(114.275923, 30.603137);  // 创建点坐标
//    map.centerAndZoom(point, 13);                 // 初始化地图，设置中心点坐标和地图级别
//    //添加地图类型控件
//    map.addControl(new BMap.MapTypeControl({
//        mapTypes: [
//            BMAP_NORMAL_MAP,
//            BMAP_HYBRID_MAP
//        ]
//    }));
//    map.setCurrentCity("武汉");          // 设置地图显示的城市 此项是必须设置的
//    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
//    //单击获取点击的经纬度
//    map.addEventListener("click", function (e) {
//        alert(e.point.lng + "," + e.point.lat);
//    });
</script>

</body>
</html>
