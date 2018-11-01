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

        <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/simditor/styles/simditor.css" />
        <link rel="stylesheet" href="${portalPath}/content/common/jcrop/jquery.Jcrop.css">
		<link rel="stylesheet" href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
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

									名称
									<span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-6">
									<input type="text" class="form-control" name="name" value="\${data.o.name}" maxlength="50" placeholder="请输入名称（建议字数在30个字以内，不超过50个字)">
									<div class="error-name"></div>
									<input name="status" value="\${data.o.status}" type="hidden"/>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-2 control-label">

									封面
									<span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-10">
									<input type="hidden" name="cover" value="\${data.o.cover}">
									<div style="padding: 10px;">建议图片大小为480*270</div>
									<img style="max-width:280px;cursor:pointer;" id="cover" data-toggle="modal" data-xsize="480" data-ysize="270"
									 data-cover="cover" data-target="#img-uploader" src="\${data.o.cover}">
									 
									 
									 <div class="error-cover"></div>

								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">

									二维码
									<span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-10">
									<input type="hidden" name="qrcoteUrl" value="\${data.o.qrcoteUrl}">
									<div style="padding: 10px;">建议图片大小为200*200</div>
									<img style="max-width:200px;cursor:pointer;" id="qrcoteUrl" data-toggle="modal" data-xsize="400" data-ysize="400"
									 data-cover="qrcoteUrl" data-target="#img-uploader" src="\${data.o.qrcoteUrl}">
									<div class="error-qrcoteUrl"></div>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-2 control-label">

									摘要
									<span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-6">
									<textarea rows="10" cols="100" class="form-control"  name="remark" maxlength="500" placeholder="请输入摘要（建议字数在100个字以内，不超过500个字)">\${data.o.remark}</textarea>
									<div class="error-remark"></div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">
									处理人
									<span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-6">
									<select  name="openId" style="width:540px;line-height: 35px;height: 35px;">
            </select>
									<div class="error-openId"></div>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-2 control-label">
									微信处理通知模板
									<span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-6">
									<select name="tplCode" class="form-control">
										{@each data.dict136 as item, index}
											<option value="\${item.CODE}" {@if item.CODE==data.o.tplCode}selected{@/if}>\${item.NAME}</option>
										{@/each}
									</select>
									<div class="error-tplCode"></div>
								</div>
							</div>
							
							

							<div class="form-group">
								<label class="col-md-2 control-label">
									微信答复通知模板
									<span class="required" aria-required="true"> * </span>
								</label>
								<div class="col-md-6">
									<select name="answerTplCode" class="form-control">
										{@each data.dict136 as item, index}
											<option value="\${item.CODE}" {@if item.CODE==data.o.answerTplCode}selected{@/if}>\${item.NAME}</option>
										{@/each}
									</select>
									<div class="error-answerTplCode"></div>
								</div>
							</div>


						</div>
                                    
									<div class="form-actions">
											<div class="row">
													<div class="col-md-offset-3 col-md-7">
															<button class="btn  green" type="submit" style="width:30%">保存</button>
													</div>
											</div>
									</div>
    </script>

    <jsp:include page="/dynamic/common/footer.jsp" />
		
		<link rel="stylesheet" type="text/css"
			  href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
		<link rel="stylesheet" type="text/css"
			  href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
		<script type="text/javascript"
				src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
		<script type="text/javascript"
				src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
		
   <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
   <script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/js/upload.js"></script>
   <script src="${portalPath}/content/common/jcrop/jquery.Jcrop.min.js?v=${cfg.version}"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/module.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/hotkeys.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/uploader.js"></script>
   <script type="text/javascript" src="${portalPath}/content/common/simditor/scripts/simditor.js"></script>
<script src="js/act.js?v=${cfg.version}"></script>
    </html>
