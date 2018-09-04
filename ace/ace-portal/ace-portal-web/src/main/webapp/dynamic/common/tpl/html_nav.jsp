<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div class="page-wrapper-row">
	<div class="page-wrapper-top">
		<div class="page-header">
			<div class="page-header-top">
				<div class="container">
					<div class="page-logo">
						<a href="index.html">
							<img src="${cfg.logo}" alt="logo" class="logo-default">
						</a>
					</div>


					<a href="#" class="navbar-brand">
						<small>
							${cfg.sys_name}${cfg.version}
						</small>
					</a>
					<div class="top-menu">
						<ul class="nav navbar-nav pull-right">
							<li class="dropdown dropdown-user dropdown-dark">
								<div  class="dropdown-toggle" data-toggle="dropdown"
									  data-hover="dropdown" data-close-others="true" style="cursor:pointer">
									<img alt="" class="img-circle"
										 src="${pageContext.request.contextPath}/content/common/assets/layouts/layout3/img/avatar9.jpg">
									<span class="username username-hide-mobile">${SESSION_USERPROP_KEY.name}</span>
								</div>


								<ul class="dropdown-menu dropdown-menu-default">
									<li><a href="${pageContext.request.contextPath}/dynamic/service/userCfg/index.jsp">
										<i class="ace-icon fa fa-cog"></i>
										个性化配置
									</a></li>
									<li>
										<a href="${pageContext.request.contextPath}/dynamic/portal/modifyPwd.jsp">
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
			<div class="page-header-menu">
				<div class="container">
					<div class="hor-menu ">
						<ul class="nav navbar-nav" id="menu">
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>