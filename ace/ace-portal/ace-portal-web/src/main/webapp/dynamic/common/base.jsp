<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%
session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalPath = '${portalPath}';
	var version='${cfg.version}';
	var fastdfs_server='${cfg.fastdfs_server}';
 </script>
<link rel="shortcut icon" href="favicon.ico">