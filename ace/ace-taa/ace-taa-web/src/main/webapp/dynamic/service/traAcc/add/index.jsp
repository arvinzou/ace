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
        <link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
	</head>

	<body>

		<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />


		<!-- BEGIN SAMPLE TABLE PORTLET-->
		<div class="portlet light">

			<div class="portlet-body" >
				<div class="form-panel" id="fm-add-panel">
					<!--具体界面元素开始-->

				</div>

			</div>
		</div>

<script id="tpl-fm-add" type="text/template">
    <form class="form-horizontal" id="fm-add" role="form">
						<div class="form-body">
							<div class="form-group">
								<div class="form-group">
									<label class="col-md-2 control-label">
										事故发生地点
										<span class="required" aria-required="true"> * </span>
									</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="address" maxlength="200" placeholder="请选择事故发生地点" style="float:left;width:90%">
										
										<a href="javascript:window.open('${portalPath}/dynamic/common/map.jsp')" style="float:right;line-height: 34px;">选择</a>
										<span class="help-block"></span>
										<input name="latitude" type="hidden" />
										<input name="longitude" type="hidden" />
                                        <input name="areaCode" type="hidden" />
									</div>
								</div>


								<div class="form-group">
									<label class="col-md-2 control-label">
										天气
										<span class="required" aria-required="true"> * </span>
									</label>
									<div class="col-md-5">


                                        <div class="radio-group-container">
                                            {@each data['171'] as item, index}
                                            {@if item.CODE!=''}
                                            <label class="mt-radio mt-radio-outline">
                                                <input type="radio" name="weather" value="\${item.CODE}" >\${item.NAME}
                                                <span></span>
                                            </label>
                                            {@/if}
                                            {@/each}
                                        </div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										车型
										<span class="required" aria-required="true"> * </span>
									</label>
									<div class="col-md-5">
							
										
										<div class="radio-group-container">
											{@each data['172'] as item, index}
											{@if item.CODE!=''}
											<label class="mt-radio mt-radio-outline">
												<input type="radio" name="vehicleType" value="\${item.CODE}" >\${item.NAME}
												<span></span>
											</label>
											{@/if}
											{@/each}
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										事故时间
									</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="accidentTime">
										<span class="help-block"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										所属路段
									</label>
									<div class="col-md-5">
										<input type="text" class="form-control" style="width:450px" name="roadSectionId" maxlength="50" placeholder="请输入所属路段（建议字数在14个字以内，不超过50个字)">
										<span class="help-block"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										所属路长
									</label>
									<div class="col-md-5">
										<input type="text" class="form-control" style="width:450px" name="roadManId" maxlength="50" placeholder="请输入所属路长（建议字数在14个字以内，不超过50个字)">
										<span class="help-block"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										死亡人数
									</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="deadthToll" maxlength="10" placeholder="">
										<span class="help-block"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										受伤人数
									</label>
									<div class="col-md-5">
										<input type="text" class="form-control" name="injuries" maxlength="10" placeholder="">
										<span class="help-block"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">
										事故原因
									</label>
									<div class="col-md-5">
										<div class="radio-group-container">
											{@each data['173'] as item, index}
											{@if item.CODE!=''}
											<label class="mt-radio mt-radio-outline">
												<input type="radio" name="cause" value="\${item.CODE}" >\${item.NAME}
												<span></span>
											</label>
											{@/if}
											{@/each}
										</div>
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
					</form>

</script>

		<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
	</body>
	<jsp:include page="/dynamic/common/footer.jsp" />
	<script type="text/javascript" src="${portalPath}/content/common/js/jquery.form.js?version=${cfg.version}"></script>
	<script src="${portalPath}/content/common/assets/global/plugins/jquery-validation/js/jquery.validate.min.js?v=${cfg.version}"></script>
    <script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
    <link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
    <script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
    <script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>
    <script src="js/act.js?v=${cfg.version}"></script>
</html>
