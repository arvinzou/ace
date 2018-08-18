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
													<form class="form-horizontal" role="form" onsubmit="return false">
														<div class="form-body">
															<div class="form-group">
																<label class="col-md-2 control-label"><span class="label-red">*</span>课程名称</label>
																<div class="col-md-10">
																	<input type="text" class="form-control" name="courseName" maxlength="28" placeholder="请输入课程名称（建议字数在14个字以内，不超过28个字)">
																	<span class="help-block"></span>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label"><span class="label-red">*</span>课程封面</label>
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
																<label class="col-md-2 control-label"><span class="label-red">*</span>课程简介</label>
																<div class="col-md-10">
																	<div style="text-align:left"><textarea name="introduction" id="courseIntro" class="introduction"></textarea></div>
																	<span class="help-block"></span>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label"><span class="label-red">*</span>课程价格</label>
																<div class="col-md-10">
																	<div class="radio-group-container">
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="toasts" value="noPay" checked="" onclick="payTypeCheck('noPay');">免费
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="toasts" value="pay" checked="" onclick="payTypeCheck('pay');">付费
																			<span></span>
																		</label>
																		<div class="price-panel">
																			<div class="row">
																				<label class="col-md-3 control-label">课程原价</label>
																				<div class="col-md-9"><input name="primeCost" type="text" style="width:70%" class="form-control" placeholder="请输入课程原价（单位：元）"/></div>
																			</div>
																			<div class="row">
																				<label class="col-md-3 control-label">划线价格</label>
																				<div class="col-md-9">
																					<input name="cost" type="text" style="width:70%" class="form-control" placeholder="请输入划线价格（单位：元）"/>
																					<span class="help-block" style="text-align:left;font-size:12px;padding-top:10px">划线价是一种常见的促销方式，您可以通过设置划线价让您的课程价格看起来更加优惠低廉，吸引更多用户进行购买。</span>
																				</div>

																			</div>
																		</div>



																	</div>
																</div>
															</div>

															<div class="form-group">
																<label class="col-md-2 control-label"><span class="label-red">*</span>课程对象</label>
																<div class="col-md-10">
																	<div class="radio-group-container">
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="toasts" value="幼儿" >幼儿
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="toasts" value="小学">小学
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="toasts" value="中学">中学
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="toasts" value="高中">高中
																			<span></span>
																		</label>
																	</div>
																</div>
															</div>

															<div class="form-group">
																<label class="col-md-2 control-label"><span class="label-red">*</span>针对能力</label>
																<div class="col-md-10">

																	<div class="radio-group-container">
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="purport" value="心理能力" >心理能力
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="purport" value="学习方法">学习方法
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="purport" value="团队合作">团队合作
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="purport" value="沟通表达">沟通表达
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="purport" value="独立思考">独立思考
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="purport" value="自我认知">自我认知
																			<span></span>
																		</label>
																		<label class="mt-radio mt-radio-outline">
																			<input type="radio" name="purport" value="其他">其他
																			<span></span>
																		</label>
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label class="col-md-2 control-label">适合谁听</label>
																<div class="col-md-10">
																	<input type="text" class="form-control" maxlength="20"  placeholder="请输入适合谁听（您可以输入心理老师、心理学爱好者、父母等）">
																	<span class="help-block"> </span>
																</div>
															</div>


														</div>
														<div class="form-actions">
															<div class="row">
																<div class="col-md-offset-3 col-md-7">
																	<button class="btn btn-lg green" onclick="save();" style="width:30%">保存</button>
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

	.price-panel{
		width:100%;
		background-color: #edf2f74f;
		padding:20px;

	}
	.price-panel .row{
		padding-bottom:10px;
	}
	.radio-group-container{
        text-align:left;padding-top:7px;
    }
</style>
</html>