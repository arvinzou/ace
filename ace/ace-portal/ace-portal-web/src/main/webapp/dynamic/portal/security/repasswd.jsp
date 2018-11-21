<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>

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

<body class=" login">
<!-- BEGIN LOGO -->
<div class="logo">


</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->


    <form style="padding-bottom:50px;padding-top:50px">
        <div class="form-title">
            <span class="form-subtitle">找回密码.</span>
        </div>
        <div id="J_Static2Quick_box" style="display:none;width:100%;text-align:center">


        </div>
        <div id="J_Quick2Static_box" style="display:block">

            <div class="form-group">

                <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                <label class="control-label visible-ie8 visible-ie9">账号</label>




                    <input class="form-control placeholder-no-fix" type="text" autocomplete="off"
                           placeholder="请输入注册使用的手机号" name="mobile" id="mobile" />

            </div>


            <div class="form-group">
                <label class="control-label visible-ie8 visible-ie9">验证码</label>
                <input class="form-control  placeholder-no-fix" type="text" name="j_captcha"
                       autocomplete="off" id="j_captcha"
                       placeholder="验证码" value=""/>



            </div>
            <div class="form-group-captcha">
                <label class="control-label visible-ie8 visible-ie9">验证码</label>
                <a href="#" id="flashImage"><img id="imageF" src="${pageContext.request.contextPath}/captcha/image.do?date=${date}"/>
                </a>
                </label>
            </div>
            <div class="form-actions">
                <button type="button" id="btn-resetpasswd" class="btn green btn-block uppercase">确定</button>
            </div>
        </div>


    </form>



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





<script type="text/javascript">
    jQuery(function ($) {

 $("#flashImage").click(
            function () {
                 $('#imageF').hide().attr('src',contextPath + '/captcha/image.do'+ '?' + Math.floor(Math.random() * 100)).fadeIn();
            });
        $('#btn-resetpasswd').on('click',function (e) {
                    $.ajax({
                            type: "post",
                            url: contextPath
                            + "/system/retrievePassword.do",
                            data: {
                                mobile: $('#mobile').val(),
                                j_captcha: $('#j_captcha').val()
                            },
                            beforeSend: function (XMLHttpRequest) {

                            },
                            success: function (rst, textStatus) {
                                alert(rst.errorMessage);
                                if(rst.status==1){
                                    $('#imageF').hide().attr('src',contextPath + '/captcha/image.do'+ '?' + Math.floor(Math.random() * 100)).fadeIn();
                                }

                            },
                            complete: function (XMLHttpRequest, textStatus) {

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
        border-bottom: 0px solid #eee;
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
    .content {
        border: 1px solid #eee;
    }
    .progress-bar-box {
        text-align:center;
    }
.login-box {
    width: 300px;
    color: #6c6c6c;
    background: #fff;
    position: relative;
    margin: 0 auto;
}

</style>


</html>