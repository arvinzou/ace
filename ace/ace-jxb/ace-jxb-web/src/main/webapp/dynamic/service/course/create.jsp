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
									<a href="${pageContext.request.contextPath}/index.jsp">首页</a>
									<i class="fa fa-circle"></i>
								</li>
								<li>
									<span>课程管理</span>
								</li>
							</ul>
							<div class="page-content-inner">

								<!---==============================================-->
								<div class="row">
									<div class="col-md-12">
										<!-- BEGIN SAMPLE TABLE PORTLET-->
										<div class="portlet light">
											<div class="portlet-title">
												<div class="caption">
													创建课程
												</div>
												<div class="actions">

												</div>
											</div>
											<div class="portlet-body" id="courseSource">
												<div class="form-panel">
												<!--具体界面元素开始-->
													<form class="form-horizontal" role="form">
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-2 control-label">课程名称</label>
																<div class="col-md-10">
																	<input type="text" class="form-control" name="courseName" placeholder="请输入课程名称（建议字数在14个字以内，不超过28个字">
																	<span class="help-block"></span>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label">课程封面</label>
																<div class="col-md-10">
																	<div class="imgbox" >
																		<img class="select_img form_imagePhotoUrl"
																			 id="courseCover"
																			 data-toggle="modal"
																			 data-xsize="375" data-ysize="210"
																			 data-cover="courseCover"
																			 data-target="#img-uploader"
																			 src="${pageContext.request.contextPath}/dynamic/service/course/img/course_default.jpg?v=${cfg.version}">
																	</div>
																	<div class="tips">建议图片尺寸750*420px或16:9，JPG、PNG、GIF格式，大小不超过2M</div>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label">课程简介</label>
																<div class="col-md-10">
																	<textarea name="introduction" id="courseIntro" class="introduction"></textarea>
																	<span class="help-block"> </span>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label">课程价格</label>
																<div class="col-md-10">
																	<div class="col-xs-1 col-md-1"><span id="noPay" class="feeLabel cactive" onclick="payTypeCheck('noPay');">免费</span></div>
																	<div class="col-xs-1 col-md-1"><span id="pay" class="feeLabel uncactive" onclick="payTypeCheck('pay');">付费</span></div>
																	<div class="col-xs-10 col-md-10">
																		<input name="price" type="text" class="form_input" />
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label">课程对象</label>
																<div class="col-md-10">
																	<span class="pointer cactive">幼儿</span>
																	<span class="pointer uncactive">小学</span>
																	<span class="pointer uncactive">中学</span>
																	<span class="pointer uncactive">高中</span>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label">课程名称</label>
																<div class="col-md-10">
																	<input type="text" class="form-control" name="courseName" placeholder="请输入课程名称">
																	<span class="help-block"> </span>
																</div>
															</div>
														</div>
													</form>
												</div>
												<!--具体界面元素结束-->
											</div>
										</div>
										<!-- END SAMPLE TABLE PORTLET-->
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
	.form-panel{
		width: 70%;
		text-align: center;
		margin: auto;
	}
	.form-control {
		display: block;
		width: 100%;
		height: 40px;
		padding: 6px 12px;
		font-size: 14px;
		line-height: 1.42857;
		color: #555555;
		background-color: #fff;
		background-image: none;
		border: 1px solid #ebedf2;
		border-radius: .25rem;
	}
	.form-group {
    	margin-bottom: 20px;
	}
</style>
</html>