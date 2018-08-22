<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>咨询我的</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>
	<body>
		<div class="box">
			<div id="itemList">

			</div>
		</div>

		<script id="myorderTemp" type="text/template">
			{@each data as item,index}
            <div class="row roomlist" onclick="showDetail('\${item.id}');">
                <div class="row room">
					<div class="row">
						<div class="col-xs-4 col-sm-4 topsize">
							<div class="img_box"><img src="\${item.consumerImgUrl}"/></div>
						</div>
						<div class="col-xs-8 col-sm-8 topsize">
							<p class="workroom_title">\${item.consumerName}</p>
							<p class="workroom_detail">
								{@if item.consultProduct.type == '1'}
								<span class="edge">电话咨询 <span class="light">\${item.amount}次</span> </span>
								{@else if item.consultProduct.type == '2'}
								<span class="edge">视频咨询 <span class="light">\${item.amount}次</span></span>
								{@else if item.consultProduct.type == '3'}
								<span class="edge">面对面咨询<span class="light">\${item.amount}次</span></span>
								{@/if}
								<span class="edge">支付金额 <span class="light">¥\${item.payMoney}</span></span></p>
						</div>
					</div>
					<div class="row" style="padding-top: 0.2rem;padding-bottom: 0.2rem;border-top: 1px solid #F7F8FC;">
						<div class="col-xs-12 col-sm-12 consultTime">预约时间：\${item.consultOrder.reserveDate}</div>
					</div>
                </div>

			</div>
			{@/each}
		</script>
	</body>
</html>
