

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
	var contextPath = '${portalPath}';
	var portalPath = '${portalPath}';
	var layoutTopHeight=280;
	var fastdfs_server='${cfg.fastdfs_server}';
	var default_page_list=[${cfg.default_page_list}];
	var now='${now}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
	var activeSyId ='${SESSION_USERPROP_KEY.activeSyId}';
	var version='${cfg.version}';
</script>
<script type="text/javascript" src="${portalPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript" src="${portalPath}/system/getUserProp.do"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/base.js?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/loading.js?v=${cfg.version}"></script>
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/bootstrap.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/jquery-ui.min.css?version=${cfg.version}" />
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/ui.jqgrid.css?version=${cfg.version}" />
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/ace-fonts.css?version=${cfg.version}" />
<link rel="stylesheet" href="${portalPath}/content/common/assets/css/ace-ie8.min.css?version=${cfg.version}" />

<link rel="stylesheet" href="${portalPath}/content/common/css/ui.multiselect.css?version=${cfg.version}" />
<link href="${portalPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<%if(portalType.equals("1")){%>
<link href="${portalPath}/content/common/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
<link href="${portalPath}/content/common/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color"/>
<%}else{%>
<link href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css" rel="stylesheet" type="text/css"/>
<link href="${portalPath}/content/common/assets/layouts/layout3/css/themes/default.min.css" rel="stylesheet" type="text/css"/>

<style>
.page-content-wrapper .page-content {
    margin-left: 0px;
    margin-top: 0;
    margin-bottom: 30px;
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

.dropdown-menu>li>a:hover, .dropdown-menu>li>a:focus, .dropdown-menu>li>a:active, .dropdown-menu>li.active>a, .dropdown-menu>li.active>a:hover, .dropdown-menu .dropdown-submenu:hover>a {
    background: #f6f6f6;
    color: #000;
}
.page-head .page-title {
    display: inline-block;
    float: left;
    padding: 6px 0;
}
</style>
<%}%>
<link rel="stylesheet" href="${portalPath}/content/common/css/ace-ui-custom.css?version=${cfg.version}" />
<link rel="shortcut icon" href="favicon.ico"/>