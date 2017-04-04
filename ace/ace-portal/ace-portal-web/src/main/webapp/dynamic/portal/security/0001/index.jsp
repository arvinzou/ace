<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Map<String,Object> p=new HashMap<String,Object>();
p.put("sys_login_bg_img", "/content/portal/images/LOGIN2880-760.png");
p.put("sys_name", "深圳市道路运输行业协会政企服务平台");
p.put("sys_dept_id", "0001");
session.setAttribute("cfg", p);
RequestDispatcher dispatcher = request.getRequestDispatcher("../login.jsp");
dispatcher .forward(request, response);
%>