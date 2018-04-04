<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="../../common/common.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/liveSub/source/css/liveSub.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/common/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/jsp/css/liveForm.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/jsp/css/rptManageInLive.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/common.js"></script>
    <title>审查现场</title>
</head>

<body>
<div class="container-fluid headContainer">
    <ul class="topToolBtn">
        <li>
            <div class="input-group form-search">
                <input type="text" class="searchByName  form-control" placeholder="请输入标题关键字">
                <span class="input-group-btn">
                <button class="search btn btn-default" type="button"><i class="iconfont">&#xe60d;</i></button>
                </span></div>
        </li>
        <li class="sortLive">创建时间</li>
        <li>
            <select class="form-control jxbStatus">
                <option value="">全部现场</option>
                <option value="1">直播预告</option>
                <option value="2">正在直播</option>
                <option value="3">直播结束</option>
            </select>
        </li>
        <li>
            <select class="form-control" id="type">

            </select>
        </li>
    </ul>
</div>
<div class="container-fluid jxbcontent">
    <ul class="sceneList">

    </ul>
</div>

<div id="htmlLoad">

</div>
<div id="JSLoad">

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/service/liveSub/source/js/liveSub.js"></script>
</html>

