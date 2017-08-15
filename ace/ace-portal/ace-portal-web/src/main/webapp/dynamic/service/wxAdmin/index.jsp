<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>活动</title>
</head>
<jsp:include page="../../common/common.jsp"/>

<link rel="stylesheet" href="${portalPath}/content/common/assets/css/colorbox.css" />

<script type="text/javascript">


</script>
<body>
<div class="page-content">
    <h5 class="header-title">管理员</h5>
    <div id="wxUser"></div>
</div>
<div id="dialog-message" class="hide">
    <div>
       昵称： <input type="text" name="q" id="q"/>
        <button class="btn btn-info" id="btn-search"
                authority="false">
            查询
            <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
        </button>
    </div>
    <div id="wxUserList">

    </div>
</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script src="${portalPath}/content/common/assets/js/uncompressed/jquery.colorbox.js"></script>

<script
        src="${pageContext.request.contextPath}/content/service/wxAdmin/controller.js?version=${cfg.version}"></script>


<jsp:include page="../../common/footer-2.jsp"/>

    <style>
        .layout-user {
            width: 80px;
            height: 80px;
            float: left;
            margin: 10px 10px 10px;
            text-align:center;
        }
        .photo {
            height: 60px;
            max-height:60px;
            max-width:60px;
            vertical-align: middle;
            border-radius:60px;
        }
        #cboxContent {
            background-color: rgb(255, 255, 255);
            border-width: 1px;
            border-style: solid;
            border-color: #ddd;
            border-image: initial;
            padding: 5px;
        }
        .ace-thumbnails > li {
            float: left;
            display: block;
            position: relative;
            overflow: hidden;
            margin: 2px;
            border-width: 0px;
            border-style: solid;
            border-color: #ddd;
            border-image: initial;
        }


    </style>

</div>
</body>
</html>