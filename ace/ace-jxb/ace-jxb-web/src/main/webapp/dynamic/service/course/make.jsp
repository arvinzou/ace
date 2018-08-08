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
	<script src="${pageContext.request.contextPath}/dynamic/service/course/js/make.js?v=${cfg.version}"></script>
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
												课程制作
											</h3>
										</div>
										<div class="panel-body">
											<div class="row">
												<span class="title01">课程资源信息</span>
											</div>
											<div class="row">
												<div class="col-xs-12 col-md-2">音频上传</div>
												<div class="col-xs-12 col-md-10">
													<div class="pictureContainer" id="video" style="z-index: 1;">
														<div class="viewPicture">
															<video id="vedioSource" src="" controls="controls" style="width: 100%;height: 100%;"></video>
														</div>
														<div class="uploadText">
															<p class="imgiocn"><img src="img/video.png" /></p>
															<p class="uploadPloadprogress">点击上传封面</p>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-md-2">课程文稿</div>
												<div class="col-xs-12 col-md-10">
													<textarea name="coursedoc" id="coursedoc" class="coursedoc"></textarea>
												</div>
											</div>
											<div class="row">
												<div class="col-xs-12 col-md-2">是否允许试听</div>
												<div class="col-xs-12 col-md-10">
													<input type="radio" name="tried" value="1"/>是
													<input type="radio" name="tried" value="0"/>否
												</div>
											</div>
											<div class="row form_row">
												<div class="col-xs-12 col-md-2">课程时长</div>
												<div class="col-xs-12 col-md-10">
													<input name="duation" type="text" class="form_input" />
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