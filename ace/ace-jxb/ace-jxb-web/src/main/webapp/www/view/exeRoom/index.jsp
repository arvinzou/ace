<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>优秀工作室详情</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
        <jsp:include page="../../../dynamic/common/base.jsp" />
        <script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>

	<body>
		<div class="box">
			<div class="row">
				<div class="slide_banner" id="bannerslide">

				</div>
				<div class="room_info">

				</div>
			</div>
			<div class="row">
				<div class="content" id="roomContent">

				</div>
			</div>
			<div class="row" style="margin-top: 0.8rem;">
				<div class="content" id="memberList">

				</div>
			</div>
			<div class="row item01">
				<div class="col-xs-3 col-sm-3">
					<p class="statics">824</p>
					<p class="title">成员</p>
				</div>
				<div class="col-xs-3 col-sm-3">
					<p class="statics">123</p>
					<p class="title">咨询</p>
				</div>
				<div class="col-xs-3 col-sm-3">
					<p class="statics">555</p>
					<p class="title">课程</p>
				</div>
				<div class="col-xs-3 col-sm-3">
					<p class="statics">1314</p>
					<p class="title">直播</p>
				</div>
			</div>
		</div>
		
		<!--工作室详情-->
		<div id="workroomDetail" style="display: none;">

		</div>

		<script id="roomInfoTemp" type="text/template">
            <div class="col-xs-3 col-sm-3">
                <img class="head" src="\${roombase.logoImgUrl}" />
                </div>
                <div class="col-xs-9 col-sm-9">
                <p class="room_title">\${roombase.name}</p>
                <p class="room_title01">帮助过<span class="num">228</span>人</p>
            </div>
		</script>
		<script id="roomContentTemp" type="text/template">
			<p>
				<span class="introduce">简介：</span> \$\${roomContent.introduce}
			</p>
		</script>

		<script id="memberListTemp" type="text/template">
			<div class="col-xs-5 col-sm-5" style="padding-left: 0;">
				<p class="member">\${member.memberList.length}个咨询师成员</p>
				<p class="mystate">你已加入</p>
			</div>
			<div class="col-xs-5 col-sm-5">
				{@each member.memberList.slice(0,4) as item,index}
				{@if index == 0}
				<img class="member_head" src="\${item.imagePhotoUrl}" />
				{@else}
				<img class="member_head overlay" src="\${item.imagePhotoUrl}" />
				{@/if}
				{@/each}
			</div>
			<div class="col-xs-2 col-sm-2 more" onclick="showMember('\${member.id}')">
				...
			</div>
		</script>

		<script id="bannerSlideTemp"  type="text/template">
				<div id="myCarousel" class="carousel slide" data-ride="carousel" style="width: 100%;height: 100%;">
					<!-- 轮播（Carousel）指标 -->
					<ol class="carousel-indicators" style="bottom:30px !important;">
						{@each slide as item,index}
						{@if index == 0}
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						{@else if index == 1}
						<li data-target="#myCarousel" data-slide-to="1"></li>
						{@else if index == 2}
						<li data-target="#myCarousel" data-slide-to="2"></li>
						{@/if}
						{@/each}
					</ol>
					<!-- 轮播（Carousel）项目 -->
					<div class="carousel-inner" style="width: 100%;height: 100%;" >
						{@each slide as banner, index}
						{@if index == 0}
						<div class="item active" style="width: 100%;height: 100%;">
							<img src="\${banner.imgUrl}" alt="First slide" style="width: 100%;height: 100%;">
						</div>
						{@else if index == 1}
						<div class="item" style="width: 100%;height: 100%;">
							<img src="\${banner.imgUrl}" alt="Second slide" style="width: 100%;height: 100%;">
						</div>
						{@else if index == 2}
						<div class="item" style="width: 100%;height: 100%;">
							<img src="\${banner.imgUrl}" alt="Third slide" style="width: 100%;height: 100%;">
						</div>
						{@/if}
						{@/each}
					</div>
				</div>
		</script>

	<script id="workroomDetailTemp"  type="text/template">
		<div>
			\$\${detail}
		</div>
	</script>
	</body>
</html>