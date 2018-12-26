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
		<link rel="stylesheet" type="text/css" href="purchaseRecord/css/style.css" />
		<link rel="stylesheet" type="text/css" href="../common/css/nav.css" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jweixin-1.2.0.js"></script>
		<script type="text/javascript" src="purchaseRecord/js/act.js"></script>
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
        <div class="row itemlist" >
            <div class="row item">
            <div class="col-xs-4 col-md-4" onclick="showOrderDetail('\${item.id}');">
				{@if item.category == '2' && item.course != undefined}
            	<img src="\${item.course.cover}" class="cover" />
				{@else if item.category == '1'}
				<img src="\${item.counselor.imagePhotoUrl}" class="cover" />
				{@/if}
            </div>
            <div class="col-xs-8 col-md-8" style="padding-left: 0 !important;">
            <div class="row" onclick="showOrderDetail('\${item.id}');">
            <p class="title">\${item.commodityName}</p>
            </div>
            <div class="row">
				{@if item.category == '2' && item.course != undefined}
            	<p class="detail">
					{@if item.course.objects == '00'}
					<span>幼儿</span>
					{@else if item.course.objects == '01'}
					<span>小学</span>
					{@else if item.course.objects == '02'}
					<span>初中</span>
					{@else if item.course.objects == '03'}
					<span>高中</span>
					{@/if}
				</p>
				{@else if item.category == '1'}
				<p class="detail"></p>
				{@/if}
        </div>
        <div class="row" style="padding-top: 0.2rem;">
			<span class="money">¥\${item.payMoney}</span>
				{@if item.category == '2' && item.course != undefined}
					<span class="history_money">¥\${item.course.primeCost}</span></div>
				{@/if}
        </div>
        </div>
        <div class="row order">
            <div class="col-xs-9 col-md-9"><span class="order_num">下单时间：\${item.createDate}</span></div>
        <div class="col-xs-3 col-md-3">
			{@if item.payStatus == '1'}
			<span class="order_state"><span class="toPay" onclick="pay('\${item.id}','\${item.payMoney}')">去支付</span></span>
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