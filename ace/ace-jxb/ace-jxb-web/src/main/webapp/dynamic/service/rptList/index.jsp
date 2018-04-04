<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="../../common/common.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/common/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/rptList/source/css/rptList.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/jsp/css/rptForm.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/common.js"></script>
    <title>审查现场</title>
</head>

<body>
<div class="container-fluid headContainer">
    <ul class="topToolBtn">
        <li>
            <div class="input-group form-search">
                <input type="text" class="searchByName form-control" placeholder="请输入标题关键字">
                <span class="input-group-btn">
                <button class="search btn btn-default" type="button"><i class="iconfont">&#xe60d;</i></button>
                </span></div>
        </li>
        <li>创建时间</li>
        <li>
            <select class="form-control" id="chooseStatus">
                <option value="">全部报道</option>
                <option value="1">待审报道</option>
                <option value="2">已审报道</option>
            </select>
        </li>

        <li class="delectRptbtn">
            <button class="btn btn-danger delect-rpt">删除报道</button>
        </li>
    </ul>
</div>
<div class="container-fluid contentRpt">
    <ul class="reportList" id="jxbReportTable">

    </ul>
</div>

<div id="htmlLoad">

</div>
<div id="JSLoad">

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/service/rptList/source/js/rptList.js"></script>
</html>

