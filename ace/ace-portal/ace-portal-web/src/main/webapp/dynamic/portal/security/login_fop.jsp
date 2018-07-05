<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    request.setAttribute("ch", "");
    request.setAttribute("date", new java.util.Date().getTime());
    javax.servlet.http.Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
    if (cookies != null) {
        for (javax.servlet.http.Cookie cookie : cookies) {
            if (cookie.getName().equals("username") || cookie.getName().equals("password") || cookie.getName().equals("ch")) {
                request.setAttribute((String) cookie.getName(), (String) cookie.getValue());
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
    <!--
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet"
          type="text/css"/>-->
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

<body>
<!-- BEGIN LOGO -->
<div class="logo">


</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="login_index">
    <div class="fop_head">
        <div class="login_logo_temp">
            <div class="logo_img">

            </div>
            <div class="logo_title">
                <div class="title">常德市非公有制经济公共服务平台</div>
                <div class="sub_title">Chang De city non-public economic public service platform</div>
            </div>
        </div>
    </div>
    <div class="login_body">
        <div class="login_body_temp">
            <div class="login_panel">
                <div class="content">
                    <!-- BEGIN LOGIN FORM -->
                    <div class="login-box-warp">
                        <div class="login-box no-longlogin module-static" id="J_LoginBox">
                            <div class="hd">
                                <div class="login-switch" onselectstart="return false;">
                                    <i class="iconfont quick" style="display:none" id="J_Static2Quick" onclick="javascript:switchLoginType('J_Static2Quick','J_Quick2Static')" ></i>
                                    <i class="iconfont static" style="display:block" id="J_Quick2Static" onclick="javascript:switchLoginType('J_Quick2Static','J_Static2Quick')"></i>
                                </div>
                            </div>

                        </div>
                    </div>

                    <form class="login_form" id="login_form" name="login_form"
                          action="${pageContext.request.contextPath}/j_spring_security_check"
                          method="post">

                        <div class="form-title">
                            <span style="color: red;font-size:16px">${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}${sessionScope['j_captcha_error']}</span>
                        </div>
                        <div class="form-title">
                            <span class="form-subtitle">密码登录.</span>
                        </div>
                        <div id="J_Static2Quick_box" style="display:none;width:100%;text-align:center">
                            <img src="image/weixin.png" class="qrcode"/>

                        </div>
                        <div id="J_Quick2Static_box" style="display:block">
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


                                <a href="#" id="flashImage"><img id="imageF"
                                                                 src="${pageContext.request.contextPath}/captcha/image.do?date=${date}"/>
                                </a>


                                </label>
                            </div>

                            <div class="form-actions">
                                <label class="rememberme mt-checkbox mt-checkbox-outline">
                                    <input type="checkbox" name="ch" ${ch} value="true"/> 两周之内记住我
                                    <span></span>
                                </label>
                                <div class="pull-right forget-password-block">
                                    <a data-target="#stack1" data-toggle="modal" class="forget-password">忘记密码了</a>
                                </div>
                            </div>

                            <div class="form-actions">
                                <button type="button" id="btn-login-submit" class="btn red btn-block uppercase">登录</button>
                            </div>
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
            </div>
        </div>
    </div>
    <div class="login_footer">版权所有：常德市工商联</div>
</div>


<div id="progress-bar-box"
     class="progress-bar-box hide">


    <div class="space-6"></div>

    <p>
        <font color="#FF0000">登录中……</font></b>
    </p>

</div>
<div id="stack1" class="modal fade" tabindex="-1" data-width="250">
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
                <button type="button" class="btn blue" id="btn-resetpasswd">提交</button>
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
<script src="${pageContext.request.contextPath}/content/common/assets/pages/scripts/ui-modals.min.js"
        type="text/javascript"></script>
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
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"
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

    window.onload = new function () {
        document.getElementById("j_username").focus();
        if (window.top != window.self)
            window.top.window.location.href = contextPath
                + "/dynamic/portal/security/login_fop.jsp";

    }
    jQuery(function ($) {

        var userName = document.getElementById("j_username");
        var password = document.getElementById("j_password");
        var captcha = document.getElementById("j_captcha");
        $("#flashImage").click(
            function () {
                $('#imageF').hide().attr(
                    'src',
                    contextPath + '/captcha/image.do'
                    + '?' + Math.floor(Math.random() * 100))
                    .fadeIn();
            });

        $('#j_username').keydown(function (e) {
            if (e.keyCode == 13) {
                password.focus();
            }
        });
        $('#j_password').keydown(function (e) {
            if (e.keyCode == 13) {
                captcha.focus();
            }
        });
        $('#j_captcha').keydown(function (e) {
            if (e.keyCode == 13) {
                j_submit();
            }
        });
        $('#btn-login-submit').on('click', function (e) {
            j_submit();
        });
        $('#btn-resetpasswd')
            .on(
                'click',
                function (e) {
                    $
                        .ajax({
                            type: "post",
                            url: contextPath
                            + "/system/retrievePassword.do",
                            data: {
                                email: $('#email').val()
                            },
                            beforeSend: function (XMLHttpRequest) {

                            },
                            success: function (rst, textStatus) {
                                $('#stack1').modal('hide');
                                alert(rst.errorMessage);

                            },
                            complete: function (XMLHttpRequest,
                                                textStatus) {

                            },
                            error: function (XMLHttpRequest,
                                             textStatus, errorThrown) {
                                alert(XMLHttpRequest.status);
                            }
                        });
                });
    });

    function switchLoginType(cur,other){
        $("#"+cur).css("display","none");
        $("#"+other).css("display","block");

         $("#"+cur+"_box").css("display","none");
        $("#"+other+"_box").css("display","block");
        if(cur=='J_Quick2Static'){
             $(".form-subtitle").html("微信扫码，安全登录");
        }else{
             $(".form-subtitle").html("密码登录");
        }
    }
    function guid() {
        function S4() {
            return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
        }
        return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
    }
    var obj = new WxLogin({
        self_redirect:false,
        id:"J_Static2Quick_box",
        appid: "wx8571545c5cc9d78c",
        scope: "snsapi_login",
        redirect_uri: encodeURI('http://zx.huacainfo.com/portal/dynamic/portal/security/callbak.jsp?j_username='+guid()),
        state: "huacai",
        style: "",
        href: ""
    });

</script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<!-- END THEME LAYOUT SCRIPTS -->
</body>
<style>
    .login .logo {
        margin: 0px auto 0;
        padding: 15px;
        text-align: left;
        height: 150px;

    }

    .login .content {
        margin: 10px auto;
    }

    a, button, code, div, img, input, label, li, p, pre, select, span, svg, table, td, textarea, th, ul {
        -webkit-border-radius: 0 !important;
        -moz-border-radius: 0 !important;
        border-radius: 2px !important;
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

    .form-actions {
        margin-bottom: 15px;
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

    .content {
        border: 1px solid #eee;
    }

    .progress-bar-box {
        text-align: center;
    }

    @font-face {
        font-family: iconfont;
        src: url(//at.alicdn.com/t/font_1451959379_8626566.eot);
        src: url(//at.alicdn.com/t/font_1451959379_8626566.eot?#iefix) format('embedded-opentype'), url(//at.alicdn.com/t/font_1451959379_8626566.woff) format('woff'), url(//at.alicdn.com/t/font_1451959379_8626566.ttf) format('truetype'), url(//at.alicdn.com/t/font_1451959379_8626566.svg#iconfont) format('svg')
    }

    .iconfont {
        font-family: iconfont !important;
        font-size: 16px;
        font-style: normal;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: .2px;
        -moz-osx-font-smoothing: grayscale
    }

    .login-box {
        width: 300px;
        color: #6c6c6c;
        background: #fff;
        position: relative;
        margin: 0 auto;
    }

    .login-switch {
        width: 52px;
        position: absolute;
        right: 5px;
        -moz-user-select: none;
    }

    .login-switch .iconfont {
        font-size: 52px;
        cursor: pointer;
        color: #1A56A8;
    }

    .iconfont {
        font-family: iconfont !important;
        font-size: 16px;
        font-style: normal;
        -webkit-font-smoothing: antialiased;
        -webkit-text-stroke-width: .2px;
        -moz-osx-font-smoothing: grayscale;
    }

    *, :after, :before {
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    user agent stylesheet
    i, cite, em, var, address, dfn {
        font-style: italic;
    }

    .qrcode {
        max-width: 250px;
        padding-bottom: 40px;
    }

    html {
        height: 100%;
    }

    * {
        margin: 0;

    }

    body {
        width: 100%;
        height: 100%;
    }

    .login_index {
        width: 100%;
        min-height: 100%;
        position: relative;
    }

    .fop_head {
        width: 100%;
        background-color: #fff;
        height: 100px;

        position: absolute;
        top: 0;

    }

    .login_logo_temp {
        max-width: 1200px;
        min-width: 900px;
        padding-top: 10px;
        margin: 0 auto;
    }

    .login_logo_temp > div {
        float: left;
    }

    .login_logo_temp .logo_img {
        width: 80px;
        height: 80px;
        background-image: url("image/logo.png");
        background-size: 100% 100%;
    }

    .login_logo_temp .logo_title {
        margin-left: 20px;
        color: #000;
        font-family: "Microsoft YaHei";
        font-weight: bolder;
    }

    .login_logo_temp .logo_title .title {
        font-size: 35px;
        height: 50px;
        line-height: 50px;

    }

    .login_logo_temp .logo_title .sub_title {
        font-size: 15px;
        height: 30px;
        line-height: 30px;
    }

    .login_body {
        min-width: 100%;
        background: -moz-linear-gradient(left, #1A56A8 0%, #1A56A8 36%, #6FDFF6 100%);
        background: -webkit-gradient(linear, left top, left bottom, from(#1A56A8), color-stop(36%, #1A56A8), to(#6FDFF6));
        background: -webkit-linear-gradient(left, #1A56A8 0%, #1A56A8 36%, #6FDFF6 100%);
        background: -o-linear-gradient(left, #1A56A8 0%, #1A56A8 36%, #6FDFF6 100%);
        background: -ms-linear-gradient(left, #1A56A8 0%, #1A56A8 36%, #6FDFF6 100%);
        background: linear-gradient(to right, #1A56A8 0%, #1A56A8 36%, #6FDFF6 100%);
        width: 100%;
        min-height: 700px;
        max-height: 100%;
        padding: 100px 0px 150px;
        box-sizing: border-box;

    }

    .login_body .login_body_temp {
        max-width: 1200px;
        min-width: 900px;
        margin: 120px auto 0 auto;
        background: url("image/body.png") no-repeat;
        height: 460px;
        background-size: auto 100%;
    }

    .login_footer {
        height: 150px;
        width: 100%;
        position: absolute;
        bottom: 0;
        color: #657180;
        text-align: center;
        line-height: 150px;
        background-color: #fff;
    }

    .login_panel {
        float: right;
        width: 350px;
        min-height: 400px;
        max-height: 600px;
        background-color: #fff;
    }

    .login_form {
        width: 80%;
        margin-left: 10%;
        margin-top: 40px;
    }

    a {
        text-decoration: none;
    }

    .login {
        width: 100%;
        height: 35px;
        background-color: #1A56A8;
        border-radius: 5px;
        border: none;
    }

    .btn.red:not(.btn-outline) {
        color: #fff;
        background-color: #1A56A8 !important;
        border-color: #1A56A8 !important;
    }


</style>
</html>