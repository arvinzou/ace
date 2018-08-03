<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>我的咨询</title>
		<link rel="stylesheet" type="text/css" href="../common/css/nav.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>
	<body>
		<div class="nav_box">
			<div class="row" style="height: 1.5rem;">
				 <div class="navigation">
			        <div class="news-title">
			            <ul class="news-module project_nav_ul clear">
			                <li class="active" onclick="orderList();">全部</li>
			                <li onclick="orderList('1');">待支付</li>
			                <li onclick="orderList('2,3,6');">待完成</li>
			                <li onclick="orderList('4,7');">已完成</li>
			            </ul>
			            <div class="news-slider"></div>
			        </div>
			    </div>
 		</div>
		<div class="container" id="orderList">

		</div>

		<script id="orderListTemp"  type="text/template">
            <div class="row">
                <div class="col-xs-3 col-sm-2 row_01">
                <img class="head_img" src="img/headImg.png" />
                </div>
                <div class="col-xs-6 col-sm-8 row_01">
                <div class="row consotor">
                <div class="col-xs-6 col-xs-6 consotor_01">肖海平</div>
                <div class="col-xs-6 col-xs-6 consotor_02"></div>
                </div>
                <div class="row introduce">
                <p>国家二级心理咨询师</p>
                </div>
                </div>
                <div class="col-xs-3 col-sm-2 row_01">
                <button class="chat">查看详情</button>
                </div>
                </div>
                <div class="row dorder">
                <div class="col-xs-5 col-sm-5" style="padding-right: 0;"><span class="dorder_title">语音咨询：</span><span class="dorder_num">1</span></div>
            <div class="col-xs-5 col-sm-5" style="padding: 0;"><span class="dorder_title">支付金额：</span><span class="dorder_num">100</span></div>
            <div class="col-xs-2 col-sm-2"></div>
                </div>
                <div class="row dorder" style="border: none;">
                <div class="col-xs-7 col-sm-7"><p class="order_num">订单号：20180601955959</p></div>
            	<div class="col-xs-5 col-sm-5" style="text-align: right;"><p class="unfinished">待接单</p></div>
            </div>
		</script>
		</div>
	</body>
</html>
