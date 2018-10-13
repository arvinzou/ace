<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<title>短信发送</title>
	</head>
	<jsp:include page="/dynamic/common/header.jsp" />

	<body>
		<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
		<div class="portlet light ">
			<div class="portlet-body">
				<div class="form-panel">
					<!--具体界面元素开始-->
					<form class="form-horizontal" id="fm-add" role="form">
						<div class="form-body">
							<div class="form-group">
								<div class="col-md-2 control-label">任务名称<span class="required">*</span></div>

								<div class="col-md-10">
									<input class="form-control" id="taskName" maxLength="20" style="width: 600px;"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2 control-label">发送给<span class="required">*</span></div>

								<div class="col-md-10">
									<div id="task-content" class="easyui-panel" style="padding: 5px; width: 600px; height: 100px"></div>

								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2 control-label"></div>

								<div class="col-md-10">
									<button class="btn green" id="btn-view-select" authority="false">
										添加
									</button>
									<button class="btn green" id="btn-view-remove" authority="false">
										全部清除
									</button>
									<button class="btn green" id="btn-view-remove-last" authority="false">
										删除最后
									</button>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2 control-label">发送给</div>

								<div class="col-md-10">
									<textarea style="width: 600px; height: 50px; line-height: 30px;" id="telext" class="form-control"></textarea>
									<div>多个手机号用;隔开</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2 control-label">短信内容<span class="required">*</span></div>

								<div class="col-md-10">
									<textarea style="width: 600px; height: 80px; line-height: 30px;" id="msg" maxLength="500" onkeyup="msgOnChange(this)" class="form-control"></textarea>
									<div id="msg-des">普通短信长度为70个字，超过后部分手机将会以多条方式接收</div>
								</div>
							</div>
							<div class="form-actions">
								<div class="row">
									<div class="col-md-offset-3 col-md-7">
										<button class="btn   green" type="submit" style="width:30%" id="btn-view-submit">发送</button>
									</div>
								</div>
							</div>


						</div>
					</form>
				</div>

			</div>
		</div>

		<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
		<jsp:include page="/dynamic/common/footer.jsp" />

		<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/metro/easyui.css?version=${cfg.version}">
		<link rel="stylesheet" type="text/css" href="${portalPath}/content/common/js/jquery-easyui-1.3.6/themes/icon.css?version=${cfg.version}">
		<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/gz/jquery.easyui.min.js?version=${cfg.version}"></script>
		<script type="text/javascript" src="${portalPath}/content/common/js/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js?version=${cfg.version}"></script>


		<script src="${pageContext.request.contextPath}/content/service/taskCmcc/act.js?version=${cfg.version}"></script>
		<div class="modal fade" role="dialog" id="modal-preview">
			<div class="modal-dialog" role="document" style="width:90%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">详细</h4>
					</div>
					<div class="modal-body">

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<style>
			.layout-user {
				width: 150px;
				height: 20px;
				float: left;
				margin: 1px 1px 1px;
			}
		</style>
	</body>
</html>
