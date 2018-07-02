<%@page import="java.util.Date"%>
<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalPath = '${portalPath}';
	var layoutTopHeight=190;
	var fastdfs_server='${cfg.fastdfs_server}';
	var default_page_list=[${cfg.default_page_list}];
	var now='${now}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
</script>
