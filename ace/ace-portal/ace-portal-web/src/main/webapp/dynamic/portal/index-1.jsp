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
<head>
    <meta charset="utf-8"/>
    <title>${cfg.sys_name}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1" name="viewport"/>
    <meta content="${cfg.sys_name}"
          name="description"/>
    <meta content="" name="author"/>
    <script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
	var layoutTopHeight=0;
	var activeSyId ='${SESSION_USERPROP_KEY.activeSyId}';

    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/system/getUserProp.do"></script>
    <!-- bootstrap & fontawesome -->
    <script
            src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"></script>


    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}"/>
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
    <link href="${pageContext.request.contextPath}/content/common/assets/global/css/components.min.css" rel="stylesheet"
          id="style_components" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/global/css/plugins.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/layout.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/themes/darkblue.min.css"
          rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${pageContext.request.contextPath}/content/common/assets/layouts/layout/css/custom.min.css"
          rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="favicon.ico"/>

    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/easyui.css?version=${cfg.version}">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">


</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white">
<div class="page-wrapper">
    <div class="page-header navbar navbar-fixed-top">
        <div class="page-header-inner ">
            <div class="page-logo">
                <a href="#">
                    <img src="${pageContext.request.contextPath}/content/common/assets/layouts/layout/img/logo.png"
                         alt="logo" class="logo-default"/> </a>
                <div class="menu-toggler sidebar-toggler" onclick="setTimeout('autoWidth()',100)">
                    <span></span>
                </div>
            </div>

            <a href="#" class="navbar-brand" style="color:#FFFFFF">
                <small>

                    ${cfg.sys_name}${cfg.version}
                </small>
            </a>
            <div class="top-menu">
                <ul class="nav navbar-nav pull-right">
                    <li class="dropdown dropdown-user">
                        <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                           data-close-others="true">
                            <img alt="" class="img-circle"
                                 src="${pageContext.request.contextPath}/content/common/assets/layouts/layout/img/avatar3_small.jpg"/>
                            <span class="username username-hide-on-mobile"> ${SESSION_USERPROP_KEY.name} </span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-default">
                            <li><a href="javascript:userCfg();">
                                <i class="ace-icon fa fa-cog"></i>
                                个性化配置
                            </a></li>
                            <li>
                                <a data-target="#stack1" data-toggle="modal">
                                    <i class="ace-icon fa fa-key"></i> 密码修改
                                </a>
                            </li>
                            <li class="divider"></li>

                            <li>
                                <a href="${pageContext.request.contextPath}/dynamic/portal/security/loginOut.jsp">
                                    <i class="icon-key"></i> 安全退出 </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="clearfix"></div>

    <div class="page-container">
        <div class="page-sidebar-wrapper">

            <div class="page-sidebar navbar-collapse collapse">
                <ul id="menu" class="page-sidebar-menu  page-header-fixed " data-keep-expanded="false"
                    data-auto-scroll="true"
                    data-slide-speed="200" style="padding-top: 0px">

                </ul>
            </div>
        </div>
        <div class="page-content-wrapper">
            <div class="page-content" id="main-content">
                <script type="text/javascript">
				var screenHeight = window.innerHeight - 46;
				console.log(screenHeight);
				document
						.write('<div id="tt" class="easyui-tabs"  style="height:'+screenHeight+'px" fit="true"></div>');

                </script>
                <!-- 陈晓克-->
            </div>


        </div>
    </div>
</div>
<div id="stack1" class="modal fade" tabindex="-1" data-width="300">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">密码修改</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <form id="fm-password">
                            <fieldset>
                                新设密码： <input id="password" type="password" style="width: 200px;"/>
                            </fieldset>
                            <fieldset>
                                重复输入： <input id="repassword" type="password" style="width: 200px;"/>
                            </fieldset>
                        </form>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn blue" onclick="submitform()">提交</button>
                <button type="button" data-dismiss="modal" class="btn red">取消</button>
            </div>
        </div>
    </div>
</div>
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/ie8.fix.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/jquery.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/plugins/bootstrap/js/bootstrap.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/global/scripts/app.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/layouts/layout/scripts/layout.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/content/common/assets/pages/scripts/ui-modals.min.js"
        type="text/javascript"></script>
<script
        src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery-ui.min.js?version=${cfg.version}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/main/menu1.js?version=${cfg.version}"></script>
<script
        src="${pageContext.request.contextPath}/content/portal/js/main/portal.js?version=${cfg.version}"></script>


<script
        src="${pageContext.request.contextPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

<script type="text/javascript">

		window.onresize = function() {
			autosize();
		}
		function autosize(){
			var h = window.innerHeight - 36;
			var w = window.innerWidth - 150;
			if(portalType=='2'){
              w = window.innerWidth
            }
			$('#tt').css("height", h);
			$('#tt').css("width", w);

			$('.tabs-header').css("width", w);
			$('.panel').css("width", w);

			$('.tabs-panels').css("height", h);
			$('.tabs-panels').css("width", w);

			$('.panel-body-noborder').css("height", h);
			$('.panel-body-noborder').css("width", w);

			$('iframe').height(h - 20);
			//$('iframe').width(w);
			autoWidth();
		}

		function autoWidth() {
			var w = $('#main-content').width();
			$('#tt').css("width", w);

			$('.tabs-header').css("width", w);
			$('.panel').css("width", w);
			$('.tabs-wrap').css("width", w);


			$('.tabs-panels').css("width", w);
			$('.panel-body-noborder').css("width", w);
			//$('iframe').width(w);

			//setTimeout("autobell()", 1000);

		}


</script>
<script type="text/javascript">
		function checkPwd() {
			var passwd = '${SystemUser.users.password}';
			if (passwd == '4297F44B13955235245B2497399D7A93'
					|| passwd == '4297f44b13955235245b2497399d7a93') {
				alert("${SystemUser.users.name}，你好！您目前使用的是默认密码为了确保安全，请您及时更换密码！");
				$('#stack1').modal('show')
			}
		}

</script>
<style>
 .page-content-wrapper .page-content {
    margin-left: 200px;
    margin-top: 0;
    min-height: 600px;
    padding: 0px 0px 0px;
}
.page-sidebar {
    width: 200px;
    float: left;
    position: relative;
    margin-right: -100%;
}
.tabs-header, .tabs-tool {
    background-color: #ffffff;
}
a, button, code, div, img, input, label, li, p, pre, select, span, svg, table, td, textarea, th, ul {
    -webkit-border-radius: 0!important;
    -moz-border-radius: 0!important;
    border-radius: 2px!important;
}

</style>
</body>

</html>