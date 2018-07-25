<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>预约详情</title>
		<jsp:include page="../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/consultDetail.js"></script>
		<script type="text/javascript" src="../common/js/loader.js"></script>
	</head>
	<body>
		<div class="box">
			<div class="row"><h3 class="title">预约咨询师</h3></div>
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
					<button class="chat" onclick="window.location.href='chat.jsp'">聊一聊</button>
				</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">咨询详情</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">咨询方式：</div>
				<div class="col-xs-8 col-sm-8 formcmt">语音咨询</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">咨询次数：</div>
				<div class="col-xs-8 col-sm-8 formcmt">1</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">服务时间：</div>
				<div class="col-xs-8 col-sm-8 formcmt">2018-07-19</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">支付金额：</div>
				<div class="col-xs-8 col-sm-8 formcmt">¥100</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">基本信息</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">姓名：</div>
				<div class="col-xs-8 col-sm-8 formcmt">武琼</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">年龄：</div>
				<div class="col-xs-8 col-sm-8 formcmt">3</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">性别：</div>
				<div class="col-xs-8 col-sm-8 formcmt">女</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">联系方式：</div>
				<div class="col-xs-8 col-sm-8 formcmt">13719064337</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">您的问题类型及描述</h3></div>
			<div class="row problem" style="margin-top: 0.3rem;margin-bottom: 0.3rem;">
				<ul>
					<li class="problem_label">儿童发展</li>
					<li class="problem_label">儿童发展</li>
				</ul>
			</div>
		</div>
		<div class="box">
			<div class="row">
				<h4 class="sec_title">问题描述</h4>
			</div>
			<div class="row">
				<p class="sec_content">我的孩子不爱学习不爱吃饭不爱睡觉只爱吃肉还特别爱吃鸡腿，只爱玩游戏打王者荣耀吃鸡，还有谈恋爱，哈哈哈哈…怎么办呢？我是不是应该把他打一顿就好了.</p>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">紧急联系人</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">姓名：</div>
				<div class="col-xs-8 col-sm-8 formcmt">武琼</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">关系：</div>
				<div class="col-xs-8 col-sm-8 formcmt">朋友</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">联系方式：</div>
				<div class="col-xs-8 col-sm-8 formcmt">13719064337</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">订单信息</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">下单时间：</div>
				<div class="col-xs-8 col-sm-8 formcmt">2018-07-19 14:22:30</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">订单编号：</div>
				<div class="col-xs-8 col-sm-8 formcmt">2018061955959</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">支付状态：</div>
				<div class="col-xs-8 col-sm-8 formcmt">已支付</div>
			</div>
		</div>
	</body>
</html>
