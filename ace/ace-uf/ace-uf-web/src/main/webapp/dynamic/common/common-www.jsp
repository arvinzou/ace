<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%
session.setAttribute("portalPath", "/portal");

%>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalPath = '${portalPath}';
	var fastdfs_server = 'http://zx.huacainfo.com/';
</script>
<link rel="stylesheet" href="${portalPath}/content/common/weui/weui.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${portalPath}/content/common/weui/jquery-weui.min.css?version=${cfg.version}" />