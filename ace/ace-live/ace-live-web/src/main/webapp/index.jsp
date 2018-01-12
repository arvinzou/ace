<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%

Object o=session.getAttribute("SESSION_USERINFO_KEY");
out.println(o);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>live</title>
</head>
<body>
<script>
<!--
    var companyId='${param.companyId}';
    if(companyId&&companyId.length > 0){
        location.href="view/index.html?companyId=${param.companyId}";
    }else{
        alert("非法访问");
    }
-->

</script>
</body>
</html>