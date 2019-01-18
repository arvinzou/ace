<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>路段</title>
</head>
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
            padding: 0px
        }
        #container {
            width: 100%;
            height: 100%
        }
    </style>

        <div id="container"></div>

<ul class="nav nav-list">

    <li><a href="#demo0" data-toggle="collapse" class="btn btn-info">地址</a></i>
        <div id="demo0" class="collapse">

            <ul class="nav nav-list">

                <li><a href="#" class="btn btn-success">cq<a><li>

                <li><a href="#" class="btn btn-success">cs<a><li>

                <li><a href="#" class="btn btn-success">cd<a><li>

            </ul>

        </div>
</ul>
<script src="${portalPath}/content/common/assets/global/plugins/jquery.min.js?v=${cfg.version}"></script>
<script src="js/act.js?version=${cfg.version}"></script>
</body>
</html>