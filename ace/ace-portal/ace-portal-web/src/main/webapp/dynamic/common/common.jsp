<%@page import="java.util.Date"%>
<%@page import="com.huacainfo.ace.common.tools.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
session.setAttribute("portalPath", "/portal");
request.setAttribute("now", CommonUtils.formatDate(new Date()));
com.huacainfo.ace.common.model.UserProp user=(com.huacainfo.ace.common.model.UserProp)session.getAttribute("SESSION_USERPROP_KEY");
String portalType=(String)user.getCfg().get("portalType");

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
<script type="text/javascript" src="${pageContext.request.contextPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/system/getUserProp.do"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/js/loading.js?v=${cfg.version}"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/bootstrap.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/jquery-ui.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/ui.jqgrid.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/ace-fonts.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/ace-ie8.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/ace-ui-custom.css?version=${cfg.version}" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/css/ui.multiselect.css?version=${cfg.version}" />
<link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<%if(portalType.equals("1")){%>
<link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color"/>
<%}else{%>
<link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/css/layout.min.css" rel="stylesheet" type="text/css"/>
<style>
.page-content-wrapper .page-content {
    margin-left: 0px;
    margin-top: 0;
    min-height: 300px;
    padding: 0px 0px 0px;
}
.breadcrumb1 {
    background: none;
    padding-top:15px;
    color: #a7b2c0;
    font-size:14px;
}
.page-header {
    padding-top: 0px;
}
</style>
<%}%>
<link rel="shortcut icon" href="favicon.ico"/>