<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>预约详情</title>
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
			<div class="row"><h3 class="title">预约咨询师</h3></div>
			<div class="row">
				<div class="col-xs-3 col-sm-2 row_01">
					<img class="head_img" src="\${data.counselor.imagePhotoUrl}" />
				</div>
				<div class="col-xs-6 col-sm-8 row_01">
					<div class="row consotor">
						<div class="col-xs-6 col-xs-6 consotor_01">\${data.counselor.name}</div>
						<div class="col-xs-6 col-xs-6 consotor_02"><img class="level" src="img/level.png" />\${data.counselor.level}</div>
					</div>
					<div class="row introduce">
						<p>\${\${data.counselor.certification}}</p>
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
				<div class="col-xs-8 col-sm-8 formcmt">
					{@if data.consultProduct.type == '1'}
					<p>电话咨询</p>
					{@else if consultProduct.type == '2'}
					<p>视频咨询</p>
					{@else}
					<p>面对面咨询</p>
					{@/if}
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">咨询次数：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${parseInt(data.payMoney) / parseInt(data.price)}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">服务时间：</div>
				<div class="col-xs-8 col-sm-8 formcmt">2018-07-19</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">支付金额：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.payMoney}</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">基本信息</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">姓名：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.consultOrder.name}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">年龄：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.consultOrder.age}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">性别：</div>
				<div class="col-xs-8 col-sm-8 formcmt">
					{@if data.consultOrder.sex == '1'}
					<p>男</p>
					{@else if data.consultOrder.sex == '2'}
					<p>女</p>
					{@/if}
				</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">联系方式：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.consultOrder.tel}</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">您的问题类型及描述</h3></div>
			<div class="row problem" style="margin-top: 0.3rem;margin-bottom: 0.3rem;">
				<ul>
					{@each data.consultOrder.tags.split(',') as tag,num}
					<li class="problem_label">\${tag}</li>
					{@/each}
				</ul>
			</div>
		</div>
		<div class="box">
			<div class="row">
				<h4 class="sec_title">问题描述</h4>
			</div>
			<div class="row">
				<p class="sec_content">
					\${data.consultOrder.info}
				</p>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">紧急联系人</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">姓名：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.consultOrder.secName}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">关系：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.consultOrder.relationship}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">联系方式：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.consultOrder.secTel}</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">订单信息</h3></div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">下单时间：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.createDate}</div>
			</div>
			<div class="row">
				<div class="col-xs-4 col-sm-4 formtitle">订单编号：</div>
				<div class="col-xs-8 col-sm-8 formcmt">\${data.id}</div>
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
