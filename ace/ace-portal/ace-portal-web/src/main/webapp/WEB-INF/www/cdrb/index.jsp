<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@page import="com.huacainfo.ace.portal.web.tools.JsoupUtils"%>
<%@page import="com.huacainfo.ace.common.tools.*"%>
<%@page import="java.util.*"%>
<%
int pages=0;
String p=request.getParameter("p");
if(CommonUtils.isBlank(p)){
pages=0;
}else{
    pages=Integer.parseInt(p);
}
List<Map<String,String>> list=JsoupUtils.getUrlList();
Map<String,String> index=list.get(pages);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="user-scalable=no">
    <jsp:include page="../../dynamic/common/common-www.jsp" />
<title><%=index.get("title")%></title>
    <script>
         var pages=<%= pages%>;
         var total=<%= list.size()%>;
    </script>
</head>
<body ontouchstart>
<div class="weui-pull-to-refresh__layer">
    <div class='weui-pull-to-refresh__arrow'></div>
    <div class='weui-pull-to-refresh__preloader'></div>
    <div class="down">下拉刷新</div>
    <div class="up">释放刷新</div>
    <div class="refresh">正在刷新</div>
</div>
<div>
 <%=JsoupUtils.getPageBody(index.get("htm"),"A"+pages)%>
</div>
<footer>
   <h2 style="text-align:center"><%=index.get("title")%> </h2>
</footer>
<jsp:include page="../../dynamic/common/footer-1-www.jsp" />
<script src="${pageContext.request.contextPath}/content/common/weui/fastclick.js"></script>
</body>
</html>