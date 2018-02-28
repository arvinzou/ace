<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>
    <title>企业单位基本信息</title>
</head>
<jsp:include page="../../common/header.jsp"/>
<script type="text/javascript">
</script>
<body>
<jsp:include page="../../common/footer.jsp"/>
<script src="${pageContext.request.contextPath}/content/service/company/config.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/model.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/controller.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/service/company/view.js?version=${cfg.version}"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/service/company/style.css?version=${cfg.version}" />
</body>
</html>