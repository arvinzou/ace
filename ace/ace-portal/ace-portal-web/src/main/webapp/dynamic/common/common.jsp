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
	var layoutTopHeight=280;
	var fastdfs_server='${cfg.fastdfs_server}';
	var default_page_list=[${cfg.default_page_list}];
	var now='${now}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
	var activeSyId ='${SESSION_USERPROP_KEY.activeSyId}';
	var version='${cfg.version}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/system/getUserProp.do"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/bootstrap.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/jquery-ui.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/datepicker.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ui.jqgrid.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-fonts.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/ace-ie8.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-skins.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-rtl.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/ace-ui-custom.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/ui.multiselect.css?version=${cfg.version}" />

<!- 新增部分开始-->
<link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css"
	  rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
	  rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	  rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/content/common/assets/global/css/components.min.css" rel="stylesheet"
	  id="style_components" type="text/css"/>
<link href="${pageContext.request.contextPath}/content/common/assets/global/css/plugins.min.css" rel="stylesheet"
	  type="text/css"/>
<link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/layout.min.css"
	  rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/themes/darkblue.min.css"
	  rel="stylesheet" type="text/css" id="style_color"/>
<link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/custom.min.css"
	  rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="favicon.ico"/>
<script src="${pageContext.request.contextPath}/content/common/js/loading.js?v=${cfg.version}"></script>
<!- 新增部分结束-->