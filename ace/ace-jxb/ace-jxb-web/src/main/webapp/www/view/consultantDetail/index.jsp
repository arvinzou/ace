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
						<p>\${data.counselor.certification}</p>
					</div>
				</div>
				<div class="col-xs-3 col-sm-2 row_01">
					<%--<button class="chat" onclick="window.location.href='chat.jsp'">聊一聊</button>--%>
				</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">咨询详情</h3></div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">咨询方式：</span>
					<span class="formcmt">
						{@if data.consultProduct.type == '1'}
							电话咨询
						{@else if data.consultProduct.type == '2'}
							视频咨询
						{@else if data.consultProduct.type == '3'}
							面对面咨询
						{@/if}
					</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">咨询次数：</span>
					<span class="formcmt">\${data.amount}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">预约时间：</span>
					<span class="formcmt">\${data.consultOrder.reserveDate}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">支付金额：</span>
					<span class="formcmt">\${data.payMoney}</span>
				</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">基本信息</h3></div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">姓名：</span>
					<span class="formcmt">\${data.consultOrder.name}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">年龄：</span>
					<span class="formcmt">\${data.consultOrder.age}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">性别：</span>
					<span class="formcmt">
						{@if data.consultOrder.sex == '1'}
							男
						{@else if data.consultOrder.sex == '2'}
							女
						{@/if}
					</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">联系方式：</span>
					<span class="formcmt">\${data.consultOrder.tel}</span>
				</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">您的问题类型及描述</h3></div>
			<div class="row problem" style="margin-top: 0.3rem;margin-bottom: 0.3rem;">
				<ul>
					{@if data.consultOrder.tags != undefined &&  data.consultOrder.tags!= null && data.consultOrder.tags!=''}
					{@each data.consultOrder.tags.split(',') as tag,num}
					<li class="problem_label">\${tag}</li>
					{@/each}
					{@/if}
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
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">姓名：</span>
					<span class="formcmt">\${data.consultOrder.secName}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">关系：</span>
					<span class="formcmt">\${data.consultOrder.relationship}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">联系方式：</span>
					<span class="formcmt">\${data.consultOrder.secTel}</span>
				</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">订单信息</h3></div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">下单时间：</span>
					<span class="formcmt">\${data.createDate}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">订单编号：</span>
					<span class="formcmt">\${data.id}</span>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">支付状态：</span>
					<span class="formcmt">
						{@if data.payStatus == '1'}
						<span class="order_state">待支付</span>
						{@else if data.payStatus == '2'}
						<span class="order_state">已付款(待接单)</span>
						{@else if data.payStatus == '3'}
						<span class="order_state">申请退款</span>
						{@else if data.payStatus == '4'}
						<span class="order_state">已退款</span>
						{@else if data.payStatus == '6'}
						<span class="order_state">结束/待评价</span>
						{@else if data.payStatus == '7'}
						<span class="order_state">已完结</span>
						{@else}
						<span class="order_state">自动关闭</span>
						{@/if}
						</span>
				</div>
			</div>
		</div>

	</script>
	</body>
</html>
