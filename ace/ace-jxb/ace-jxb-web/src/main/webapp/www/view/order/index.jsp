<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>预约咨询</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<link rel="stylesheet" type="text/css" href="../../common/js/datetime/css/index.css"/>
		<link rel="stylesheet" type="text/css" href="../../common/js/datetime/LCalendar/css/LCalendar.css"/>
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>

	<body>
		<div class="main_box">
			<div class="container">
				<div class="row" id="consultorInfo"></div>
				<div class="row content_03">
					<div class="col-xs-4 col-sm-4" style="padding-left: 0;">
						<span class="method_01">预约时间</span>
					</div>
					<div class="col-xs-8 col-sm-8">
						<input style="width: 100%;height: 1rem;border:none;" name="start_date" id="start_date" placeholder="选择预约时间" readonly="readonly" />
					</div>
				</div>
			</div>
			<!--基本信息-->
			<div class="container">
				<div class="row">
					<span class="method_01">基本信息</span>
				</div>
				<div class="row form">
					<div class="col-xs-4 col-sm-4">
						<span class="form_title">姓名</span>
					</div>
					<div class="col-xs-7 col-sm-7" style="padding-left: 0;">
						<input class="form_input" name="username" type="text" placeholder="请输入姓名" onblur="this.placeholder='请输入姓名'" onfocus="this.placeholder=''" />
					</div>
				</div>
				<div class="row form">
					<div class="col-xs-4 col-sm-4">
						<span class="form_title">年龄</span>
					</div>
					<div class="col-xs-7 col-sm-7" style="padding-left: 0;">
						<input class="form_input" name="age" type="text" placeholder="请输入您孩子的年龄" onblur="this.placeholder='请输入您孩子的年龄'" onfocus="this.placeholder=''" />
					</div>
				</div>
				<div class="row form">
					<div class="col-xs-4 col-sm-4">
						<span class="form_title">性别</span>
					</div>
					<div class="col-xs-7 col-sm-7" style="padding-left: 0;">
						<span class="sex checked" onclick="changeSex(this,'1');">男生</span>
						<span class="sex unchecked" onclick="changeSex(this,'2');">女生</span>
					</div>
				</div>
				<div class="row form">
					<div class="col-xs-4 col-sm-4">
						<span class="form_title">联系方式</span>
					</div>
					<div class="col-xs-7 col-sm-7" style="padding-left: 0;">
						<input class="form_input" name="phoneNum" type="text" placeholder="请输入联系方式" onblur="this.placeholder='请输入联系方式'" onfocus="this.placeholder=''" />
					</div>
				</div>
			</div>
			<!--问题类型及描述-->
			<div class="container">
				<div class="row">
					<span class="method_01">问题类型及描述</span>
				</div>
				<div class="row" style="margin-bottom: 0.293333rem;">
					<span class="title02">常见问题标签</span>
				</div>
				<div class="row" id="lebel" style="margin-top: 0.2rem;margin-bottom: 0.2rem;">

				</div>
				<div class="row" style="margin-bottom: 0.293333rem;margin-top: 0.293333rem;">
					<span class="title02">
					问题描述
				</span>
				</div>
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<textarea class="form_input" name="problem" placeholder="请详细描述您遇到的问题，更有助于咨询师帮助您哦~"></textarea>
					</div>
				</div>
			</div>

			<!--紧急联系人-->
			<div class="container">
				<div class="row">
					<span class="method_01">紧急联系人</span>
				</div>
				<div class="row form">
					<div class="col-xs-4 col-sm-4">
						<span class="form_title">姓名</span>
					</div>
					<div class="col-xs-7 col-sm-7" style="padding-left: 0;">
						<input class="form_input" name="contact_name" type="text" placeholder="请输入紧急联系人姓名" onblur="this.placeholder='请输入紧急联系人姓名'" onfocus="this.placeholder=''" />
					</div>
				</div>
				<div class="row form">
					<div class="col-xs-4 col-sm-4">
						<span class="form_title">关系</span>
					</div>
					<div class="col-xs-7 col-sm-7" style="padding-left: 0;">
						<input  class="form_input" name="contact_relation" type="text" placeholder="请输入紧急联系人关系" onblur="this.placeholder='请输入紧急联系人关系'" onfocus="this.placeholder=''" />
					</div>
				</div>
				<div class="row form">
					<div class="col-xs-4 col-sm-4">
						<span class="form_title">联系方式</span>
					</div>
					<div class="col-xs-7 col-sm-7" style="padding-left: 0;">
						<input class="form_input" name="contact_phone" type="text" placeholder="该联系方式用于紧急情况" onblur="this.placeholder='该联系方式用于紧急情况'" onfocus="this.placeholder=''" />
					</div>
				</div>
			</div>
			
			<div class="row" style="padding-bottom: 2rem;">
				<div class="col-xs-1 col-sm-1"><img id="read" class="read" src="img/no.png"/></div>
				<div class="col-xs-11 col-sm-11"><span class="read_01">我已阅读并同意</span><span class="read_02"><a href="readme.html"> 《顾问在线服务协议》</a></span></div>
			</div>
		</div>
		
		<div class="row footer">
			<div class="col-xs-6 col-sm-6 amount">
				<span class="amount_01">共计</span><span class="amount_02">¥</span><span class="amount_03" id="totalMoney">100</span>
			</div>
			<div class="col-xs-6 col-sm-6">
				<button class="appointment" onclick="createOrder();">立即预约</button>
			</div>
		</div>

	<script id="labelTemp" type="text/template">
        <ul class="tagboard">
            {@each data.counselorVo.tags.split(',') as item,index}
        <li class="tagUncheck" onclick="checkTags(this);">\${item}</li>
        {@/each}
        </ul>
	</script>
	<script id="consulorTemp" type="text/template">
        <div class="row content_01">
            <div class="col-xs-3 col-sm-2 row_01">
            <img class="head_img" src="\${infoData.counselorVo.imagePhotoUrl}" />
            </div>
            <div class="col-xs-6 col-sm-8 row_01">
            <div class="row consotor">
            <div class="col-xs-6 col-xs-6 consotor_01">\${infoData.counselorVo.name}</div>
            <div class="col-xs-6 col-xs-6 consotor_02"><img class="level" src="img/level.png" />\${infoData.counselorVo.level}</div>
            </div>
            <div class="row introduce">
            <p>\${infoData.counselorVo.certification}</p>
            </div>
            </div>
            <div class="col-xs-3 col-sm-2 row_01">
            <%--<button class="chat" onclick="window.location.href='../chat/index.jsp'">聊一聊</button>--%>
            </div>
            </div>
            <div class="row method">
				<span class="method_01">咨询方式</span>
				<span class="method_02">每次30分钟</span>
            </div>
            <div class="row content_02">
				{@each infoData.consultVo.productList as consultType}
				{@if consultType.type == '1'}
				<div class="col-xs-4 col-sm-4">
					<span class="unactive" onclick="changeType(this,'\${consultType.price}','\${consultType.id}');">
						<p class="words_01"><span class="money">\${consultType.price}</span>元/次</p>
						<p class="words_02">电话咨询</p>
					</span>
				</div>
				{@else if consultType.type == '2'}
				<div class="col-xs-4 col-sm-4 panelbox" style="padding-left: 0;">
					<span class="unactive" onclick="changeType(this,'\${consultType.price}','\${consultType.id}');">
						<p class="words_01"><span class="money">\${consultType.price}</span>元/次</p>
						<p class="words_02">视频咨询</p>
					</span>
				</div>
				{@else if consultType.type == '3'}
				<div class="col-xs-4 col-sm-4 panelbox" style="padding-left: 0;">
					<span class="unactive" onclick="changeType(this,'\${consultType.price}','\${consultType.id}');">
						 <p class="words_01"><span class="money">\${consultType.price}</span>元/次</p>
						 <p class="words_02">面对面咨询</p>
					</span>
				</div>
				{@/if}
				{@/each}
            </div>
            <div class="row content_03">
            <div class="col-xs-7 col-sm-7" style="padding-left: 0;">
            <span class="method_01">咨询次数</span>
            <span class="method_02">每次30分钟</span>
            </div>
            <div class="col-xs-5 col-sm-5" style="padding-top: 0.2rem;">
            <span class="reduce"><img src="img/reduce.png" onclick="reduce();"/></span>
            <span class="num" id="num">1</span>
            <span class="add"><img src="img/add.png" onclick="add();"/></span>
            </div>
            </div>
	</script>
	</body>
	
	<!--预约须知-->
	<div class="notes" id="notes" style="display: none;">
		<div class="row">
			<p class="ftitle">预约肖海平老师须知</p>
		</div>
		<div class="row">
			<p class="stitle"><span class="dot"></span><span>回应时长</span></p>
		</div>
		<div class="row">
			<p class="note_content">
				您好，我将在接收到预约通知的24小时内回复您~接下来会以私信协商时间进行电话咨询或邀请您加入QQ或微信进行视频咨询， 面对面咨询地点在常德
			</p>
		</div>
		<div class="row">
			<p class="stitle"><span class="dot"></span><span>若变更预约</span></p>
		</div>
		<div class="row">
			<p class="note_content">
                               若因为不可抗力需要变更/取消已协商好的咨询预约，请务必提前24小时联络我。否则咨询将如期开始			
			</p>
		</div>
		<div class="row">
			<p class="stitle"><span class="dot"></span><span>爽约/迟到</span></p>
		</div>
		<div class="row">
			<p class="note_content">咨询结束的时间不变，请留意时间哦~</p>
		</div>
		<div class="row noteopt" style="text-align: center;">
			<button class="readnote" onclick="closeTips();">我知道了</button>
		</div>
	</div>
</html>