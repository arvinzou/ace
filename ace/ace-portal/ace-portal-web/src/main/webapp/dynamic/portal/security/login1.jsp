<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/bootstrap.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/font-awesome.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-fonts.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace-rtl.min.css?version=${cfg.version}" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/content/common/assets/css/ace.onpage-help.css?version=${cfg.version}" />
<script type="text/javascript">
	if (!window.console)
		window.console = {};
	if (!window.console.log)
		window.console.log = function() {
		};
</script>
<!--[if lte IE 8]>
			<script type="text/javascript" src="${pageContext.request.contextPath}/content/common/assets/js/gz/jquery1x.min.js?version=${cfg.version}"></script>
		<![endif]-->
<script type="text/javascript">
	window.jQuery
			|| document
					.write("<script src='${pageContext.request.contextPath}/content/common/assets/js/gz/jquery.min.js?version=${cfg.version}'>"
							+ "<"+"/script>");
</script>
<script
	src="${pageContext.request.contextPath}/content/common/assets/js/gz/bootstrap.min.js?version=${cfg.version}"></script>
<script
	src="${pageContext.request.contextPath}/content/common/assets/js/gz/bootbox.min.js?version=${cfg.version}"></script>
<style type="text/css">
body {
	background-color: #FFFFFF;
	font-family: "微软雅黑";
	font-size: 1.2em;
}
</style>

</head>
<body class="login-layout" onload="onLoad()">
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
	<script type="text/javascript">
		var contextPath = '${pageContext.request.contextPath}';
	</script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/security/login.js?version=${cfg.version}"></script>

	<div class="main-container" id="main_container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="center">
						<h1>
							<i class="ace-icon fa fa-leaf green"></i> <span class="red"></span>
							<span class="white" style="font-family: '微软雅黑'" id="id-text2">${cfg.sys_name}</span>
						</h1>
						<h4 class="blue" id="id-company-text"></h4>
					</div>

					<div class="space-6"></div>
				</div>
			</div>
		</div>
		<div
			style="background-image: url('${pageContext.request.contextPath}${cfg.sys_login_bg_img}');no-repeat top center;background-size: 100% 100%;height:400px">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div style="height: 40px"></div>
						<div class="login-container">
							<div class="position-relative">
								<div id="login-box"
									class="login-box visible widget-box no-border">


									<div class="widget-body">
										<div class="widget-main">

											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i> 请输入您的信息
											</h4>
											<div class="space-6"></div>

											

											<form class="login_form" id="login_form" name="login_form"
												action="${pageContext.request.contextPath}/j_spring_security_check"
												method="post">

												<h4 class="heade lighter">
													<span style="color: red">
														${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
														${sessionScope['j_captcha_error']} </span>

												</h4>
												<!-- 
												<label> <input name="login-type" type="radio"
												class="ace" checked="checked" value="0"/> <span class="lbl">
													协会</span>
											</label> <label> <input name="login-type" type="radio"
												class="ace" value="1"/> <span class="lbl"> 会员</span>
											</label> <label> <input name="login-type" type="radio"
												class="ace" value="2"/> <span class="lbl"> 企业</span>
											</label> -->
												<fieldset>
													<label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="text" class="form-control" name="j_username"
															id="j_username" placeholder="用户名" value="${username}" />
															<i class="ace-icon fa fa-user"></i>
															
													</span>
													</label> <label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="password" class="form-control" name="j_password"
															id="j_password" placeholder="密码" value="${password}" />
															<i class="ace-icon fa fa-lock"></i>
													</span>
													</label>
													<label class="block clearfix"> <span
														class="input-icon" style="width: 150px"> <input type="text"
															class="form-control" name="j_captcha" id="j_captcha"
															placeholder="验证码" value="" /> <i
															class="ace-icon fa fa-leaf"></i>
													</span> <span> <img id="imageF"
															src="${pageContext.request.contextPath}/captcha/image.do?date=${date}" />
															<a href="#" id="flashImage">换一张</a>
													</span>


													</label>
													<div class="space"></div>

													<div class="clearfix">
														<label class="inline"> <!--[if lte IE 8]>
													<input type="checkbox"  name="ch" ${ch} /> 
												<![endif]--> <!--[if gte IE 9]>
													<input type="checkbox" class="ace" name="ch" ${ch} /> 
												<![endif]--> <![if !IE]> <input type="checkbox" class="ace"
															name="remember_me" value="true" /> <![endif]>
															<span class="lbl"> 两周之内记住我 </span>
														</label>

														<button type="button" id="btn-login-submit"
															class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i> <span
																class="bigger-110">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
											<div class="space-6"></div>

										</div>
										<!-- /.widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="#" data-target="#forgot-box"
													class="forgot-password-link"> <i
													class="ace-icon fa fa-arrow-left"></i> 忘记密码了
												</a>
												
												
											</div>
											
											<div>
												<a href="/kernel/dynamic/www/reg/index.jsp" class="user-signup-link">
													企业注册
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
											

											
										</div>
									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.login-box -->
								<div id="progress-bar-box"
									class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i> 系统提示
											</h4>

											<div class="space-6"></div>

											<p>
												<font color="#000033"><b> 系统信息：</b></font><b><font
													color="#FF0000">登录中……</font></b>
											</p>


										</div>
										<!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box"
												class="back-to-login-link"> Back to login <i
												class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.progress-bar-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>找回密码
											</h4>

											<div class="space-6"></div>
											<p>输入注册的Email</p>
											<div class="space-6"></div>
											<form>
												<fieldset>
													<label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="email" name="email" id="email" class="form-control"
															placeholder="Email" /> <i
															class="ace-icon fa fa-envelope"></i>
													</span>
													</label>



													<div class="clearfix">
														<button type="button" id="btn-login-rp"
															class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon fa fa-lightbulb-o"></i> <span
																class="bigger-110">发送</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div>
										<!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box"
												class="back-to-login-link"> 返回登录 <i
												class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main"></div>

										<div class="toolbar center">
											<a href="#" data-target="#login-box"
												class="back-to-login-link"> <i
												class="ace-icon fa fa-arrow-left"></i> Back to login
											</a>
										</div>
									</div>
									<!-- /.widget-body -->
								</div>
								<!-- /.signup-box -->
							</div>
							<!-- /.position-relative -->

							<div class="navbar-fixed-top align-right">
								<br /> &nbsp; <a id="btn-login-dark" href="#">Dark</a> &nbsp; <span
									class="blue">/</span> &nbsp; <a id="btn-login-blur" href="#">Blur</a>
								&nbsp; <span class="blue">/</span> &nbsp; <a
									id="btn-login-light" href="#">Light</a> &nbsp; &nbsp; &nbsp;
							</div>
						</div>
						<!--  end login-container-->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
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
	<!-- /.main-container -->

</body>
</html>




