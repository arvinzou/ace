<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->

<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>

</head>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<!-- END HEAD -->
<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';
</script>

<body class="login">
<form class="login_form" id="login_form"  action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    <input type="hidden" name="j_username" value="${param.j_username}">
    <input type="hidden" name="code" value="${param.code}">
    <input type="hidden" name="j_password" value="123123">
</form>

<div id="progress-bar-box" class="progress-bar-box hide">


    <div class="space-6"></div>

    <p>
        <font color="#FF0000">登录中……</font></b>
    </p>

</div>
<script type="text/javascript">
function j_submit() {
	document.getElementById("login_form").submit()
	$('.content').addClass('hide');
	$('#progress-bar-box').removeClass('hide');
}
jQuery(function ($) {
 window.onload = new function () {
    j_submit();
 }
});
</script>

</body>
</html>