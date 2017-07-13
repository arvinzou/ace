<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%
request.setAttribute("ch", "");
request.setAttribute("date", new java.util.Date().getTime());
javax.servlet.http.Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
if(cookies!=null){
for(javax.servlet.http.Cookie cookie : cookies){
if(cookie.getName().equals("username")||cookie.getName().equals("password")||cookie.getName().equals("ch")){
request.setAttribute((String)cookie.getName(), (String)cookie.getValue());
}
}
}
%>
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
    <title>${cfg.sys_name} 登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content=">${cfg.sys_name} " name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/simple-line-icons/simple-line-icons.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
          rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGINS -->
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/select2/css/select2.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/plugins/select2/css/select2-bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL PLUGINS -->
    <!-- BEGIN THEME GLOBAL STYLES -->
    <link href="${pageContext.request.contextPath}/content/common/assets/global/css/components.min.css" rel="stylesheet"
          id="style_components" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/css/plugins.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- END THEME GLOBAL STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="${pageContext.request.contextPath}/content/common/assets/pages/css/login-2.min.css" rel="stylesheet"
          type="text/css"/>
    <!-- END PAGE LEVEL STYLES -->
    <!-- BEGIN THEME LAYOUT STYLES -->
    <!-- END THEME LAYOUT STYLES -->
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';


</script>

<body class=" login">
<!-- BEGIN LOGO -->
<div class="logo">
    <h1>

        <span  style="color:#FFFFFF">华彩${cfg.sys_name}</span>
    </h1>
    <div style="color: red;font-size:16px">
        ${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
        ${sessionScope['j_captcha_error']}
    </div>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login_form" id="login_form" name="login_form"
          action="${pageContext.request.contextPath}/j_spring_security_check"
          method="post">

        <div class="form-title">

            <span class="form-subtitle">欢迎，请登录.</span>
        </div>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span> 请输入账号与密码. </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">账号</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off"
                   placeholder="账号" name="j_username" id="j_username" value="${username}"/></div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off"
                   placeholder="密码" name="j_password" id="j_password" value="${password}"/></div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">验证码</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" name="j_captcha"
                   autocomplete="off" id="j_captcha"
                   placeholder="验证码" value=""/>


            </label>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">验证码</label>


            <a href="#" id="flashImage"><img id="imageF"
                                             src="${pageContext.request.contextPath}/captcha/image.do?date=${date}"/>
            </a>


            </label>
        </div>
        <div class="form-actions">
            <div class="pull-left">
                <label class="rememberme mt-checkbox mt-checkbox-outline">
                    <input type="checkbox" name="ch" ${ch} value="true"/> 两周之内记住我
                    <span></span>
                </label>
            </div>
            <div class="pull-right forget-password-block">
                <a data-target="#stack1" data-toggle="modal" class="forget-password">忘记密码了</a>
            </div>
        </div>

        <div class="form-actions">
            <button type="button" id="btn-login-submit" class="btn red btn-block uppercase">登录</button>
        </div>


    </form>
    <!-- /.main-content -->
    <div align="center">
        <h6>
            <span style="font-family: '微软雅黑'">建议WIN7以上系统使用IE9以上浏览器，XP、MAC系统使用</span><a
                href="http://rj.baidu.com/soft/detail/14744.html?ald"
                target="_blank" style="font-family: '微软雅黑'">谷歌浏览器</a> <span
                style="font-family: '微软雅黑'">分辨率1024*768以上为佳</span>
        </h6>
    </div>


</div>

<div id="progress-bar-box"
     class="logo hide">
    <h4 class="header red lighter bigger">
        <i class="ace-icon fa fa-key"></i> 系统提示
    </h4>

    <div class="space-6"></div>

    <p>
        <font color="#FF0000">登录中……</font></b>
    </p>

</div>
<div id="stack1" class="modal fade" tabindex="-1" data-width="300">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">忘记密码 ?</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="padding:10px">
                    <div style="padding:10px">
                        输入注册的个人邮箱，系统将重置的密码发送到此邮箱.
                    </div>

                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="邮箱"
                               name="email" id="email"/>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button"  class="btn blue" id="btn-resetpasswd">提交</button>
                <button type="button" data-dismiss="modal" class="btn red">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="copyright hide"> 2014 © Metronic. Admin Dashboard Template.</div>
<!-- END LOGIN -->
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->

<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.min.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery-ui.min.js?version=${cfg.version}"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/pages/scripts/ui-modals.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/js.cookie.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.blockui.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
        type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery-validation/js/additional-methods.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/select2/js/select2.full.min.js"
        type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${pageContext.request.contextPath}/content/common/assets/global/scripts/app.min.js"
        type="text/javascript"></script>
<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${pageContext.request.contextPath}/content/common/assets/pages/scripts/login.min.js"
        type="text/javascript"></script>

<script type="text/javascript">

function onLoad() {
	document.getElementById("j_username").focus();
}
function j_submit() {
	var userName = document.getElementById("j_username");
	var password = document.getElementById("j_password");
	var captcha = document.getElementById("j_captcha");
	var reg = /(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,16}/i;
	if (userName.value == "") {
		userName.focus();
		alert("请输入用户名");
		return false;
	}
	if (password.value == "") {
		password.focus();
		alert("请输入密码");
		return false;
	}
	if (captcha.value == "") {
		captcha.focus();
		alert("请输入验证码");
		return false;
	}
	if (userName.value.length > 20) {
		userName.focus();
		alert("用户名长度不能超过20");
		return false;
	}

	if (password.value.length > 20) {
		password.focus();
		alert("密码 长度不能超过20");
		return false;
	}
	if (!reg.test(password.value)) {
		alert("尊敬的用户您的密码过于简单，建议您把密码修改为，长度大于8位 、包含字母、数字、特殊符号，谢谢！");
	}
	var form = document.getElementById("login_form");
	form.submit();
	$('.content').addClass('hide');// hide others
	$('#progress-bar-box').removeClass('hide');
}
window.onload = new function() {
 document.getElementById("j_username").focus();
	if (window.top != window.self)
		window.top.window.location.href = contextPath
				+ "/dynamic/portal/security/login.jsp";

}
jQuery(function($) {

	var userName = document.getElementById("j_username");
	var password = document.getElementById("j_password");
	var captcha = document.getElementById("j_captcha");
	$("#flashImage").click(
			function() {
				$('#imageF').hide().attr(
						'src',
						contextPath+'/captcha/image.do'
								+ '?' + Math.floor(Math.random() * 100))
						.fadeIn();
			});

	$('#j_username').keydown(function(e) {
		if (e.keyCode == 13) {
			password.focus();
		}
	});
	$('#j_password').keydown(function(e) {
		if (e.keyCode == 13) {
			captcha.focus();
		}
	});
	$('#j_captcha').keydown(function(e) {
		if (e.keyCode == 13) {
			j_submit();
		}
	});
	$('#btn-login-submit').on('click', function(e) {
		j_submit();
	});
	$('#btn-resetpasswd')
			.on(
					'click',
					function(e) {
						$
								.ajax({
									type : "post",
									url : contextPath
											+ "/system/retrievePassword.do",
									data : {
										email : $('#email').val()
									},
									beforeSend : function(XMLHttpRequest) {

									},
									success : function(rst, textStatus) {
									    $('#stack1').modal('hide');
										alert(rst.errorMessage);

									},
									complete : function(XMLHttpRequest,
											textStatus) {

									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										alert(XMLHttpRequest.status);
									}
								});
					});
});



</script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<!-- END THEME LAYOUT SCRIPTS -->
</body>
<style>
    .login .logo {
    margin: 20px auto 0;
    padding: 15px;
    text-align: center;

}
    .login .content {
    width: 400px;
    margin: 10px auto;
}
a, button, code, div, img, input, label, li, p, pre, select, span, svg, table, td, textarea, th, ul {
    -webkit-border-radius: 0!important;
    -moz-border-radius: 0!important;
    border-radius: 2px!important;
}

</style>
</html>