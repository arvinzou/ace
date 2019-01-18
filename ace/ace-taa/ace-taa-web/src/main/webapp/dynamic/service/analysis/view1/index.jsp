<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<script charset="utf-8" src="https://map.qq.com/api/js?v=2.exp&key=ALFBZ-5Z2CJ-TK6F7-KVINX-AX5L7-UFBXL"></script>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
    var default_page_list = [${cfg.default_page_list}];
</script>
<script>
    var id='${param.id}';
</script>

<body>
<style type="text/css">
        html,
        body {
            height: 100%;
            margin: 0px;
            padding: 0px;
            background:#fff;
        }
        #container {
            height: 600px;
        }
#FullScreen {
    position: absolute;
    top: 50%;
    left: -10pxpx;
    margin-top: -40px;
    z-index: 201;
    width: 21px;
    height: 58px;
    cursor: pointer;
}
#MapViewPanel {
    position: relative;
    overflow: hidden;
    top: 33px;
}
.InlineBlock {
    display: inline-block;
    zoom: 1;
    *display: inline;
    _display: inline;
}
    </style>



<div class="row" style="height: 100%;">
    <div class="col-md-3 collapse" id="toolBar">

    </div>
    <div class="col-md-12" id="map">
        <a id="FullScreen" href="#toolBar" data-toggle="collapse" title="收起左栏"
           class="InlineBlock" pgv="86"
           style="background-image: url(https://3gimg.qq.com/webmap_pc/v/themes/default/img/arrow.png?v=v5.1.132); background-position: -44px 0px;"></a>
        <div id="container"></div>
    </div>
</div>

<!--[if lt IE 9]>
<script src="${portalPath}/content/common/assets/global/plugins/respond.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/excanvas.min.js"></script>
<script src="${portalPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/assets/global/plugins/js.cookie.min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/init-rem.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/loading.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/juicer/juicer-min.js?v=${cfg.version}" type="text/javascript"></script>
<script src="${portalPath}/content/common/js/jquery.form.js?v=${cfg.version}"></script>
<script src="${portalPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>
<script src="js/act.js?version=${cfg.version}"></script>
</body>
</html>