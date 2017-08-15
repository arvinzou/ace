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
    <title>注册管理员</title>
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
    <link href="${pageContext.request.contextPath}/content/common/assets/pages/css/login-3.min.css" rel="stylesheet"
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
    <h3>

        <span  style="color:#FFFFFF">${cfg.sys_name}管理员注册</span>
    </h3>

</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" id="login-form" name="login-form"
          action="${pageContext.request.contextPath}/www/loginUser.do"
          method="post">

        <div class="form-title">

            <span class="form-subtitle">请注册.</span>
        </div>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span> 请输入账号与密码. </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">账号</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
            <input class="form-control placeholder-no-fix" type="text" autocomplete="off"
                   placeholder="账号" name="j_username" id="j_username" value="${username}"/>
            </div>
            </div>

        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
            <input class="form-control  placeholder-no-fix" type="password" autocomplete="off"
                   placeholder="密码" name="j_password" id="j_password" value="${password}"/>
            </div>

            </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">验证码</label>
            <input class="form-control  placeholder-no-fix" type="text" name="j_captcha"
                   autocomplete="off" id="j_captcha"
                   placeholder="验证码" value=""/>


            </label>
        </div>
        <div class="form-group-captcha">
            <label class="control-label visible-ie8 visible-ie9">验证码</label>


            <a href="#" id="flashImage"><img id="imageF" src="${pageContext.request.contextPath}/captcha/image.do?date=${date}"/>
            </a>


            </label>
        </div>


        <div class="form-actions">
            <button type="button" id="btn-login-submit" class="btn red btn-block uppercase">提交</button>
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

	var form = document.getElementById("login-form");
	form.submit();
	$('.content').addClass('hide');
	$('#progress-bar-box').removeClass('hide');
}

jQuery(function($) {
	$("#flashImage").click(
			function() {
			$('#imageF').hide().attr('src',contextPath+'/captcha/image.do'+ '?' + Math.floor(Math.random() * 100)).fadeIn();
	});
	$('#btn-login-submit').on('click', function(e) {
		j_submit();
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
    margin: 10px auto;
}
a, button, code, div, img, input, label, li, p, pre, select, span, svg, table, td, textarea, th, ul {
    -webkit-border-radius: 0!important;
    -moz-border-radius: 0!important;
    border-radius: 2px!important;
}
.login .content .form-actions {
    background-color: #fff;
    clear: both;
    border: 0;
    border-bottom: 1px solid #eee;
    padding: 15px 35px 15px;
    margin-left: -30px;
    margin-right: -30px;
}
.login .content .form-actions .rememberme {
     margin-top: 0px;
    display: inline-block;
}
.mt-checkbox, .mt-radio {
    margin-bottom: 0px;
}
.form-group-captcha {
    margin-bottom: 5px;
}
</style>
</html>