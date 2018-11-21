<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	session.invalidate();
	response.sendRedirect("/portal/dynamic/portal/security/login.jsp");
%>
<script type="text/javascript">
	if(sessionStorage){
         sessionStorage.clear();
    }
</script>