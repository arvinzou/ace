<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>课程订单详情</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>
	<body>
	<div class="index" id="index">

	</div>
	<script id="dataTemp" type="text/template">
		<div class="box">
			<div class="row"><h3 class="title">课程订单详情</h3></div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">课程详情</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">
					<img class="head_img" src="\${data.course.cover}" />
				</div>
				<div class="col-xs-8 col-sm-8 formcmt">课程名称：\${data.course.name}</div>
			</div>

		</div>
		<div class="box">
			<div class="row"><h3 class="title">订单信息</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">支付金额：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.course.cost}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">下单时间：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.createDate}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">订单编号：</div>
				<div class="col-xs-8 col-sm-8 formcmt"><p>\${data.id}</p></div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">支付状态：</div>
				<div class="col-xs-8 col-sm-8 formcmt">
					{@if data.payStatus == '1'}
					<p class="order_state">待支付</p>
					{@else if data.payStatus == '2'}
					<p class="order_state">已付款(待接单)</p>
					{@else if data.payStatus == '3'}
					<p class="order_state">申请退款</p>
					{@else if data.payStatus == '4'}
					<p class="order_state">已退款</p>
					{@else if data.payStatus == '6'}
					<p class="order_state">结束/待评价</p>
					{@else if data.payStatus == '7'}
					<p class="order_state">已完结</p>
					{@else}
					<p class="order_state">自动关闭</p>
					{@/if}
				</div>
			</div>
		</div>
	</script>
	</body>
</html>
