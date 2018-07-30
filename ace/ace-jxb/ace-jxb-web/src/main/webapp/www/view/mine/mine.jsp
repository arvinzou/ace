<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>个人中心</title>
		<link rel="stylesheet" type="text/css" href="css/mine.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/mine.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>
	<body>

		<div class="box" id="mine">

		</div>
		<script id="myInfo" type="text/template">
			<div class="row"  style="padding-top: 0.533333rem;">
				<div class="col-xs-2 col-sm-2">
					<span class="img_box"><img src="\${data.headimgurl}"/></span>
				</div>
				<div class="col-xs-7 col-sm-7">
					<div class="row">
						<div class="col-xs-5 col-sm-5" style="padding: 0;"><p class="title01">\${data.counselor.name}</p></div>
						<div class="col-xs-7 col-sm-7">
							<img class="level" src="img/level.png"/>
							<img class="level" src="img/level.png"/>
							<img class="level" src="img/level.png"/>
						</div>
					</div>
					<div class="row"><p class="content">\${data.counselor.certification}</p></div>
				</div>
				<div class="col-xs-3 col-sm-3">
					<span class="myindex">个人主页</span>
				</div>
			</div>
			<div class="row">
				<div class="banner">
					<div class="row" style="padding-top: 0.826666rem;"><p class="title02">累计总收益（元）</p></div>
					<div class="row"><p class="statics">\${data.counselor.income}</p></div>
					<div class="row"><p class="title02">本月累计收益：2000元</p></div>
				</div>
			</div>
			<div class="row statics_detail">
				<div class="col-xs-3 sol-sm-3">
					<div class="row statics_num">\${data.statistic.consultCount}</div>
					<div class="row statics_title">咨询</div>
				</div>
				<div class="col-xs-2 sol-sm-2">
					<div class="row statics_num">\${data.statistic.courseCount}</div>
					<div class="row statics_title">课程</div>
				</div>
				<div class="col-xs-2 sol-sm-2">
					<div class="row statics_num">\${data.statistic.liveCount}</div>
					<div class="row statics_title">直播</div>
				</div>
				<div class="col-xs-2 sol-sm-2">
					<div class="row statics_num">\${data.statistic.teacherCount}</div>
					<div class="row statics_title">老师</div>
				</div>
				<div class="col-xs-3 sol-sm-3">
					<div class="row statics_num">\${data.statistic.parentCount}</div>
					<div class="row statics_title">会员</div>
				</div>
			</div>
		</script>
		<div class="box">
			<div class="row menu-li">
				<div class="col-xs-3 col-sm-3"><p class="menu_title">每日签到</p></div>
				<div class="col-xs-5 col-sm-5"><p class="sign_info">本月已签到10天</p></div>
				<div class="col-xs-4 col-sm-4"><span class="sign_btn">立即签到</span></div>
			</div>
			<div class="row menu-li" onclick="window.location.href='mycount.jsp'">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">我的账户</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title" onclick="window.location.href='../workroom/index.jsp'">我的工作室</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">邀请老师/好友</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">入驻与认证</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">联系我们</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
		</div>
	</body>
</html>
