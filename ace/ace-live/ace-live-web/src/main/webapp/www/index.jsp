<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>live</title>
</head>
<body>
<%

Object o=session.getAttribute("SESSION_USERINFO_KEY");
out.println(o);
%>
</body>
</html>