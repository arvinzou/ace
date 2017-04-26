<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>${cfg.sys_name}</title>

<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<script type="text/javascript">
	var contextPath = '${pageContext.request.contextPath}';
	var portalType='${SESSION_USERPROP_KEY.cfg.portalType}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/system/getUserProp.do"></script>
<!-- bootstrap & fontawesome -->
<script
	src="${pageContext.request.contextPath}/content/common/js/base.js?version=${cfg.version}"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/bootstrap.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-fonts.css?version=${cfg.version}" />

<!-- ace styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace.min.css?version=${cfg.version}" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/ace-part2.min.css?version=${cfg.version}" />
		<![endif]-->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-skins.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-rtl.min.css?version=${cfg.version}" />

<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/content/common/assets/css/ace-ie.min.css?version=${cfg.version}" />
		<![endif]-->




<!-- ace settings handler -->
<script
	src="${pageContext.request.contextPath}/content/common/assets/js/gz/ace-extra.min.js?version=${cfg.version}"></script>

<!-- HTML5 shim and Respond.js?version=${cfg.version} IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="${pageContext.request.contextPath}/content/common/assets/js/gz/html5shiv.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/common/assets/js/gz/respond.min.js?version=${cfg.version}"></script>
		<![endif]-->

<style>

.tabs-container {
	overflow: hidden;
}

.tabs-header {
	border-width: 1px;
	border-style: solid;
	border-bottom-width: 0;
	position: relative;
	padding: 0;
	padding-top: 2px;
	overflow: hidden;
}

.tabs-header-plain {
	border: 0;
	background: transparent;
}

.tabs-scroller-left,.tabs-scroller-right {
	position: absolute;
	top: auto;
	bottom: 0;
	width: 18px;
	font-size: 1px;
	display: none;
	cursor: pointer;
	border-width: 1px;
	border-style: solid;
}

.tabs-scroller-left {
	left: 0;
}

.tabs-scroller-right {
	right: 0;
}

.tabs-tool {
	position: absolute;
	bottom: 0;
	padding: 1px;
	overflow: hidden;
	border-width: 1px;
	border-style: solid;
}

.tabs-header-plain .tabs-tool {
	padding: 0 1px;
}

.tabs-wrap {
	position: relative;
	left: 0;
	overflow: hidden;
	width: 100%;
	margin: 0;
	padding: 0;
}

.tabs-scrolling {
	margin-left: 18px;
	margin-right: 18px;
}

.tabs-disabled {
	opacity: 0.3;
	filter: alpha(opacity = 30);
}

.tabs {
	list-style-type: none;
	height: 26px;
	margin: 0px;
	padding: 0px;
	padding-left: 4px;
	width: 5000px;
	border-style: solid;
	border-width: 0 0 1px 0;
}

.tabs li {
	float: left;
	display: inline-block;
	margin: 0 4px -1px 0;
	padding: 0;
	position: relative;
	border: 0;
}

.tabs li a.tabs-inner {
	display: inline-block;
	text-decoration: none;
	margin: 0;
	padding: 0 10px;
	height: 25px;
	line-height: 25px;
	text-align: center;
	white-space: nowrap;
	border-width: 1px;
	border-style: solid;
	-moz-border-radius: 5px 5px 0 0;
	-webkit-border-radius: 5px 5px 0 0;
	border-radius: 5px 5px 0 0;
}

.tabs li.tabs-selected a.tabs-inner {
	font-weight: bold;
	outline: none;
}

.tabs li.tabs-selected a:hover.tabs-inner {
	cursor: default;
	pointer: default;
}

.tabs li a.tabs-close,.tabs-p-tool {
	position: absolute;
	font-size: 1px;
	display: block;
	height: 12px;
	padding: 0;
	top: 50%;
	margin-top: -6px;
	overflow: hidden;
}

.tabs li a.tabs-close {
	width: 12px;
	right: 5px;
	opacity: 0.6;
	filter: alpha(opacity = 60);
}

.tabs-p-tool {
	right: 16px;
}

.tabs-p-tool a {
	display: inline-block;
	font-size: 1px;
	width: 12px;
	height: 12px;
	margin: 0;
	opacity: 0.6;
	filter: alpha(opacity = 60);
}

.tabs li a:hover.tabs-close,.tabs-p-tool a:hover {
	opacity: 1;
	filter: alpha(opacity = 100);
	cursor: hand;
	cursor: pointer;
}

.tabs-with-icon {
	padding-left: 18px;
}

.tabs-icon {
	position: absolute;
	width: 16px;
	height: 16px;
	left: 10px;
	top: 50%;
	margin-top: -8px;
}

.tabs-title {
	font-size: 12px;
}

.tabs-closable {
	padding-right: 8px;
}

.tabs-panels {
	margin: 0px;
	padding: 0px;
	border-width: 1px;
	border-style: solid;
	border-top-width: 0;
	overflow: hidden;
}

.tabs-header-bottom {
	border-width: 0 1px 1px 1px;
	padding: 0 0 2px 0;
}

.tabs-header-bottom .tabs {
	border-width: 1px 0 0 0;
}

.tabs-header-bottom .tabs li {
	margin: -1px 4px 0 0;
}

.tabs-header-bottom .tabs li a.tabs-inner {
	-moz-border-radius: 0 0 5px 5px;
	-webkit-border-radius: 0 0 5px 5px;
	border-radius: 0 0 5px 5px;
}

.tabs-header-bottom .tabs-tool {
	top: 0;
}

.tabs-header-bottom .tabs-scroller-left,.tabs-header-bottom .tabs-scroller-right
	{
	top: 0;
	bottom: auto;
}

.tabs-panels-top {
	border-width: 1px 1px 0 1px;
}

.tabs-header-left {
	float: left;
	border-width: 1px 0 1px 1px;
	padding: 0;
}

.tabs-header-right {
	float: right;
	border-width: 1px 1px 1px 0;
	padding: 0;
}

.tabs-header-left .tabs-wrap,.tabs-header-right .tabs-wrap {
	height: 100%;
}

.tabs-header-left .tabs {
	height: 100%;
	padding: 4px 0 0 4px;
	border-width: 0 1px 0 0;
}

.tabs-header-right .tabs {
	height: 100%;
	padding: 4px 4px 0 0;
	border-width: 0 0 0 1px;
}

.tabs-header-left .tabs li,.tabs-header-right .tabs li {
	display: block;
	width: 100%;
	position: relative;
}

.tabs-header-left .tabs li {
	left: auto;
	right: 0;
	margin: 0 -1px 4px 0;
	float: right;
}

.tabs-header-right .tabs li {
	left: 0;
	right: auto;
	margin: 0 0 4px -1px;
	float: left;
}

.tabs-header-left .tabs li a.tabs-inner {
	display: block;
	text-align: left;
	-moz-border-radius: 5px 0 0 5px;
	-webkit-border-radius: 5px 0 0 5px;
	border-radius: 5px 0 0 5px;
}

.tabs-header-right .tabs li a.tabs-inner {
	display: block;
	text-align: left;
	-moz-border-radius: 0 5px 5px 0;
	-webkit-border-radius: 0 5px 5px 0;
	border-radius: 0 5px 5px 0;
}

.tabs-panels-right {
	float: right;
	border-width: 1px 1px 1px 0;
}

.tabs-panels-left {
	float: left;
	border-width: 1px 0 1px 1px;
}

.tabs-header-noborder,.tabs-panels-noborder {
	border: 0px;
}

.tabs-header-plain {
	border: 0px;
	background: transparent;
}

.tabs-scroller-left {
	background: #f3f3f3 url('${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/images/tabs_icons.png') no-repeat 1px center;
}

.tabs-scroller-right {
	background: #f3f3f3 url('${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/images/tabs_icons.png') no-repeat -15px center;
}

.tabs li a.tabs-close {
	background: url('${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/images/tabs_icons.png') no-repeat -34px center;
}

.tabs li a.tabs-inner:hover {
	background: #e2e2e2;
	color: #000000;
	filter: none;
}

.tabs li.tabs-selected a.tabs-inner {
	background-color: #ffffff;
	color: #575765;
	background: -webkit-linear-gradient(top, #F8F8F8 0, #ffffff 100%);
	background: -moz-linear-gradient(top, #F8F8F8 0, #ffffff 100%);
	background: -o-linear-gradient(top, #F8F8F8 0, #ffffff 100%);
	background: linear-gradient(to bottom, #F8F8F8 0, #ffffff 100%);
	background-repeat: repeat-x;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#F8F8F8,
		endColorstr=#ffffff, GradientType=0 );
}

.tabs-header-bottom .tabs li.tabs-selected a.tabs-inner {
	background: -webkit-linear-gradient(top, #ffffff 0, #F8F8F8 100%);
	background: -moz-linear-gradient(top, #ffffff 0, #F8F8F8 100%);
	background: -o-linear-gradient(top, #ffffff 0, #F8F8F8 100%);
	background: linear-gradient(to bottom, #ffffff 0, #F8F8F8 100%);
	background-repeat: repeat-x;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,
		endColorstr=#F8F8F8, GradientType=0 );
}

.tabs-header-left .tabs li.tabs-selected a.tabs-inner {
	background: -webkit-linear-gradient(left, #F8F8F8 0, #ffffff 100%);
	background: -moz-linear-gradient(left, #F8F8F8 0, #ffffff 100%);
	background: -o-linear-gradient(left, #F8F8F8 0, #ffffff 100%);
	background: linear-gradient(to right, #F8F8F8 0, #ffffff 100%);
	background-repeat: repeat-y;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#F8F8F8,
		endColorstr=#ffffff, GradientType=1 );
}

.tabs-header-right .tabs li.tabs-selected a.tabs-inner {
	background: -webkit-linear-gradient(left, #ffffff 0, #F8F8F8 100%);
	background: -moz-linear-gradient(left, #ffffff 0, #F8F8F8 100%);
	background: -o-linear-gradient(left, #ffffff 0, #F8F8F8 100%);
	background: linear-gradient(to right, #ffffff 0, #F8F8F8 100%);
	background-repeat: repeat-y;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#ffffff,
		endColorstr=#F8F8F8, GradientType=1 );
}

.tabs li a.tabs-inner {
	color: #575765;
	background-color: #f3f3f3;
	background: -webkit-linear-gradient(top, #F8F8F8 0, #eeeeee 100%);
	background: -moz-linear-gradient(top, #F8F8F8 0, #eeeeee 100%);
	background: -o-linear-gradient(top, #F8F8F8 0, #eeeeee 100%);
	background: linear-gradient(to bottom, #F8F8F8 0, #eeeeee 100%);
	background-repeat: repeat-x;
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#F8F8F8,
		endColorstr=#eeeeee, GradientType=0 );
}

.tabs-header,.tabs-tool {
	background-color: #f3f3f3;
}

.tabs-header-plain {
	background: transparent;
}

.tabs-header,.tabs-scroller-left,.tabs-scroller-right,.tabs-tool,.tabs,.tabs-panels,.tabs li a.tabs-inner,.tabs li.tabs-selected a.tabs-inner,.tabs-header-bottom .tabs li.tabs-selected a.tabs-inner,.tabs-header-left .tabs li.tabs-selected a.tabs-inner,.tabs-header-right .tabs li.tabs-selected a.tabs-inner
	{
	border-color: #D3D3D3;
}

.tabs-p-tool a:hover,.tabs li a:hover.tabs-close,.tabs-scroller-over {
	background-color: #e2e2e2;
}

.tabs li.tabs-selected a.tabs-inner {
	border-bottom: 1px solid #ffffff;
}

.tabs-header-bottom .tabs li.tabs-selected a.tabs-inner {
	border-top: 1px solid #ffffff;
}

.tabs-header-left .tabs li.tabs-selected a.tabs-inner {
	border-right: 1px solid #ffffff;
}

.tabs-header-right .tabs li.tabs-selected a.tabs-inner {
	border-left: 1px solid #ffffff;
}
.tabs-icon {
	position: absolute;
	width: 16px;
	height: 16px;
	left: 10px;
	top: 50%;
	margin-top: -8px;
}

.tabs li a.tabs-close {
	background: url('${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/images/tabs_icons.png') no-repeat -34px center;
}
</style>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/jquery-ui.min.css?version=${cfg.version}" />
<style>
html {
	position: relative;
}

body {
	font-family: "微软雅黑";
	font-size: 12px;
}
</style>
</head>

<body class="no-skin">
<!-- #section:basics/navbar.layout -->
		<div id="navbar" class="navbar navbar-default    navbar-collapse       h-navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-leaf"></i>
							${cfg.sys_name}${cfg.version}
						</small>
					</a>

					<!-- /section:basics/navbar.layout.brand -->

					<!-- #section:basics/navbar.toggle -->
					<button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
						<span class="sr-only">Toggle user menu</span>


					</button>

					<button class="pull-right navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".sidebar">
						<span class="sr-only">Toggle sidebar</span>

						<span class="icon-bar"></span>

						<span class="icon-bar"></span>

						<span class="icon-bar"></span>
					</button>

					<!-- /section:basics/navbar.toggle -->
				</div>

				<!-- #section:basics/navbar.dropdown -->
				<div class="navbar-buttons navbar-header pull-right  collapse navbar-collapse" role="navigation">
					<ul class="nav ace-nav">



                    					<!-- #section:basics/navbar.user_menu -->
                    					<li class="light-blue"><a data-toggle="dropdown" href="#"
                    						class="dropdown-toggle"> <img class="nav-user-photo"
                    							src="${pageContext.request.contextPath}/content/common/assets/avatars/users.png"
                    							alt="Jason's Photo" /> <span class="user-info"> <small>欢迎,</small>
                    								${SESSION_USERPROP_KEY.name}
                    						</span> <i class="ace-icon fa fa-caret-down"></i>
                    					</a>

                    						<ul
                    							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">




                    							<li class="divider"></li>

                    							 <li><a href="javascript:userCfg();"
                                                								id="btn-view-user-cfg"> <i class="ace-icon fa fa-cog"></i>
                                                									个性化配置
                                                							</a></li>
                                                							<li><a href="javascript:modifyPasswd();"
                                                								id="btn-view-modify-passwd"> <i class="ace-icon fa fa-key"></i>
                                                									密码修改
                                                							</a></li>

                    							<li><a
                    								href="${pageContext.request.contextPath}/dynamic/portal/security/loginOut.jsp">
                    									<i class="ace-icon fa fa-power-off"></i> 安全退出
                    							</a></li>
                    						</ul></li>

                    					<!-- /section:basics/navbar.user_menu -->
                    				</ul>
				</div>

				<!-- /section:basics/navbar.dropdown -->
				<nav role="navigation" class="navbar-menu pull-left collapse navbar-collapse">
					<!-- #section:basics/navbar.nav -->

					<!-- /section:basics/navbar.nav -->

					<!-- #section:basics/navbar.form -->


					<!-- /section:basics/navbar.form -->
				</nav>
			</div><!-- /.navbar-container -->
		</div>

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<!-- #section:basics/sidebar.horizontal -->
			<div id="sidebar" class="sidebar h-sidebar navbar-collapse collapse">
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">


						<!-- /section:basics/sidebar.layout.shortcuts -->
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>

						<span class="btn btn-info"></span>

						<span class="btn btn-warning"></span>

						<span class="btn btn-danger"></span>
					</div>
				</div><!-- /.sidebar-shortcuts -->

				<ul class="nav nav-list" id="menu">

				</ul><!-- /.nav-list -->

				<!-- #section:basics/sidebar.layout.minimize -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>

				<!-- /section:basics/sidebar.layout.minimize -->
				<script type="text/javascript">
					try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
				</script>
			</div>

			<!-- /section:basics/sidebar.horizontal -->
			<div class="main-content" >
				<script type="text/javascript">
                				var screenHeight = window.innerHeight - 46;
                				if(portalType=='2'){
                                        screenHeight=window.innerHeight - 30;
                                    }
                				console.log(screenHeight);
                				document
                						.write('<div id="tt" class="easyui-tabs"  style="height:'+screenHeight+'px" fit="true"></div>');
                			</script>
			</div><!-- /.main-content -->

	<div id="dialog-message" class="hide">
		<form id="fm-password">
			<fieldset>
				新设密码： <input id="password" type="password" style="width: 200px;" />
			</fieldset>
			<fieldset>
				重复输入： <input id="repassword" type="password" style="width: 200px;" />
			</fieldset>
		</form>
	</div>

	<!-- basic scripts -->


	<!--[if lte IE 8]>
			<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery1x.min.js?version=${cfg.version}"></script>
		<![endif]-->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${pageContext.request.contextPath}/content/common/assets/js/gz/jquery.min.js?version=${cfg.version}'>"
								+ "<"+"/script>");
	</script>



	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='${pageContext.request.contextPath}/content/common/assets/js/gz/jquery.mobile.custom.min.js?version=${cfg.version}'>"
							+ "<"+"/script>");
	</script>
	<script
		src="${pageContext.request.contextPath}/content/common/assets/js/gz/bootstrap.min.js?version=${cfg.version}"></script>


	<!-- ace scripts -->
	<script
		src="${pageContext.request.contextPath}/content/common/assets/js/gz/ace-elements.min.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/assets/js/gz/ace.min.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/assets/js/gz/bootbox.min.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery-ui.min.js?version=${cfg.version}"></script>


	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
<script
            src="${pageContext.request.contextPath}/content/portal/js/main/menu2.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/main/portal.js?version=${cfg.version}"></script>
		

	<script
		src="${pageContext.request.contextPath}/content/common/js/dict_${SESSION_USERPROP_KEY.activeSyId}.js?version=${cfg.version}"></script>

	<script type="text/javascript">
	var activeSyId ='${SESSION_USERPROP_KEY.activeSyId}';
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
			$('iframe').width(w);
			autoWidth();
		}

		function autoWidth() {
			var w = $('#main-content').width();
			$('#tt').css("width", w);

			$('.tabs-header').css("width", w);
			$('.panel').css("width", w);

			$('.tabs-panels').css("width", w);
			$('.panel-body-noborder').css("width", w);
			$('iframe').width(w);

			//setTimeout("autobell()", 1000);

		}
	</script>



	<script type="text/javascript">
		function checkPwd() {
			var passwd = '${SystemUser.users.password}';
			if (passwd == '4297F44B13955235245B2497399D7A93'
					|| passwd == '4297f44b13955235245b2497399d7a93') {
				alert("${SystemUser.users.name}，你好！您目前使用的是默认密码为了确保安全，请您及时更换密码！");
				modifyPasswd();
			}
		}

		$('#tt').tabs({
		    border:false,
		    onSelect:function(title,index){
		        
		    }
		});
	</script>
<style>
.nav-list li>.arrow {
    display: none;
    position: absolute;
    top: 8px;
    right: 1px;
    z-index: 1027;
}
.panel-body {
    padding: 1px;
}
.sidebar.h-sidebar {
    position: relative;
    float: none !important;
    width: auto !important;
    margin-top: 7px;
    border-width: 0 !important;
    box-shadow: none;
}
</style>
</body>
</html>
