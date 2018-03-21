<%@page import="org.platform.snail.beans.SystemUser"%>
<%@page import="org.platform.snail.utils.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>基本信息</title>
</head>

<jsp:include page="../../common/common.jsp" />
<style>
.layout-user {
	width: 100px;
	height: 30px;
	float: left;
	margin: 2px 2px 2px;
}
</style>

<body>
	<div class="page-content">
					<!-- PAGE CONTENT BEGINS -->
							<div class="row">
													<div class="col-xs-12 col-sm-6 widget-container-col">
		<!-- #section:custom/widget-box -->
									<div class="widget-box">
										<div class="widget-header">
											<h5 class="widget-title">待办的任务</h5>

											<!-- #section:custom/widget-box.toolbar -->
											<div class="widget-toolbar">
												<div class="widget-menu">
													<a href="#" data-action="settings" data-toggle="dropdown">
														<i class="ace-icon fa fa-bars"></i>
													</a>

													<ul class="dropdown-menu dropdown-menu-right dropdown-light-blue dropdown-caret dropdown-closer">
														<li>
															<a data-toggle="tab" href="#dropdown1">Option#1</a>
														</li>

														<li>
															<a data-toggle="tab" href="#dropdown2">Option#2</a>
														</li>
													</ul>
												</div>

												<a href="#" data-action="fullscreen" class="orange2">
													<i class="ace-icon fa fa-expand"></i>
												</a>

												<a href="#" data-action="reload">
													<i class="ace-icon fa fa-refresh"></i>
												</a>

												<a href="#" data-action="collapse">
													<i class="ace-icon fa fa-chevron-up"></i>
												</a>

												<a href="#" data-action="close">
													<i class="ace-icon fa fa-times"></i>
												</a>
											</div>

											<!-- /section:custom/widget-box.toolbar -->
										</div>

										<div class="widget-body">
											<div class="widget-main" style="height:300px">	
											<table id="myTaskList" class="datalist">
			<thead>
				<tr>
					<td>序号</td>
					<td>名称</td>
					<td>评测对象</td>
					<td>状态</td>
					<td>截止时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="task-content1">

			</tbody>
		</table>			
									
</div></div></div>
										
								</div><!-- /.span -->
								<div class="col-xs-12 col-sm-6 widget-container-col">
									<!-- #section:custom/widget-box -->
									<div class="widget-box">
										<div class="widget-header">
											<h5 class="widget-title">结束的任务</h5>

											<!-- #section:custom/widget-box.toolbar -->
											<div class="widget-toolbar">
												<div class="widget-menu">
													<a href="#" data-action="settings" data-toggle="dropdown">
														<i class="ace-icon fa fa-bars"></i>
													</a>

													<ul class="dropdown-menu dropdown-menu-right dropdown-light-blue dropdown-caret dropdown-closer">
														<li>
															<a data-toggle="tab" href="#dropdown1">Option#1</a>
														</li>

														<li>
															<a data-toggle="tab" href="#dropdown2">Option#2</a>
														</li>
													</ul>
												</div>

												<a href="#" data-action="fullscreen" class="orange2">
													<i class="ace-icon fa fa-expand"></i>
												</a>

												<a href="#" data-action="reload">
													<i class="ace-icon fa fa-refresh"></i>
												</a>

												<a href="#" data-action="collapse">
													<i class="ace-icon fa fa-chevron-up"></i>
												</a>

												<a href="#" data-action="close">
													<i class="ace-icon fa fa-times"></i>
												</a>
											</div>

											<!-- /section:custom/widget-box.toolbar -->
										</div>

										<div class="widget-body">
											<div class="widget-main" style="height:300px">
												<table id="myTaskList" class="datalist">
			<thead>
				<tr>
					<td>序号</td>
					<td>名称</td>
					<td>评测对象</td>
					<td>状态</td>
					<td>截止时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody id="task-content2">

			</tbody>
		</table>	
											</div>
										</div>
									</div>

									<!-- /section:custom/widget-box -->
								</div>

		
							</div><!-- /.row -->
		
	</div>
	<style type="text/css">
	.layout-user {
	width: 130px;
	height: 40px;
	float: left;
	margin: 2px 2px 2px;
}
a {
	color: blue;
	text-decoration: none;
}

a:link {
	color: blue;
	text-decoration: none;
}

a:visited {
	color: blue;
	text-decoration: none;
}

a:hover {
	color: #ff2020;
	text-decoration: underline;
}

a:actived {
	color: #2c850d;
	text-decoration: none;
}

.datalist {
	border: 1px solid #FCFCFC; /* 表格边框 */
	font-family: Arial;
	border-collapse: collapse; /* 边框重叠 */
	background-color: #ffffff; /* 表格背景色 */
}

.datalist th {
	border: 1px solid #000000; /* 行名称边框 */
	background-color: #ffffff; /* 行名称背景色 */
	color: #000000; /* 行名称颜色 */
	font-weight: bold;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 12px;
	padding-right: 12px;
	text-align: left;
}

.datalist td {
	border: 1px solid #FCFCFC; /* 单元格边框 */
	text-align: left;
	padding-top: 4px;
	padding-bottom: 4px;
	padding-left: 10px;
	padding-right: 10px;
}
</style>
	<!-- /section:elements.tab.option -->
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/index-vote.js"></script>


	<jsp:include page="../../common/footer-2.jsp" />
</body>
</html>