<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  


<head>  
    <title>Insert title here</title>  
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>  
</head> 
<jsp:include page="/dynamic/common/common.jsp" /> 

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/css/ace-ui-jqgrid-treegird-custom.css?version=${cfg.version}" />
<body style="margin-left:20px">  
<table id="treegrid"></table>

<jsp:include page="/dynamic/common/footer-1.jsp" />

<script type="text/javascript" src="cell2.js?version=${cfg.version}"></script>
<jsp:include page="/dynamic/common/footer-2.jsp" />
</body>  


</html>  