<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>editcell</title>

</head>

<jsp:include page="/dynamic/common/common.jsp" />


<body>
<input id="cc" name="dept" value="01">
	<input type="button" value="add" onclick="append()">
	<input type="text" name="itype" value="1"/>

	<table id="grid"></table>

	<jsp:include page="/dynamic/common/footer-1.jsp" />
	
	<script type="text/javascript" src="cellcombogrid.js?version=${cfg.version}"></script>
	<jsp:include page="/dynamic/common/footer-2.jsp" />
</body>
</html>