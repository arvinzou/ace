<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>预约成功</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>

	<body>
		<div id="box">

		</div>
	<script id="orderDetailTemp" type="text/template">
		<div class="container">
			<div class="row tips" style="margin-left: 0!important;margin-right: 0 !important;">
				<p>您已经成功预约 \${data.counselor.name}老师，系统会通知ta并尽快联系你，请保持电话畅通哦~</p>
			</div>
		</div>
		<div class="box">
			<div class="row">
				<h3 class="title">\${data.counselor.name}老师将为您服务</h3></div>
			<div class="row">
				<div class="col-xs-3 col-sm-2 row_01">
					<img class="head_img" src="\${data.counselor.imagePhotoUrl}" />
				</div>
				<div class="col-xs-6 col-sm-8 row_01">
					<div class="row consotor">
						<div class="col-xs-6 col-xs-6 consotor_01">\${data.counselor.name}</div>
						<%--<div class="col-xs-6 col-xs-6 consotor_02"><img class="level" src="img/level.png" />\${data.counselor.level}</div>--%>
					</div>
					<div class="row introduce">
						<p>\${data.counselor.certification}</p>
					</div>
				</div>
				<div class="col-xs-3 col-sm-2 row_01">
					<%--<button class="chat">告诉TA</button>--%>
				</div>
			</div>
			<div class="row dorder" id="orderDetail">
				<div class="col-xs-5 col-sm-5" style="padding-right: 0;">
					{@if data.consultProduct.type == '1'}
					<span class="dorder_title">电话咨询：</span><span class="dorder_num">\${data.amount}</span>
					{@else if data.consultProduct.type == '2'}
					<span class="dorder_title">视频咨询：</span><span class="dorder_num">\${data.amount}</span>
					{@else if data.consultProduct.type == '3'}
					<span class="dorder_title">面对面咨询：</span><span class="dorder_num">\${data.amount}</span>
					{@/if}
				</div>
				<div class="col-xs-5 col-sm-5" style="padding: 0;"><span class="dorder_title">支付金额：</span><span class="dorder_num">\${data.payMoney}</span></div>
				<div class="col-xs-2 col-sm-2"></div>
			</div>
			<div class="row" style="border: none;" id="orderNo">
				<div class="col-xs-12 col-sm-12"><a class="order_num" onclick="showOrderDetail('\${data.id}')">订单号：\${data.id}</a></div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12" style="padding-top: 0.2rem;text-indent: 4em;">
					{@if data.payStatus == '1'}
					<p class="order_state">待支付</p>
					{@else if data.payStatus == '2'}
					<p class="order_state">已付款</p>
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
		</div>
		<div class="box">
			<div class="row">
				<h3 class="title">您的问题类型及描述</h3></div>
			<div class="row problem" style="margin-top: 0.3rem;margin-bottom: 0.3rem;">
				<ul>
					{@if data.consultOrder.tags != undefined &&  data.consultOrder.tags!= null}
					{@each data.consultOrder.tags.split(',') as tag,num}
					<li class="problem_label">\${tag}</li>
					{@/each}
					{@/if}
				</ul>
			</div>
			<div class="row">
				<h4 class="sec_title">问题描述</h4>
			</div>
			<div class="row">
				<p class="sec_content">
					\${data.consultOrder.info}
				</p>
			</div>
		</div>
	</script>
	</body>

</html>