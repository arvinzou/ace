<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <jsp:include page="../../common/common.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/common/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/liveRpt/source/css/liveRpt.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/jsp/css/rptForm.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/common/js/common.js"></script>
    <title>创建报道</title>
</head>

<body>
<div class="container-fluid headContainer">
    <div class="searchInput input-group form-search">
        <input type="text" class="searchByName form-control" placeholder="请输入标题关键字">
        <span class="input-group-btn">
        <button class="search btn btn-default" type="button"><i class="iconfont">&#xe60d;</i></button>
        </span>
    </div>
</div>
<div class="container-fluid content_container">
    <div class="table_content">
        <table id="jxbListTable" class="table-striped table">
            <tr>
                <td>【常德融媒】常德冰冻周实时直播</td>
                <td>全媒体记者 伍银</td>
                <td>2018-01-24 10:31</td>
                <td><a>发布报道</a></td>
            </tr>
        </table>
    </div>
</div>
<div id="htmlLoad">

</div>
<div id="JSLoad">

</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/dynamic/service/liveRpt/source/js/liveRpt.js"></script>
</html>
