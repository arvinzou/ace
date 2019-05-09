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
    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<script type="text/javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<body class=" login">


<div class="logo">
    <img src="img/logo.png" alt=""/>
</div>

<div class="login-frame">
    <form class="login_form" id="login_form" name="login_form"
          action="${pageContext.request.contextPath}/j_spring_security_check"
          method="post">
        <div class="form">
            <div class="title">登陆</div>
            <div class="input">
                <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off"
                       placeholder="账号" name="j_username" id="j_username" value="${username}"/>
            </div>
            <div class="input">
                <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off"
                       placeholder="密码" name="j_password" id="j_password" value="${password}"/>
            </div>
            <div class="input numbers">
                <input class="form-control form-control-solid placeholder-no-fix" type="text" name="j_captcha"
                       autocomplete="off" id="j_captcha"
                       placeholder="验证码" value=""/>
                <img id="imageF"
                     src="${pageContext.request.contextPath}/captcha/image.do?date=${date}"/>
            </div>
            <div class="set">
                <div class="remenber checkbox-group">
                    <input type="checkbox" id="t" name="ch" ${ch} value="true"/>
                    <label class="key" for="t">两周之内记住我</label>
                </div>
                <%--<div class="forget">
                    忘记密码了
                </div>--%>
            </div>
            <div class="submit">
                <button type="button" id="btn-login-submit" class="btn red btn-block uppercase">登录</button>
            </div>
        </div>
    </form>
</div>


<%--<div class="content">
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
           </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
           </div>
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


</div>--%>

<%--<div id="progress-bar-box"
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
                <button type="button" class="btn blue" id="btn-resetpasswd">提交</button>
                <button type="button" data-dismiss="modal" class="btn red">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="copyright hide"> 2014 © Metronic. Admin Dashboard Template.</div>--%>


<!-- BEGIN CORE PLUGINS -->

<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/js.cookie.min.js"
        type="text/javascript"></script>

<%--<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.blockui.min.js"--%>
        <%--type="text/javascript"></script>--%>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery-validation/js/additional-methods.min.js"
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
        var form = document.getElementById("login_form");
        form.submit();
        $('.content').addClass('hide');// hide others
        $('#progress-bar-box').removeClass('hide');
    }

    window.onload = new function () {
        document.getElementById("j_username").focus();
        if (window.top != window.self)
            window.top.window.location.href = contextPath
                + "/dynamic/portal/security/login.jsp";

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


</script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<!-- END THEME LAYOUT SCRIPTS -->
</body>
<style>
    .numbers{
        display: flex;
    }

    .numbers input{
        width: 50%;
    }

    #imageF{
    height: 100%;
        width: auto;
    }

    * {
        margin: 0;
        padding: 0;
        outline: 0;
        box-sizing: border-box;
        /*去除系统默认appearance的样式,常用于IOS下移除原生样式*/
        -webkit-appearance: none;
        /*当用户轻按一个链接或者JavaScript可点击元素时给元素覆盖一个高亮色
    如果想取消这个高亮，将值设置为全透明即可，比如transparent*/
        -webkit-tap-highlight-color: transparent
    }

    body,
    html {
        height: 100%
    }

    body {
        /*width: 16rem!important;*/
        margin: 0 auto !important;
        /*文本大小不会根据设备尺寸进行调整。*/
        -webkit-text-size-adjust: none;
        /*抗锯齿*/
        -webkit-font-smoothing: antialiased;
        /*字体*/
        font-family: "Helvetica Neue", Helvetica, Arial, sans-serif
    }

    a {
        /*文字线。下划线*/
        text-decoration: none
    }

    li,
    ul {
        /*样式*/
        list-style: none
    }

    input {
        border: 0
    }

    .checkbox-group input {
        display: none;
    }

    .checkbox-group .key {
        display: inline-block;
        position: relative;
        font-size: 14px;
        font-family: MicrosoftYaHei;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        padding-left: 24px;
    }

    .checkbox-group .key::before {
        position: absolute;
        content: "";
        display: block;
        width: 14px;
        height: 14px;
        border: 1px solid rgba(255, 255, 255, 1);
        border-radius: 2px;
        left: 0px;
        top: 50%;
        transform: translate(0%, -50%);
    }

    .checkbox-group input:checked + .key::after {
        content: '';
        width: 9px;
        height: 5px;
        position: absolute;
        left: 0px;
        top: 50%;
        border: 3px solid #fff;
        border-top: none;
        border-right: none;
        background: transparent;
        transform: translate(21%, -72%) rotate(-45deg);
        display: block;
    }

    body {
        background: url(img/bg.png);
        background-size: cover;
        background-position: 50% 50%;
    }

    .login-frame {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -70%);
        width: 434px;
        height: 338px;
        background: url(img/login.png);
        background-size: cover;
        padding-top: 30px;
    }

    .login-frame .form {
        width: 326px;
        margin: auto;
    }

    .login-frame .title {
        font-size: 20px;
        font-family: MicrosoftYaHei;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
        margin-bottom: 15px;
    }

    .login-frame .input {
        margin-bottom: 8px;
        height:45px;
    }

    .login-frame .input input {
        width: 100%;
        height: 45px;
        background: rgba(52, 61, 76, 1);
        border-radius: 2px;
        padding: 0 20px;
        line-height: 45px;
        font-size: 14px;
        font-family: MicrosoftYaHei;
        font-weight: 400;
        color: rgba(255, 255, 255, 1);
    }

    .login-frame .set {
        display: flex;
        justify-content: space-between;
        margin-bottom: 15px;
    }

    .login-frame .set .forget {
        font-size: 14px;
        font-family: MicrosoftYaHei;
        font-weight: 400;
        color: rgba(0, 222, 222, 1);
    }

    .login-frame .submit button {
        width: 100%;
        height: 48px;
        background: rgba(0, 222, 222, 1);
        border-radius: 2px;
        font-size: 16px;
        font-family: MicrosoftYaHei;
        font-weight: 400;
        color: rgba(5, 91, 91, 1);
        text-align: center;
        line-height: 48px;
        border: none;
    }

    .login-frame .submit button:active {
        background: rgb(3, 191, 191);
    }

    .logo {
        width: 200px;
        height: 40px;
        position: absolute;
        top: 40px;
        left: 54px;
    }

    .logo img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }


</style>
</html>