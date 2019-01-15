<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>学员注册</title>
		<jsp:include page="../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/www/common/css/mobileSelect.css"/>
	</head>
	<body>
		<div class="index">
			<div class="form-title">基本信息填写</div>
			<div class="form-box basicinfo">
				<div class="form-content form-border">
					<label class="form-label">姓名</label>
					<input class="form-input" type="text" name="name" placeholder="识别/输入真实姓名"/>
				</div>
				<div class="form-content">
					<label class="form-label">身份证号</label>
					<input class="form-input" maxlength="18" type="text" name="idCard" placeholder="识别/输入身份证号（选填）"/>
				</div>
                <div class="camera-box">
                    <label for="file"><img src="img/camera.png" class="camera"/></label>
                    <input type="file" id="file" name="file" onchange="imgChange();" style="display: none;width: 100%;height: 100%;"/>
                </div>
			</div>
			
			<div class="form-box">
				<%--<div class="form-content form-border">
					<label class="form-label">性别</label>
					<div class="radio-box">
						<div class="sex-box"><img src="img/sex_unselect.png" class="sex-radio"/><span class="radio-title">男生</span></div>
						<div class="sex-box"><img src="img/sex_unselect.png" class="sex-radio"/><span class="radio-title">女生</span></div>
					</div>
				</div>--%>
				<div class="form-content form-border">
					<label class="form-label">政治面貌</label>
					<div class="form-select" type="text" name="political" id="political"><span class="unselect">请选择政治面貌</span></div>
					<img src="img/icon_select.png" class="redirect" />
				</div>
				<div class="form-content form-border">
					<label class="form-label">单位名称</label>
					<input class="form-input" type="text" name="workUnitName" placeholder="请输入单位名称"/>
				</div>
				<div class="form-content form-border">
					<label class="form-label">单位职务</label>
					<input class="form-input" type="text" name="postName" placeholder="请输入所在单位职务"/>
				</div>
				<div class="form-content">
					<label class="form-label">班次</label>
					<div class="form-select" type="text"  id="classes"><span class="unselect">请选择党校所分配的班级</span></div>
					<img src="img/icon_select.png" class="redirect" />
				</div>
			</div>
			
			<div class="form-title">账号密码设置</div>
			
			<div class="form-box">
				<div class="form-content form-border">
					<label class="form-label">手机号</label>
					<input class="form-input" maxlength="11" type="number" name="mobile" placeholder="请输入手机号码"/>
				</div>
				<div class="form-content form-border">
					<label class="form-label">设置密码</label>
					<input class="form-input" minlength="6" type="password" name="pwd" placeholder="请设置登录密码（6位以上)"/>
				</div>
				<div class="form-content">
					<label class="form-label">确认密码</label>
					<input class="form-input" minlength="6" type="password" name="password" placeholder="请再次输入登录密码"/>
				</div>
			</div>
			
			<div class="tips">
				<img class="tips-img" src="img/tips.png"/>
				<div class="tips-content">该手机号和密码用于登录此平台</div>
			</div>
			
			<div class="footer">
				<img src="img/regist.png" onclick="regist();"/>
			</div>
		</div>


		<!--绑定微信栏目-->
		<div class="modal"id="bindModal"  style="display: none;">
            <form action="${pageContext.request.contextPath}/www/oauth2/auth" id="bindForm" method="post" onsubmit="bindWx();">
                <input type="hidden" name="jsonData"/>
                <input type="hidden" value="WX_BIND"  name="action"/>
                <input type="hidden" value="/partyschool/www/login/index.jsp" id="respUri" name="respUri"/>


                <div class="modal-main">
                    <div class="modal-banner"><img src="img/bind.png" class='banner'/></div>
                    <div class="modal-title">是否绑定微信</div>
                    <div class="modal-sectitle">绑定微信快速登录</div>
                    <div class="modal-opt">
                        <div class="cancel"><span onclick="cancel();">取消</span></div>
                        <div class="bind" ><button type="submit" class="submit">立即绑定</button></div>
                    </div>
                </div>
            </form>
		</div>
	</body>

	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/mobileSelect.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery.base64.js"></script>
	<script type="text/javascript" src="js/student.js"></script>
</html>
