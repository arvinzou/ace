<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>消费信息</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
	<body>
		<div class="index">
			<div class="banner" id="count">

			</div>
			<div class="title">刷卡记录</div>
			<div class="list" id="list">

			</div>
		</div>

        <script id="count-tpl" type="text/template">
			{@if data.lCardNo}
            <div class="cardNo">卡号: \${data.lCardNo}
            </div>
            <div class="balance">
                {@if data.acctNum}
                \${data.acctNum}
                {@else}
                0
                {@/if}
            </div>
            <div class="balance-title">累计刷卡次数</div>
			{@else}
			<div class="balance">
				没有记录
			</div>
			{@/if}
        </script>

        <script id="list-tpl" type="text/template">
            {@each data as item, index}
            <div class="item">
                <div class="icon"><img src="img/icon_consume.png"/></div>
                <div class="record">\${item.dealTime}</div>
            </div>
            {@/each}
        </script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
		<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
		<script type="text/javascript" src="js/student.js"></script>
	</body>
</html>
