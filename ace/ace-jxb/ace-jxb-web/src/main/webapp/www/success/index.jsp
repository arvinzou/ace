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
		<jsp:include page="../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../common/js/loader.js"></script>
	</head>

	<body>
		<div class="container">
			<div class="row tips">
				<p>您已经成功预约肖海平老师，系统会通知ta确认订单并尽快联系你，请保持电话畅通~你也可以私信告诉TA哦~</p>
			</div>
		</div>
		<div class="box">
			<div class="row">
				<h3 class="title">肖海平老师将为您服务</h3></div>
			<div class="row">
				<div class="col-xs-3 col-sm-2 row_01">
					<img class="head_img" src="img/headImg.png" />
				</div>
				<div class="col-xs-6 col-sm-8 row_01">
					<div class="row consotor">
						<div class="col-xs-6 col-xs-6 consotor_01">肖海平</div>
						<div class="col-xs-6 col-xs-6 consotor_02"><img class="level" src="img/level.png" />5.0</div>
					</div>
					<div class="row introduce">
						<p>国家二级心理咨询师</p>
					</div>
				</div>
				<div class="col-xs-3 col-sm-2 row_01">
					<button class="chat">告诉TA</button>
				</div>
			</div>
			<div class="row dorder">
				<div class="col-xs-5 col-sm-5" style="padding-right: 0;"><span class="dorder_title">语音咨询：</span><span class="dorder_num">1</span></div>
				<div class="col-xs-5 col-sm-5" style="padding: 0;"><span class="dorder_title">支付金额：</span><span class="dorder_num">100</span></div>
				<div class="col-xs-2 col-sm-2"></div>
			</div>
			<div class="row dorder" style="border: none;">
				<div class="col-xs-9 col-sm-9">
					<p class="order_num">订单号：20180601955959</p>
				</div>
				<div class="col-xs-3 col-sm-3">
					<p class="order_state">待接单</p>
				</div>
			</div>
		</div>
		<div class="box">
			<div class="row">
				<h3 class="title">您的问题类型及描述</h3></div>
			<div class="row problem" style="margin-top: 0.3rem;margin-bottom: 0.3rem;">
				<ul>
					<li class="problem_label">儿童发展</li>
					<li class="problem_label">儿童发展</li>
				</ul>
			</div>
			<div class="row">
				<h4 class="sec_title">问题描述</h4>
			</div>
			<div class="row">
				<p class="sec_content">我的孩子不爱学习不爱吃饭不爱睡觉只爱吃肉还特别爱吃鸡腿，只爱玩游戏打王者荣耀吃鸡，还有谈恋爱，哈哈哈哈…怎么办呢？我是不是应该把他打一顿就好了.</p>
			</div>
		</div>
	</body>

</html>