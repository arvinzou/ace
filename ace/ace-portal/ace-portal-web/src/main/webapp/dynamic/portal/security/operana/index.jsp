<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Map<String,Object> p=new HashMap<String,Object>();
p.put("sys_login_bg_img", "/portal/dynamic/portal/security/operana/login.png");
p.put("sys_name", "电装运营");
session.setAttribute("cfg", p);
RequestDispatcher dispatcher = request.getRequestDispatcher("../login.jsp");
dispatcher .forward(request, response);
%>