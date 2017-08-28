<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Map<String,Object> p=new HashMap<String,Object>();
p.put("sys_login_bg_img", "/content/portal/images/login-bg.png");
p.put("sys_name", " 深圳市自卸车协会行业公共服务平台");
p.put("sys_dept_id", "00010004");
session.setAttribute("cfg", p);
RequestDispatcher dispatcher = request.getRequestDispatcher("../reg/index.jsp");
dispatcher .forward(request, response);
%>