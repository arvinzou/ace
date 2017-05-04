<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>个性化配置</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">


</script>
<body>
	<div class="page-content">

<table id="pg">
</table>

	<div id="toolbar" class="toolbar">



    						<button class="btn btn-info btn-block" id="btn-view-save"
    							authority="false">
    							保存
    						</button>


    					</div>
		
	</div>

	<jsp:include page="../../common/footer-1.jsp" />

	<script
		src="${pageContext.request.contextPath}/content/service/userCfg/controller.js?version=${cfg.version}"></script>

	<jsp:include page="../../common/footer-2.jsp" />

</body>
</html>