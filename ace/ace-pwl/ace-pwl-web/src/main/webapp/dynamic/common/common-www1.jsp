<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    session.setAttribute("portalPath", "/portal");

%>
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var portalPath = '${portalPath}';
    var fastdfs_server = 'http://zx.huacainfo.com/';
</script>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/content/common/lib/weui.min.css?version=${cfg.version}"/>
<link rel="stylesheet"
      href="${pageContext.request.contextPath}/content/common/css/jquery-weui.min.css?version=${cfg.version}"/>