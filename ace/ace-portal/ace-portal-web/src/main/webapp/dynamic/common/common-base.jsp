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

</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/bootstrap/css/bootstrap.min.css?version=${cfg.version}" />
<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/bootstrap/js/bootstrap.min.js?version=${cfg.version}"></script>