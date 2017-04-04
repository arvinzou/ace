<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${param.id}</title>
<!-- basic scripts -->
<!--[if lte IE 8]>
			<script type="text/javascript" src="/portal/content/common/assets/js/gz/jquery1x.min.js?version=${cfg.version}"></script>
		<![endif]-->
<script type="text/javascript">
	window.jQuery
			|| document
					.write("<script src='/portal/content/common/assets/js/gz/jquery.min.js?version=${cfg.version}'>"
							+ "<"+"/script>");
</script>
<!-- build:css display/styles/displaymodel-style.css -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/content/display/jquery.qtip.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/content/display/displaymodel.css" />
<script>
var contextPath = '${pageContext.request.contextPath}';
jQuery(function($) {
	buidModel("${param.id}");
});
</script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/jquery.qtip.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/raphael.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/bpmn-draw.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/bpmn-icons.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/Polyline.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/display/displaymodel.js"></script>

</head>
<body>
	<div class="model-preview-wrapper">
		<div id="bpmnModel" data-model-id="15023" data-model-type="runtime"
			style="width: 643px; height: 350px;"></div>
	</div>


</body>
</html>