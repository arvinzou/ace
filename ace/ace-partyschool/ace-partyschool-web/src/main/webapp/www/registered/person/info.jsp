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
	</head>
	<body>
		<div class="index">
			<div class="box" id="userInfo">

			</div>
			<div class="box" id="option">

			</div>
			<div class="box">
				<div class="exit" onclick="exit();">
					<span>退出登录</span>
				</div>
			</div>
		</div>
	</body>
	<script id="info-tpl" type="text/template">
		<div class="info">
			<div class="title">姓名</div>
			<div class="content">
				{@if data.regType == "student"}
					\${data.student.name}
				{@else}
				    \${data.teacher.name}
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="title">身份证号</div>
			<div class="content">
				{@if data.regType == "student"}
				<input type="text" name="idCard"  value="\${data.student.idCard}"/>
				{@else}
				<input type="text" name="idCard"  value="\${data.teacher.idCard}"/>
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="title">手机号</div>
			<div class="content">
				{@if data.regType == "student"}
				<input type="text" name="mobile"  value="\${data.student.mobile}"/>
				{@else}
				<input type="text" name="mobile"  value="\${data.teacher.mobile}"/>
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="title">性别</div>
			<div class="content">
				{@if data.sex == '1'}
				<div class="sex-left">
					<div class="inner">
						<img src="img/icon-sex.png" class="icon-sex"/><span>男</span>
					</div>
				</div>
				<div class="sex-right">
					<div class="inner">
						<img src="img/icon-unsex.png" class="icon-sex"/><span>女</span>
					</div>
				</div>
				{@else}
				<div class="sex-left">
					<div class="inner">
						<img src="img/icon-unsex.png" class="icon-sex"/><span>男</span>
					</div>
				</div>
				<div class="sex-right">
					<div class="inner">
						<img src="img/icon-sex.png" class="icon-sex"/><span>女</span>
					</div>
				</div>
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="title">政治面貌</div>
			<div class="content">
				{@if data.regType == "student"}
					{@if data.student.political == 'party'}
						党员
					{@else}
						普通学员
					{@/if}
				{@else}
					{@if data.teacher.political == 'party'}
					党员
					{@else}
					普通学员
					{@/if}
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="title">单位名称</div>
			<div class="content">
				{@if data.regType == "student"}
				\${data.student.workUnitName}
				{@else}
				\${data.teacher.workUnitName}
				{@/if}
			</div>
		</div>
		<div class="info">
			<div class="title">单位职务</div>
			<div class="content">
				{@if data.regType == "student"}
				<input type="text" name="postName"  value="\${data.student.postName}"/>
				{@else}
				<input type="text" name="postName"  value="\${data.teacher.postName}"/>
				{@/if}
			</div>
		</div>
	</script>

	<script id="option-tpl" type="text/template">
		<div class="info">
			<div class="title">微信绑定</div>
			<div class="content" id="bindWx">
				{@if data.isBindWx == 0}
				<form action="${pageContext.request.contextPath}/www/oauth2/auth" id="bindForm" method="post" onsubmit="bindWx();">
					<input type="hidden" name="jsonData"/>
					<input type="hidden" value="WX_BIND"  name="action"/>
					<input type="hidden" value="/partyschool/www/registered/person/info.jsp" id="respUri" name="respUri"/>
					<button type="submit" class="bind-btn bindLink">绑定微信</button><img class="redirect" src="img/icon_select.png"/>
				</form>
				{@else}
				<span class="bind">已绑定</span>
				{@/if}
			</div>
			</form>
		</div>
		<div class="info">
			<div class="title">密码设置</div>
			<div class="content" onclick="editPassword();"><span class="bindLink">修改密码</span><img class="redirect" src="img/icon_select.png"/></div>
		</div>
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/www/common/js/init-rem.js"></script>
	<script type="text/javascript" src="${portalPath}/content/common/juicer/juicer-min.js"></script>
	<script type="text/javascript" src="js/info.js"></script>
</html>
