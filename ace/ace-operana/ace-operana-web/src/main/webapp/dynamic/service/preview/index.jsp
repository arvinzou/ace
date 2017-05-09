<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>运营分析</title>
</head>
<jsp:include page="../../common/common.jsp"/>
<script type="text/javascript">
var meetingId='${param.meetingId}';
var topicId='${param.topicId}';
var normId='${param.normId}';

</script>
<body>
<div class="page-content">
    <div class="row"><div class="col-md-6" id="ct1">ct1</div><div class="col-md-6" id="ct2">ct2</div></div>
    <div class="row"><div class="col-md-6" id="ct3">ct3</div><div class="col-md-6" id="ct4">ct4</div></div>
</div>
<jsp:include page="../../common/footer-1.jsp"/>
<script
        src="${portalPath}/content/common/js/echarts-2.27/echarts.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/preview/config-1.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/preview/controller.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/service/preview/view.js?version=${cfg.version}&t=1"></script>
<jsp:include page="../../common/footer-2.jsp"/>

</body>
</html>