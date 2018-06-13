<%@page import="java.util.Date" %>
<%@page import="com.huacainfo.ace.common.tools.CommonUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    session.setAttribute("portalPath", "/portal");
    request.setAttribute("now", CommonUtils.formatDate(new Date()));
%>
<!-- basic scripts -->
<!--[if lte IE 8]>
<script type="text/javascript" src="${portalPath}/content/common/assets/js/gz/jquery1x.min.js?version=${cfg.version}"></script>
<![endif]-->
<script type="text/javascript">
    window.jQuery || document.write("<script src='${portalPath}/content/common/assets/js/gz/jquery.min.js?version=${cfg.version}'>" + "<" + "/script>");
</script>
<link href="//cdn.bootcss.com/tether/1.3.6/css/tether.min.css" rel="stylesheet">
<script src="//cdn.bootcss.com/tether/1.3.6/js/tether.min.js"></script>
<link rel="stylesheet" href="${portalPath}/content/common/bootstrap/css/bootstrap.min.css?version=${cfg.version}"/>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var layoutTopHeight = 190;
    var fastdfs_server = '${cfg.fastdfs_server}';
    var default_page_list = [${cfg.default_page_list}];
    var now = '${now}';
    var portalType = '${SESSION_USERPROP_KEY.cfg.portalType}';

</script>
<script type="text/javascript" src="${portalPath}/system/getButtonAuthority.do?id=${param.id}"></script>
<script type="text/javascript" src="${portalPath}/system/getUserProp.do?version=${cfg.version}"></script>
<script type="text/javascript" src="${portalPath}/content/common/js/base.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${portalPath}/content/common/bootstrap/js/bootstrap.min.js?version=${cfg.version}"></script>