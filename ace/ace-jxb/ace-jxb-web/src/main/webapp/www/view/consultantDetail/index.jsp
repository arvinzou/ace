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
						<%--<div class="col-xs-6 col-xs-6 consotor_02"><img class="level" src="img/level.png" />\${data.counselor.level}</div>--%>
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
			{@if data.consultProduct.type == '3'}
			<div class="row">
				<div class="col-xs-12 col-sm-12">
					<span class="formtitle">咨询地点：</span>
					<span class="formcmt">荷花社区三楼咨询室</span>
				</div>
			</div>
			{@/if}
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
					<span class="formcmt"><a href="tel:\${data.consultOrder.tel}">\${data.consultOrder.tel}</a><img src="data:image/png;base64,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"></span>
				</div>
			</div>
		</div>
		<div class="box">
			<div class="row"><h3 class="title">您的问题类型及描述</h3></div>
			<div class="row problem" style="margin-top: 0.3rem;margin-bottom: 0.3rem;" data-id="\${data.consultOrder.tags}">
				{@if data.consultOrder.tags}
				<ul>
					{@each data.consultOrder.tags.split(',') as tag,num}
					{@if tag!='' && tag.length>0}
					<li class="problem_label">\${tag}</li>
					{@/if}
					{@/each}
				</ul>
				{@/if}
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
					<span class="formcmt"><a
							href="tel:\${data.consultOrder.secTel}">\${data.consultOrder.secTel}</a><img src="data:image/png;base64,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"></span>
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
						<span class="order_state">已付款</span>
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
