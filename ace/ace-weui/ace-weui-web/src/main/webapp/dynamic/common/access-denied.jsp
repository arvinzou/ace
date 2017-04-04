<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% 
 if(request.getRequestURI().indexOf("jsp")!=-1){
%>
	 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	 <html>
	 <head>
	 <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	 <title>access-denied</title>
	 </head>
	 <body>
	 <h3>access-denied</h3>
	 </body>
	 </html>
<% 
 }else{
	 
	 response.setContentType("application/json;charset=UTF-8");
%>
	{state:false,errorMessage:'access-denied'}
<%
 }
%>
