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
					{@if data.counselor.imagePhotoUrl!=null && data.counselor.imagePhotoUrl!=undefined}
					<span class="img_box"><img src="\${data.counselor.imagePhotoUrl}"/></span>
					{@else}
					<span class="img_box"><img src="\${data.headimgurl}"/></span>
					{@/if}
				</div>
				<div class="col-xs-7 col-sm-7" style="padding-left: 0!important;">
					<div class="row">
						<div class="col-xs-5 col-sm-5" style="padding: 0;"><p class="title01">\${data.counselor.name}</p></div>
						<div class="col-xs-7 col-sm-7">

						</div>
					</div>
					<div class="row"><p class="content">\${data.counselor.certification}</p></div>
				</div>
				<div class="col-xs-3 col-sm-3">
					<span class="myindex" onclick="loadMyInfo('\${data.counselor.id}');">个人主页</span>
				</div>
			</div>
			<div class="row">
				<div class="banner">
					<div class="row" style="padding-top: 0.826666rem;"><p class="title02">累计总收益（元）</p></div>
					<div class="row">
						{@if data.counselor.amount == undefined || data.counselor.amount == null}
						<p class="statics">0.00</p>
						{@else}
						<p class="statics">\${data.counselor.amount}</p>
						{@/if}
					</div>
					<%--<div class="row"><p class="title02">账户余额：
						{@if data.counselor.income == undefined || data.counselor.income== null}
							0.00
						{@else}
							\${data.counselor.income}
						{@/if}
						元</p></div>--%>
				</div>
			</div>
			<div class="row statics_detail">
				<div class="col-xs-4 sol-sm-4">
					<div class="row statics_num">\${data.statistic.consultCount}</div>
					<div class="row statics_title">咨询</div>
				</div>
				<div class="col-xs-4 sol-sm-4">
					<div class="row statics_num">\${data.statistic.courseCount}</div>
					<div class="row statics_title">课程</div>
				</div>
				<%--<div class="col-xs-2 sol-sm-2">--%>
					<%--<div class="row statics_num">\${data.statistic.liveCount}</div>--%>
					<%--<div class="row statics_title">直播</div>--%>
				<%--</div>--%>
				<%--<div class="col-xs-2 sol-sm-2">--%>
					<%--<div class="row statics_num">\${data.statistic.teacherCount}</div>--%>
					<%--<div class="row statics_title">老师</div>--%>
				<%--</div>--%>
				<div class="col-xs-4 sol-sm-4">
					<div class="row statics_num">\${data.statistic.parentCount}</div>
					<div class="row statics_title">会员</div>
				</div>
			</div>
		</script>
		<script id="signInfo" type="text/template">
            本月已签到
            \${signCount}
			天
		</script>
		<div class="box">
			<div class="row menu-li">
				<div class="col-xs-3 col-sm-3" style="padding-right: 0 !important;"><p class="menu_title">每日签到</p></div>
				<div class="col-xs-5 col-sm-5" style="padding-left: 0 !important; padding-right: 0 !important;"><p class="sign_info"></p></div>
				<div class="col-xs-4 col-sm-4"><span id="signIn" class="sign_btn" onclick="sign();">立即签到</span></div>
			</div>
			<div class="row menu-li">
				<div class="col-xs-9 col-sm-9"><p class="menu_title">是否在线</p></div>
				<div class="col-xs-3 col-sm-3" id="onOrOff">

				</div>
			</div>
			<div class="row menu-li" onclick="showMycount();">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">我的账户</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title" onclick="myStudio();">我的工作室</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
			<div class="row menu-li" onclick="showMyOrder();">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">咨询我的</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>
		<%--	<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">入驻与认证</p></div>
				<div class="col-xs-2 col-sm-2"><img class="next" src="img/next.png"/></div>
			</div>--%>
			<div class="row menu-li">
				<div class="col-xs-10 col-sm-10"><p class="menu_title">联系我们</p></div>
				<div class="col-xs-2 col-sm-2"><a href="tel:0736-7083862"><img class="next" src="img/next.png"/></a></div>
			</div>
		</div>

		<script id="onOffTemp" type="text/template">
			{@if counselor.onlineStatus == '1'}
			<img id="offline" src="img/switch_on.png" style="width: 1.346666rem;height: 0.64rem;" onclick="switchOffline();"/>
			{@else}
			<img id="offline" src="img/switch_off.png" style="width: 1.346666rem;height: 0.64rem;" onclick="switchOffline();"/>
			{@/if}
		</script>
	</body>
</html>
