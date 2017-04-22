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

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/content/common/js/jquery-easyui-1.3.6/themes/gray/easyui.css?version=${cfg.version}">
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
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<!-- #section:basics/sidebar.mobile.toggle -->
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<!-- /section:basics/sidebar.mobile.toggle -->
			<div class="navbar-header pull-left">
				<!-- #section:basics/navbar.layout.brand -->
				<a href="#" class="navbar-brand"> <small> <i
						class="fa fa-leaf"></i> ${cfg.sys_name}${cfg.version}
				</small>
				</a>

				<!-- /section:basics/navbar.layout.brand -->

				<!-- #section:basics/navbar.toggle -->

				<!-- /section:basics/navbar.toggle -->
			</div>

			<!-- #section:basics/navbar.dropdown -->
			<div class="navbar-buttons navbar-header pull-right"
				role="navigation" id="navigation">
				<ul class="nav ace-nav">
					<li class="grean"><a data-toggle="dropdown"
						class="dropdown-toggle" href="#"> <i
							class="ace-icon fa fa-tasks"></i> <span class="badge badge-danger"
							id="workflow-task-list-point">4</span>
					</a>

						<ul id="workflow-task-list"
							class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header"><i class="ace-icon fa fa-check"></i>
								4 Tasks to complete</li>

							<li><a href="#">
									<div class="clearfix">
										<span class="pull-left">Software Update</span> <span
											class="pull-right">65%</span>
									</div>

									<div class="progress progress-mini">
										<div style="width: 65%" class="progress-bar"></div>
									</div>
							</a></li>


							<li class="dropdown-footer"><a href="#"> See tasks with
									details <i class="ace-icon fa fa-arrow-right"></i>
							</a></li>
						</ul></li>
		
				
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
		</div>
		<!-- /.navbar-container -->
	</div>

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<!-- #section:basics/sidebar -->
		<div id="sidebar" class="sidebar                  responsive">
			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'fixed')
				} catch (e) {
				}
			</script>

			<div class="sidebar-shortcuts" id="sidebar-shortcuts">
				<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
					<button class="btn btn-success">
						<i class="ace-icon fa fa-signal"></i>
					</button>

					<button class="btn btn-info">
						<i class="ace-icon fa fa-pencil"></i>
					</button>

					<!-- #section:basics/sidebar.layout.shortcuts -->
					<button class="btn btn-warning" id="fa-calendar-set">
						<i class="ace-icon fa fa-calendar"></i>
					</button>

					<button class="btn btn-danger" id="fa-cloud-upload">
						<i class="ace-icon fa fa-cloud-upload"></i>
					</button>

					<!-- /section:basics/sidebar.layout.shortcuts -->
				</div>

				<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
					<span class="btn btn-success"></span> <span class="btn btn-info"></span>

					<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
				</div>
			</div>
			<!-- /.sidebar-shortcuts -->

			<ul class="nav nav-list" id="menu">

			</ul>
			<!-- /.nav-list -->

			<!-- #section:basics/sidebar.layout.minimize -->
			<div class="sidebar-toggle sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left"
					onclick="setTimeout('autoWidth()',100)"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>

			<!-- /section:basics/sidebar.layout.minimize -->
			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'collapsed')
				} catch (e) {
				}
			</script>
		</div>

		<!-- /section:basics/sidebar -->
		<div class="main-content" id="main-content">

			<!-- #section:settings.box -->
			<div class="ace-settings-container" id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
					id="ace-settings-btn">
					<i class="ace-icon fa fa-cog bigger-150"></i>
				</div>

				<div class="ace-settings-box clearfix" id="ace-settings-box">
					<div class="pull-left width-50">
						<!-- #section:settings.skins -->
						<div class="ace-settings-item">
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="no-skin" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; 选择皮肤</span>
						</div>

						<!-- /section:settings.skins -->

						<!-- #section:settings.navbar -->
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-navbar" /> <label class="lbl"
								for="ace-settings-navbar"> 固定导航栏</label>
						</div>

						<!-- /section:settings.navbar -->

						<!-- #section:settings.sidebar -->
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-sidebar" /> <label class="lbl"
								for="ace-settings-sidebar"> 固定侧边栏</label>
						</div>

						<!-- /section:settings.sidebar -->

						<!-- #section:settings.breadcrumbs -->
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-breadcrumbs" /> <label class="lbl"
								for="ace-settings-breadcrumbs"> 固定的面包屑</label>
						</div>

						<!-- /section:settings.breadcrumbs -->

						<!-- #section:settings.rtl -->
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-rtl" /> <label class="lbl"
								for="ace-settings-rtl"> 从右到左 (rtl)</label>
						</div>

						<!-- /section:settings.rtl -->

						<!-- #section:settings.container -->
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-add-container" /> <label class="lbl"
								for="ace-settings-add-container"> <b>容器局中</b>
							</label>
						</div>

						<!-- /section:settings.container -->
					</div>
					<!-- /.pull-left -->

					<div class="pull-left width-50">
						<!-- #section:basics/sidebar.options -->
						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-hover" /> <label class="lbl"
								for="ace-settings-hover"> 盘旋</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-compact" /> <label class="lbl"
								for="ace-settings-compact"> 紧凑的侧边栏</label>
						</div>

						<div class="ace-settings-item">
							<input type="checkbox" class="ace ace-checkbox-2"
								id="ace-settings-highlight" /> <label class="lbl"
								for="ace-settings-highlight"> Alt.活动项目</label>
						</div>


						<div  class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2"
								id="closeall" /> <label class="lbl"
								> 关闭全部选项卡</label>
						
						</div>
						<div  class="ace-settings-item">
						
						<input type="checkbox" class="ace ace-checkbox-2"
								id="closeother" /> <label class="lbl"
								> 关闭其他选项卡</label></div>

						<!-- /section:basics/sidebar.options -->
					</div>
					<!-- /.pull-left -->
				</div>
				<!-- /.ace-settings-box -->
			</div>
			<!-- /.ace-settings-container -->

			<script type="text/javascript">
				var screenHeight = window.innerHeight - 46;
				console.log(screenHeight);
				document
						.write('<div id="tt" class="easyui-tabs"  style="height:'+screenHeight+'px" fit="true"></div>');
			</script>
		</div>
		<!-- /.main-content -->


	</div>
	<!-- /.main-container -->
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
            src="${pageContext.request.contextPath}/content/portal/js/main/menu1.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/main/portal.js?version=${cfg.version}"></script>
		
		<script
		src="${pageContext.request.contextPath}/content/portal/js/main/workflow.js?version=${cfg.version}"></script>
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

			setTimeout("autobell()", 1000);

		}
	</script>

	<script type="text/javascript">
		function autobell() {
			var t1 = Math.floor(Math.random() * 10 + 1);
			var t2 = Math.floor(Math.random() * 10 + 1);
			//notification(t1);
			//message(t2);
		}
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/content/common/js/pushlet_js/ajax-pushlet-client.js?version=${cfg.version}"></script>
	<script type="text/javascript">
		/*PL.webRoot = contextPath + "/";
		PL._init();
		PL.joinListen('/message/plushlet');
		function onData(event) {

			console.log(event.get("status"));
			if (event.get("status") == '1') {
				//initTaskList()
			}
		}*/
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
		$(function(){
			
			 $("#fa-calendar-set").bind("click",function(){
				 //addPanel('日程安排','/portal/dynamic/portal/calendar/index.jsp?version=${cfg.version}',true);
			    });
			 
		});
		$('#tt').tabs({
		    border:false,
		    onSelect:function(title,index){
		        
		    }
		});
	</script>

</body>
</html>
