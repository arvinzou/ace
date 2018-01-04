<%@page import="java.util.Date"%>
<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
session.setAttribute("portalPath", "/portal");
request.setAttribute("now", CommonUtils.formatDate(new Date()));
%>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalPath = '${portalPath}';
	var layoutTopHeight=190;
	var fastdfs_server='${cfg.fastdfs_server}';
	var default_page_list=[${cfg.default_page_list}];
	var now='${now}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
	var websocketurl='${cfg.websocketurl}';

</script>
<script type="text/javascript"
	src="${portalPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript"
	src="${portalPath}/system/getUserProp.do"></script>
<script type="text/javascript"
	src="${portalPath}/content/common/js/base.js?version=${cfg.version}"></script>
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/bootstrap.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/jquery-ui.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/datepicker.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/ui.jqgrid.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/ace-fonts.css?version=${cfg.version}" />
	
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/ace-ie8.min.css?version=${cfg.version}" />

	
<!--[if lte IE 9]>
			<link rel="stylesheet" href="${portalPath}/content/common/assets/css/ace-part2.min.css?version=${cfg.version}" />
<![endif]-->
		
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/ace-skins.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${portalPath}/content/common/assets/css/ace-rtl.min.css?version=${cfg.version}" />
	
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${portalPath}/content/common/assets/css/ace-ie.min.css?version=${cfg.version}" />
<![endif]-->

       <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

	<!--[if lte IE 8]>
	<script src="${portalPath}/content/common/assets/js/gz/html5shiv.js?version=${cfg.version}"></script>
	<script src="${portalPath}/content/common/assets/js/gz/respond.min.js?version=${cfg.version}"></script>
	<![endif]-->		

<link rel="stylesheet" href="${portalPath}/content/common/css/ace-ui-custom.css?version=${cfg.version}" />
<link rel="stylesheet" href="${portalPath}/content/common/css/ui.multiselect.css?version=${cfg.version}" />
