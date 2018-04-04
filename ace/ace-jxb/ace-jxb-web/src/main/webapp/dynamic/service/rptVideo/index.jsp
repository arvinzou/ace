<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="../../common/common.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/common/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/rptVideo/source/css/rptVideo.css">
    <title>审查视频</title>
</head>

<body>
<div class="container-fluid headContainer">
    <ul class="topToolBtn">
        <li>
            <div class="input-group">
                <input id="startDate" size="10" type="text" readonly class="inputDate form-control form_datetime"
                       placeholder="开始时间">
            </div>
        </li>
        <li> 至</li>
        <li>
            <div class="input-group">
                <input id="endDate" size="10" type="text" readonly class="inputDate form-control form_datetime"
                       placeholder="结束时间">
            </div>
        </li>
        <li>
            <div class="input-group form-search">
                <input type="text" class="form-control" placeholder="请输入标题关键字">
                <span class="input-group-btn">
                <button class="btn btn-default" type="button"><i class="iconfont">&#xe60d;</i></button>
                </span></div>
        </li>
        <li> 创建时间</li>
        <li>
            <button type="button" class="delect-video btn btn-danger">删除视频</button>
        </li>
    </ul>
</div>
<div class="container-fluid video-content">
    <ul class="reportList">
        <li class="picbar">
            <div class="videobar">
                <div class="pic">
                    <video src="11.mp4"></video>
                </div>
                <div class="mediaMark">
                </div>
                <label class="Topcheckbox">
                    <input type="checkbox">
                </label>
                <div class="title omission">【常德融媒直播】常德市第十届农业博览会暨首届年货节</div>
            </div>
            <div class="msgbar">
                <span class="omission msgbar-common creater">
                    <i class=""></i> 王瑜王瑜王瑜王瑜王瑜王瑜王瑜王瑜
                </span>
                <span class="msgbar-common msgbar-time">
                    <i class="icon-clock"></i> 2018-01-12 09:39
                </span>
                <a href="" download="">下载</a>
                <a class="j-report">发布</a>
            </div>
        </li>
    </ul>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/service/rptVideo/source/js/rptVideo.js"></script>
</html>

