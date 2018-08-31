<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta name="format-detection" content="telephone=no" />
		<title>私信</title>
		<jsp:include page="../../../dynamic/common/base.jsp" />
		<script type="text/javascript" src="js/act.js"></script>
		<script type="text/javascript" src="../../common/js/loader.js"></script>
	</head>

	<body>
			<div class="container" style="background-color: #FFFFFF;">
				<div class="row content_01">
					<div class="col-xs-3 col-sm-2 row_01">
						<img class="head_img" src="img/headImg.png" />
					</div>
					<div class="col-xs-6 col-sm-8 row_01">
						<div class="row consotor">
							<div class="col-xs-6 col-xs-6 consotor_01">肖海平</div>
                            <%--<div class="col-xs-6 col-xs-6 consotor_02"><img class="level" src="img/level.png" />5.0</div>--%>
						</div>
						<div class="row introduce">
							<p>国家二级心理咨询师</p>
						</div>
					</div>
					<div class="col-xs-3 col-sm-2 row_01">
						<button class="chat">立即预约</button>
					</div>
				</div>
			</div>
			<div class="body">
				<div id="convo" data-from="Sonu Joshi">
					<ul class="chat-thread">
						<li class="left">
							<span class="head"><img src="img/headImg.png"/></span>
							您好，有什么可以帮到您的吗？
						</li>
					</ul>
				</div>
			</div>
		<div class="formstyle">
			<form class="bs-example bs-example-form" role="form">
				<div class="input-group input-group-lg">
					<input type="text" class="form-control" style="border-radius: unset;" placeholder="点击输入" onfocus="inputFocus();">
					<span id="button" class="input-group-addon" style="border-radius: unset;">发送</span>
				</div>
				<br>
				<input type="text" style="display:none;">
			</form>
		</div>
	</body>

</html>