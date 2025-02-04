<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>咨询师主页</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>

	<body>
		<div class="main_box">
			<div class="container">
				<div class="row">
					<img class="cbanner" src="img/cbanner.png" />
				</div>
				<div class="chead">

				</div>
			</div>
			<div id="consulor"></div>
		</div>
		<div class="row footer">
			<%--<div class="col-xs-5 col-sm-5">
				&lt;%&ndash;<span class="chat_icon"><img src="img/chat_icon.png"/></span>&ndash;%&gt;
				&lt;%&ndash;<span>聊一聊</span>&ndash;%&gt;
			</div>--%>
			<div class="col-xs-12 col-sm-12" id="createOrder">

			</div>
		</div>

		<script id="headTemp" type="text/template">
            <img src="\${headData.imagePhotoUrl}" />
		</script>
		<script id="consulorTemp" type="text/template">
        <div class="content">
			<div class="row data" style="margin-top: 0.6rem!important;">
            <p class="data_01">\${data.counselorVo.name}</p>
            <p class="data_02">\${data.counselorVo.certification}</p>
            </div>
            <div class="row">
            <div class="col-xs-4 col-sm-4 statics">
            <p class="case_num">\${data.counselorVo.peopleNum}</p>
            <p class="case_title">个案人数(人)</p>
            </div>
            <div class="col-xs-4 col-sm-4 statics">
            <p class="course_num">\${data.counselorVo.duration}</p>
            <p class="course_title">授课时长(时)</p>
            </div>
            <div class="col-xs-4 col-sm-4 statics">
            <p class="score_num">\${data.counselorVo.consultCount}</p>
            <p class="score_title">服务次数</p>
            </div>
            </div>
            <div class="row">
            <h3 class="sec_title">资历认证</h3>
            <p class="detail">\${data.counselorVo.certification}</p>
            </div>
            <div class="row">
            <h3 class="sec_title">擅长领域</h3>
            <ul class="tagboard">
                {@if data.consultVo.field != undefined}
				{@each data.consultVo.field.split(',') as item,index}
            	<li>\${item}</li>
				{@/each}
                {@/if}
            </ul>
            </div>
            <div class="row">
				<h3 class="sec_title">个人简介</h3>
				<div class="row">
					<div class="detail">\$\${data.counselorVo.profile}</div>
				</div>
        </div>
        <div class="row">
            <h3 class="sec_title">咨询方式</h3>
            </div>
            <div class="row">
				{@each data.consultVo.productList as consultType}
				{@if consultType.type == '1'}
				<div class="col-xs-4 col-sm-4 statics">
					<p class="case_num">\${consultType.price}</p>
					<p><span class="unit">（元/次）</span></p>
					<p class="case_title">电话咨询</p>
				</div>
				{@else if consultType.type == '2'}
				<div class="col-xs-4 col-sm-4 statics">
					<p class="course_num">\${consultType.price}</p>
					<p><span class="unit">（元/次）</span></p>
					<p class="course_title">视频咨询</p>
				</div>
				{@else if consultType.type == '3'}
				<div class="col-xs-4 col-sm-4 statics">
					<p class="score_num">\${consultType.price}</p>
					<p><span class="unit">（元/次）</span></p>
					<p class="score_title">面对面咨询</p>
				</div>
				{@/if}
				{@/each}
            </div>
            <div class="row">
            <span class="warn">面对面咨询目前仅开放常德市</span>
            </div>
            <div class="row">
            <h3 class="sec_title">平台保障</h3>
            </div>
            <div class="row">
            <div class="ensure">
            <div class="col-xs-6 col-sm-6" style="padding-right: 0;padding-left: 0;"><span><img class="ensure_img" src="img/ensure.png"/></span><span>来访者隐私安全</span></div>
        <div class="col-xs-6 col-sm-6" style="padding-right: 0;padding-left: 0;"><span><img class="ensure_img" src="img/ensure.png"/></span><span>咨询师真实认证</span></div>
        </div>
        </div>
        </div>
	</script>

		<script id="createOrderTemp" type="text/template">
			{@if counselor.onlineStatus == '1'}
			<button class="appointment" onclick="createOrder('\${counselor.id}');">立即咨询</button>
			{@else if counselor.onlineStatus == '0'}
			<button class="appointment" onclick="createOrder('\${counselor.id}');">预约咨询</button>
			{@/if}
		</script>
	</body>

</html>