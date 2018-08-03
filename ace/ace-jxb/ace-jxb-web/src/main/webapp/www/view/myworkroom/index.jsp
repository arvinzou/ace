<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>我的工作室</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
        <jsp:include page="../../../dynamic/common/base.jsp" />
        <script type="text/javascript" src="../../common/js/loader.js"></script>
		<script type="text/javascript" src="js/act.js"></script>
	</head>

	<body>
		<div class="box">
			<div class="row">
				<div class="slide_banner">
					<div id="myCarousel" class="carousel slide" data-ride="carousel" style="width: 100%;height: 100%;">
						<!-- 轮播（Carousel）指标 -->
						<ol class="carousel-indicators" style="bottom:30px !important;">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<li data-target="#myCarousel" data-slide-to="1"></li>
							<li data-target="#myCarousel" data-slide-to="2"></li>
						</ol>
						<!-- 轮播（Carousel）项目 -->
						<div class="carousel-inner" style="width: 100%;height: 100%;">
							<div class="item active" style="width: 100%;height: 100%;">
								<img src="img/banner1.png" alt="First slide" style="width: 100%;height: 100%;">
							</div>
							<div class="item" style="width: 100%;height: 100%;">
								<img src="img/banner2.png" alt="Second slide" style="width: 100%;height: 100%;">
							</div>
							<div class="item" style="width: 100%;height: 100%;">
								<img src="img/banner1.png" alt="Third slide" style="width: 100%;height: 100%;">
							</div>
						</div>
					</div>
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
		<div class="footer"></div>
		
		<!--工作室详情-->
		<div id="workroomDetail" style="display: none;">
			<p>心阳光团队集结行业内大量优质咨询师——近心帮协会的优秀团队，高歌进军家庭教育市场，致力于打造家庭贴身家心阳光团队集结行业内大量优质咨询师——近心帮协会的优秀团队，高歌进军家庭教育市场，致力于打造家庭贴心阳光团队集结行业内大量优质咨询师——近心帮协会的优秀团队，高歌进军家庭教育市场，致力于打造家庭贴心阳光团队集结行业内大量优质咨询师——近心帮协会的优秀团队，高歌进军家庭教育市场，致力于打造家庭贴心阳光团队集结行业内大量优质咨询师——近心帮协会的优秀团队，高歌进军家庭教育市场，致力于打造家庭贴身家心阳光团队集结行业内大量优质咨询师——近的优秀团队，高歌进军家庭教育市场打造家庭贴</p>
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
				<span class="introduce">简介：</span> \${roomContent.introduce}
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

		<script id="footerTemp" type="text/template">
			<p onclick="invate('\${footer.id}');">邀请老师加入</p>
		</script>
	</body>
</html>