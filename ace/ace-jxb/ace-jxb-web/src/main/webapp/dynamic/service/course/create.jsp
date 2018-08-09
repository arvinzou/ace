<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
	<meta content="${cfg.sys_name}" name="description"/>
	<jsp:include page="../../common/base.jsp"/>
	<link rel="stylesheet" href="${portalPath}/content/common/assets/pages/css/profile.css">
	<link rel="stylesheet" href="${portalPath}/content/common/assets/css/font-awesome.min.css">
	<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${portalPath}/content/common/assets/global/css/components.min.css">
	<link rel="stylesheet" href="${portalPath}/content/common/assets/layouts/layout3/css/layout.min.css">
	<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/dynamic/service/course/css/upload.css"/>
	<link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/dynamic/service/course/css/create.css">
	<script src="${pageContext.request.contextPath}/content/common/js/loader.js?v=${cfg.version}"></script>
	<script src="${pageContext.request.contextPath}/dynamic/service/course/js/create.js?v=${cfg.version}"></script>
</head>

<body>

<div class="page-wrapper">

	<div class="page-wrapper-row full-height">
		<div class="page-wrapper-middle">
			<div class="page-container">
				<div class="page-content-wrapper">
					<div class="page-content">
						<div class="container">
							<ul class="page-breadcrumb breadcrumb">
								<li>
									<a href="index4.jsp">首页</a>
									<i class="fa fa-circle"></i>
								</li>
								<li>
									<span>仪表盘</span>
								</li>
							</ul>
							<div class="page-content-inner">

								<!---==============================================-->
								<div class="container">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">
												课程创建/编辑
											</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												<span class="title01">课程基本信息</span>
											</div>
											<div class="row form_row">
												<div class="col-xs-12 col-md-2">课程名称</div>
												<div class="col-xs-12 col-md-10"><input name="courseName" class="form_input" type="text" placeholder="请输入课程名称" /></div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-md-2">课程封面</div>
												<div class="col-xs-12 col-md-10">
													<div class="tips">建议图片尺寸750*420px或16:9，JPG、PNG、GIF格式，大小不超过2M</div>
													<div class="imgbox" >
														<img class="select_img form_imagePhotoUrl"
															 id="courseCover"
															 data-toggle="modal"
															 data-xsize="375" data-ysize="210"
															 data-cover="courseCover"
															 data-target="#img-uploader"
															 src="${pageContext.request.contextPath}/dynamic/service/course/img/course_default.jpg?v=${cfg.version}">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-md-2">课程简介</div>
												<div class="col-xs-12 col-md-10">
													<textarea name="introduction" id="courseIntro" class="introduction"></textarea>
												</div>
											</div>
											<div class="row form_row">
												<div class="col-xs-12 col-md-2">价格</div>
												<div class="col-xs-12 col-md-10">
													<div class="col-xs-1 col-md-1"><span id="noPay" class="feeLabel cactive" onclick="payTypeCheck('noPay');">免费</span></div>
													<div class="col-xs-1 col-md-1"><span id="pay" class="feeLabel uncactive" onclick="payTypeCheck('pay');">付费</span></div>
													<div class="col-xs-10 col-md-10">
														<input name="price" type="text" class="form_input" />
													</div>
												</div>
											</div>
											<div class="row form_row">
												<div class="col-xs-12 col-md-2">课程对象</div>
												<div class="col-xs-12 col-md-10">
													<span class="pointer cactive">幼儿</span>
													<span class="pointer uncactive">小学</span>
													<span class="pointer uncactive">中学</span>
													<span class="pointer uncactive">高中</span>
												</div>
											</div>
											<div class="row form_row">
												<div class="col-xs-12 col-md-2">适合谁听</div>
												<div class="col-xs-12 col-md-10"><input class="form_input" type="text" placeholder="请输入适合人群" /></div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-md-2">针对能力</div>
												<div class="col-xs-12 col-md-10">
													<div style="height:30px;margin-bottom: 30px;">
														<span class="ability cactive">心理能力</span>
														<span class="ability uncactive">学习方法</span>
														<span class="ability uncactive">团队合作</span>
														<span class="ability uncactive">沟通表达</span>
														<span class="ability uncactive">独立思考</span>
														<span class="ability uncactive">自我认知</span>
														<span class="ability uncactive">其他</span>
													</div>
													<div style="width:100%;">
														<textarea class="ability_intro"></textarea>
													</div>
												</div>
											</div>
											<div class="row form_row">
												<div class="col-xs-12 col-md-2">起始人气(选填)</div>
												<div class="col-xs-12 col-md-10">
													<input type="text" class="form_input" />
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-md-2">状态设置</div>
												<div class="col-xs-12 col-md-10">
													<input type="radio" value="" name="status"/>上架
													<input type="radio" value="" name="status"/>下架
												</div>
											</div>
											<div class="row" style="text-align: center;">
												<button class="save" onclick="save();">保存</button>
											</div>
										</div>
									</div>
								</div>
								<!--=======================================-->

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="bottom"></div>

</div>

</body>

<style>
	.modal .headbox {
		width: 150px !important;
		height: 150px !important;
		border-radius: 50% !important;
		overflow: hidden;
		margin: 0 auto;
	}

	.modal-body {
		font-size: 16px;
		line-height: 24px;
		text-align: justify
	}

	.modal img {
		width: 100%;
		height: 100%;
	}
</style>
</html>