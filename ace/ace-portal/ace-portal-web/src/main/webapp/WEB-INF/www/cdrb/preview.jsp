<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@page import="com.huacainfo.ace.portal.web.tools.JsoupUtils"%>
<%
String url=request.getParameter("url");
String[] html=JsoupUtils.getHtml02(url);
%>
<html>
<head>
    <title><%=html[0]%></title>
</head>
<body>


<%=html[1]%>



<style type="text/css">
<!--
body {
	background-image: url();
	background-color: #F7FAFF;
	font-size: 25px;

}
body,td,th,prelink {font-size: 25px;}
.STYLE30 {color: #ea5f02; font-weight: bold; }
.STYLE31 {color: #EA5F02}
.xilan_content_tt{font-size:25px; line-height:40px; margin:auto; padding:10px;}
.bt1 {
	font-family: "宋体";
	font-size: 40px;
	font-style: normal;
	line-height: normal;
	font-weight: bold;
	font-variant: normal;
	color: #333333;
}
.bt3 {
	font-family: "宋体";
	font-size: 25px;
	font-style: normal;
	line-height: normal;
	font-weight: bold;
	font-variant: normal;
	color: #000000;
}
.bt2 {
	font-family: "宋体";
	font-size: 25px;
	font-style: normal;
	line-height: normal;
	font-weight: bold;
	font-variant: normal;
	color: #666666;
}
p{text-indent:2em;margin-bottom:5px;margin-top:5px;}
-->
</style>

</body>
</html>