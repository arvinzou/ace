<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">


	<head>
		<meta charset="utf-8" />
		<title>${cfg.sys_name}</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<meta content="${cfg.sys_name}" name="${cfg.sys_name}" />
		<jsp:include page="/dynamic/common/header.jsp" />
		<link rel="stylesheet" href="css/style.css">

		<link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
	</head>

	<body>

		<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet light">

			<div class="portlet-body" id="courseSource">
				<div class="form-panel">
					<!--具体界面元素开始-->
					<form class="form-horizontal" id="fm-edit" role="form">

					</form>
				</div>
				<!--具体界面元素结束-->
			</div>
		</div>

		<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

	</body>
	<script id="tpl-fm" type="text/template">
		<div class="form-body">
		<div class="form-group">
		                <label class="col-md-2 control-label">
		
		                    多媒体类型
		                        </label>
		                <div class="col-md-10">
							
		                 
							
							<div class="radio-group-container">
									<label>
											<input type="radio" name="mediaType" value="2" \${data.o.mediaType==2?'checked':''}/>
											<span style="padding:10px">图文</span>
									</label>
									
									<label>
											<input type="radio" name="mediaType" value="3" \${data.o.mediaType==3?'checked':''}/>
											<span style="padding:10px">音频</span>
									</label>
									<label>
											<input type="radio" name="mediaType" value="1" \${data.o.mediaType==1?'checked':''}/>
											<span style="padding:10px">视频</span>
									</label>
									</div>
									<div class="error-category"></div>
							</div>
		                </div>
		            </div>
                             
             <div class="form-group">
                <label class="col-md-2 control-label">

                    内容
                                                         </label>
                <div class="col-md-10">
                    <textarea rows="10" class="form-control" name="content"  maxlength="500" placeholder="请输入内容（建议字数在100个字以内，不超过500个字)">\${data.o.content}</textarea>
                    <span class="help-block"></span>
                </div>
            </div>
			<div class="form-group">
			
			                <label class="col-md-2 control-label">
			
			                    图片
			                                                         </label>
																	
			                <div class="col-md-10">
							<div class="image-boxs my-gallery">

							{@each data.o.imageList as img, idx}
							
							                <div class="image-box">
												
												<img src="\${img.url}" class="cover"/>
								
												<img src="${portalPath}/content/common/image/remove.png" style="cursor:pointer;" class="delete"/>
													
											</div>
							
							                {@/each}
											</div>
											
			                    

			                </div>
                
			            </div>
						
						<div class="form-group">
						<label class="col-md-2 control-label"></label>
						<div class="col-md-10">
						<img style="max-width:480px;cursor:pointer;" id="imageSrc" data-toggle="modal" data-xsize="480" data-ysize="270" data-cover="imageSrc"
							data-target="#img-uploader" data-multi="true" src="${portalPath}/content/common/image/add.png">
						</div>

								
						</div>
                     
                     
                     <div class="form-group">
                <label class="col-md-2 control-label">

                    多媒体资源
                                                         </label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="mediaContent" value="\${data.o.mediaContent}" maxlength="200" placeholder="请输入多媒体资源">
                    <span class="help-block"></span>
                </div>
            </div>
                    
                     </div>
    <div class="form-actions">
        <div class="row">
            <div class="col-md-offset-3 col-md-7">
                <button class="btn   green" type="submit" style="width:30%">保存</button>
            </div>
        </div>
    </div>
    </script>


	<jsp:include page="/dynamic/common/footer.jsp" />
	<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
	<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
	<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
	<script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
	<script type="text/javascript" src="${portalPath}/content/common/js/upload.js"></script>
	<script src="js/act.js?v=${cfg.version}"></script>
</html>
