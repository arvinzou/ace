<%@page import="com.huacainfo.ace.common.tools.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html >
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>基本信息</title>
</head>
<script type="text/javascript">
	var id = '${param.id}';
</script>
<jsp:include page="../../common/common.jsp" />
<body>
	<div class="page-content">
    <div><center id="name" ></center></div>
    <div><span id="content"></span></div>
    </div>
	<jsp:include page="../../common/footer-1.jsp" />
	<jsp:include page="../../common/footer-2.jsp" />
	<script>
	$.ajax({
		type : "get",
		url : contextPath + "/message/selectByPrimaryKey.do",
		data:{id:id},
		beforeSend : function(XMLHttpRequest) {
		},
		success : function(rst, textStatus) {
			 $("#name").html(rst.value.name);
			 $("#content").html(rst.value.content);
		},
		complete : function(XMLHttpRequest, textStatus) {
			
		},
		error : function() {
		}
	});
	</script>
</body>
</html>