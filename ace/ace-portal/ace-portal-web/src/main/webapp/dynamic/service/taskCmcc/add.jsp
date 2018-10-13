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
									<input class="form-control" id="taskName" maxLength="20" style="width: 600px;" />
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
									<textarea style="width: 600px; height: 80px; line-height: 30px;" id="msg" maxLength="500" onkeyup="msgOnChange(this)"
									 class="form-control"></textarea>
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
		<div class="modal fade" role="dialog" id="modal-tree">
			<div class="modal-dialog" role="document" style="width:90%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body">
						<div class="tabbable">
							<ul class="nav nav-tabs padding-16" id="myTab4">
								<li class="active"><a data-toggle="tab" href="#dept"> <i class="green ace-icon glyphicon glyphicon-user bigger-125"></i>
										人士分组

									</a></li>


								<li><a data-toggle="tab" id="tab-group-free" href="#free">
										<i class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
										自由分组
									</a></li>
								<li><a data-toggle="tab" id="tab-group-tmp" href="#tmp">
										<i class="green ace-icon glyphicon glyphicon-file bigger-125"></i>
										临时
									</a></li>
							</ul>

							<div class="tab-content">
								<div id="dept" class="tab-pane in active">
									<div id="tree-dept-panel" class="easyui-panel" style="padding: 5px; width: 550px; height: 300px">
										<ul id="tree-dept"></ul>
									</div>
								</div>

								<div id="free" class="tab-pane">
									<div id="tree-free-panel" class="easyui-panel" style="padding: 5px; width: 550px; height: 300px">
										<ul id="tree-free"></ul>
									</div>
								</div>
								<div id="tmp" class="tab-pane">

									<select class="easyui-combogrid" style="width: 560px; height: 25px; line-height: 25px;" id="combogrid-tmp"></select>

									<div style="height:5px"></div>
									<div>
										<button class="btn green" id="btn-view-select-tmp" authority="false">
											添加
										</button>
										<button class="btn green" id="btn-view-remove-tmp" authority="false">
											全部清除
										</button>
										<button class="btn green" id="btn-view-remove-tmp-last" authority="false">
											删除最后
										</button>
									</div>
									<div style="height:5px"></div>
									<div id="task-content-tmp" class="easyui-panel" style="padding: 5px; width: 560px; height: 200px"></div>

								</div>

							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" authority="false">关闭</button>
						<button type="button" class="btn green" authority="false" id="btn-view-save">确定</button>
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
