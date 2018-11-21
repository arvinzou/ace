<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <title>会员分布</title>
</head>
<jsp:include page="/dynamic/common/header.jsp"/>
<style>
    .charts {
        width: 485px;
        height: 350px
    }
</style>
<body>
<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
<div class="portlet light ">

    <div class="portlet-body" style="padding:50px">

<div class="row">

    <div id="ct" class="charts col-md-6"></div>

    <div class="charts col-md-6">
        <table id="table"
               class="table">
            <thead>
            <tr>
                <th class="center" style="width:80px;">序号</th>
                <th>区域</th>
                <th>数量</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</div>


    </div>
</div>

<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


<jsp:include page="/dynamic/common/footer.jsp" />
<script src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>

<script src="${pageContext.request.contextPath}/content/service/analysis/memberArea/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/analysis/memberArea/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/analysis/view.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/analysis/index.js?version=${cfg.version}"></script>

</body>
</html>