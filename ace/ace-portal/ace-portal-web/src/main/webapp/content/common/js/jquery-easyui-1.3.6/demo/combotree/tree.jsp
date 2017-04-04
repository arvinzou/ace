<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>企业资源管理</title>
</head>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/system/getButtonAuthor.do?id=${param.id}"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/jquery-ui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/datepicker.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ui.jqgrid.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-fonts.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/assets/css/ace-rtl.min.css" />
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-part2.min.css" />
		<![endif]-->

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/content/assets/css/ace-ie.min.css" />
		<![endif]-->
<!-- inline styles related to this page -->
<!--[if lte IE 8]>
		<script src="${pageContext.request.contextPath}/content/assets/js/html5shiv.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/assets/js/respond.min.js?version=${cfg.version}"></script>
		<![endif]-->
<script
	src="${pageContext.request.contextPath}/content/assets/js/ace-extra.min.js?version=${cfg.version}"></script>
<script type="text/javascript">
	window.jQuery
			|| document
					.write("<script src='${pageContext.request.contextPath}/content/assets/js/jquery.min.js?version=${cfg.version}'>"
							+ "<"+"/script>");
</script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/bootstrap.min.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/date-time/bootstrap-datepicker.min.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/uncompressed/jqGrid/jquery.jqGrid.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/ace-elements.min.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/assets/js/ace.min.js?version=${cfg.version}"></script>

	

<body>
	<input class="easyui-combotree" data-options="url:'tree_data1.json',method:'get',required:true" style="width:200px;">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/jquery-easyui-1.3.6/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/content/jquery-easyui-1.3.6/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/content/jquery-easyui-1.3.6/jquery.easyui.min.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/js/common/jquery.form.js?version=${cfg.version}"></script>
</body>
</html>