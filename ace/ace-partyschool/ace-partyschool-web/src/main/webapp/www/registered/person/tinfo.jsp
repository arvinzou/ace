<%@page language="java" contentType="text/html; charset=utf-8"
		pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>个人信息</title>
		<jsp:include page="../../common/common.jsp"/>
		<link rel="stylesheet" type="text/css" href="css/info.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/www/common/css/mobileSelect.css"/>
	</head>
	<body>
		<div class="index">
			<div class="box" id="userInfo">

			</div>
			<div class="box" id="option">

			</div>
			<div class="box">
				<div class="exit">
                    <span class="sbr" onclick="save();">保存修改</span>
					<span onclick="exit();">退出登录</span>
				</div>
			</div>
		</div>
	</body>
	<script id="info-tpl" type="text/template">
		<div class="info">
			<div class="ititle br">姓名</div>
			<div class="icontent br">
				\${data.teacher.name}
			</div>
		</div>
		<div class="info">
			<div class="ititle br">身份证号</div>
			<div class="icontent br">
				<input type="text" name="idCard" maxlength="18"  value="\${data.teacher.idCard}"/>
			</div>
		</div>
		<div class="info">
			<div class="ititle br">手机号</div>
			<div class="icontent br">
				<input type="text" name="mobile" maxlength="11"  value="\${data.teacher.mobile}"/>
			</div>
		</div>
		<div class="info">
			<div class="ititle br">性别</div>
			<div class="icontent br">
				{@if data.sex == '1'}
				<div class="sex-left">
                    <img src="img/icon-sex.png" class="icon-sex" onclick="selectSex(this,'1')"/><span>男</span>
				</div>
				<div class="sex-right">
                    <img src="img/icon-unsex.png" class="icon-sex" onclick="selectSex(this,'2')"/><span>女</span>
				</div>
				{@else}
				<div class="sex-left">
					<img src="img/icon-unsex.png" class="icon-sex" onclick="selectSex(this,'1')"/><span>男</span>
				</div>
				<div class="sex-right">
					<img src="img/icon-sex.png" class="icon-sex" onclick="selectSex(this,'2')"/><span>女</span>
				</div>
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="ititle br">政治面貌</div>
			<div class="icontent br"  name="political" id="political">
				{@if data.teacher.political == 'party'}
				<span class="unselect">党员</span>
				{@else}
				<span class="unselect">非党员</span>
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="ititle br">处室</div>
			<div class="icontent br" name="workUnit" id="workUnit">
				<span class="unselect">\${data.teacher.workUnitName}</span>
			</div>
		</div>
		<div class="info">
			<div class="ititle br">职务全称</div>
			<div class="icontent br">
				<input class="form-input" type="text" name="postName" value="\${data.teacher.postName}"/>
			</div>
		</div>
	</script>

	<script id="option-tpl" type="text/template">
		<div class="info">
			<div class="ititle">微信绑定</div>
			<div class="icontent" id="bindWx">
				{@if data.isBindWx == 0}
				<form action="${pageContext.request.contextPath}/www/oauth2/auth" id="bindForm" method="post" onsubmit="bindWx();">
					<input type="hidden" name="jsonData"/>
					<input type="hidden" value="WX_BIND"  name="action"/>
					<input type="hidden" value="/partyschool/www/registered/person/tinfo.jsp" id="respUri" name="respUri"/>
					<button type="submit" class="bind-btn bindLink">绑定微信</button><img class="redirect" src="img/icon_select.png"/>
				</form>
				{@else}
				<span class="bind">已绑定</span>
				{@/if}
			</div>
			</form>
		</div>
		<div class="info">
			<div class="ititle">密码设置</div>
			<div class="icontent" onclick="editPassword();"><span class="bindLink">修改密码</span><img class="redirect" src="img/icon_select.png"/></div>
		</div>
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
	<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/mobileSelect.min.js"></script>
	<script src="${portalPath}/content/common/js/dict_partyschool.js?version=${version}"></script>
	<script type="text/javascript" src="js/info.js"></script>
</html>
