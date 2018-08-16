<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>购买记录</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="../common/css/nav.css" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
        <script type="text/javascript" src="js/act.js"></script>
	</head>

	<body>
		<div class="mainContainer">
			<div class="row menu">
				<div class="navigation">
					<div class="news-title">
						<ul class="news-module order_nav_ul clear">
							<li class="active" onclick="orderList();">全部</li>
							<li onclick="orderList('1')">待支付</li>
						</ul>
						<div class="news-slider"></div>
					</div>
				</div>
			</div>
			<div class="row content" id="orderList">

			</div>
		</div>

	<script id="orderListTemp" type="text/template">
		{@each data as item, index}
        <div class="row itemlist">
            <div class="row item">
            <div class="col-xs-4 col-md-4">
				{@if item.category == '2'}
            	<img src="\${item.course.cover}" class="cover" />
				{@else if item.category == '1'}
				<img src="\${item.counselor.imagePhotoUrl}" class="cover" />
				{@/if}
            </div>
            <div class="col-xs-8 col-md-8" style="padding-left: 0 !important;">
            <div class="row">
            <p class="title">\${item.commodityName}</p>
            </div>
            <div class="row">
				{@if item.category == '2'}
            	<p class="detail">\${item.course.objects}</p>
				{@else if item.category == '1'}
				<p class="detail"></p>
				{@/if}
        </div>
        <div class="row" style="padding-top: 0.2rem;">
			<span class="money">¥\${item.payMoney}</span>
				{@if item.category == '2'}
					<span class="history_money">¥\${item.course.primeCost}</span></div>
				{@/if}
        </div>
        </div>
        <div class="row order">
            <div class="col-xs-9 col-md-9"><span class="order_num">订单号：\${item.id}</span></div>
        <div class="col-xs-3 col-md-3">
			{@if item.payStatus == '1'}
			<span class="order_state">待支付</span>
			{@else if item.payStatus=='2'}
			<span class="order_state">已付款</span>
			{@else if item.payStatus=='3'}
			<span class="order_state">申请退款</span>
			{@else if item.payStatus=='4'}
			<span class="order_state">已退款</span>
			{@else if item.payStatus=='6'}
			<span class="order_state">结束/待评价</span>
			{@else if item.payStatus=='7'}
			<span class="order_state">已完结</span>
			{@else if item.payStatus=='8'}
			<span class="order_state">自动关闭</span>
			{@/if}
		</div>
        </div>
        </div>
		{@/each}
	</script>
	</body>
</html>