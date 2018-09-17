<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8" %>
<%
session.setAttribute("portalPath", "/portal");
%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var version = '${cfg.version}';
    var fastdfs_server = '${cfg.fastdfs_server}';
    var activeSyId = '${SESSION_USERPROP_KEY.activeSyId}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';
</script>
<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css?v=${cfg.version}" />
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/css/font-awesome.min.css?v=${cfg.version}" />
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/css/components.min.css?v=${cfg.version}" />
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/layout.min.css?v=${cfg.version}" />
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/themes/default.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css?v=${cfg.version}"/>
<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/assets/layouts/layout${SESSION_USERPROP_KEY.cfg.portalType}/css/custom.min.css?v=${cfg.version}" />

