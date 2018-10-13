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
				<div class="profile-info-row">
					<div class="profile-info-name">任务名称：</div>

					<div class="profile-info-value">
						<input style="width: 600px; height: 25px; line-height: 25px;" id="taskName" maxLength="20" /><span style='color:red;font-size:16px;font-weight:800'>*</span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">发送给：</div>

					<div class="profile-info-value">
						<div id="task-content" class="easyui-panel" style="padding: 5px; width: 600px; height: 100px"></div><span style='color:red;font-size:16px;font-weight:800'>*</span>

					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"></div>

					<div class="profile-info-value">
						<button class="btn btn-purple" id="btn-view-select" authority="false">
							添加<i class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-purple" id="btn-view-remove" authority="false">
							全部清除<i class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-purple" id="btn-view-remove-last" authority="false">
							删除最后<i class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">发送给：</div>

					<div class="profile-info-value">
						<textarea style="width: 600px; height: 50px; line-height: 30px;" id="telext"></textarea>
						<div>多个手机号用;隔开</div>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">短信内容：</div>

					<div class="profile-info-value">
						<textarea style="width: 600px; height: 80px; line-height: 30px;" id="msg" maxLength="500" onkeyup="msgOnChange(this)"></textarea>
						<div id="msg-des">普通短信长度为70个字，超过后部分手机将会以多条方式接收</div>
					</div>
				</div>


				<div class="profile-info-row">
					<div class="profile-info-name"></div>
					<div class="profile-info-value">
						<button class="btn btn-info" id="btn-view-submit" authority="false">
							<i class="ace-icon fa fa-check bigger-110"></i> 发送
						</button>
					</div>
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
