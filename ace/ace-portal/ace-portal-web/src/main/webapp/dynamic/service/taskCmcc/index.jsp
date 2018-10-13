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
	<link rel="stylesheet" href="${portalPath}/content/common/jqGrid/jqGrid.css?v=${cfg.version}" />
	<link href="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"
	 rel="stylesheet" type="text/css" />
	<body>
		<jsp:include page="/dynamic/common/prefix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />
		<div class="portlet light ">

			<div class="portlet-body">

				<div class="row custom-toolbar">
					<div class="col-md-4">

						<button type="button" class="btn  green" id="btn-view-add" authority="${pageContext.request.contextPath}/taskCmcc/insertTaskCmcc.do">创建</button>



					</div>

					<div class="col-md-8">

						<form action="#" id="fm-search">

							<div style="width:40px;float:left;line-height:30px"> 时间 </div>
							<div class="input-group date form_datetime" style="width:25%;float:left;border: 1px solid #efefef;">
								<input type="text" size="16" name="dateStart" readonly="" class="form-control">
							</div>

							<span class="input-group-addon" style="width:40px;float:left;">
								<font style="vertical-align: inherit;">
									<font style="vertical-align: inherit;"> 至 </font>
								</font>
							</span>
							<div class="input-group date form_datetime" style="width:25%;float:left;border: 1px solid #efefef;">
								<input type="text" size="16" name="dateEnd" readonly="" class="form-control">

							</div>

							<div class="input-group" style="width:35%;float:right">
								<input type="text" name="taskCmccName" class="form-control" placeholder="请输入名称">
								<span class="input-group-btn">
									<button class="btn  btn-default search_btn" id="btn-search" authority="${pageContext.request.contextPath}/taskCmcc/findTaskCmccList.do">
										搜索
									</button>
								</span>
							</div>
						</form>
					</div>

				</div>

				<table id="grid-table"></table>

				<div class="paginationbar">

					<ul id="grid-pager" class="pagination"></ul>
				</div>
			</div>
		</div>

		<jsp:include page="/dynamic/common/suffix${SESSION_USERPROP_KEY.cfg.portalType}.jsp" />

		<jsp:include page="/dynamic/common/footer.jsp" />
		<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"
		 type="text/javascript"></script>
		<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
		<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
		<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/taskCmcc/config.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/taskCmcc/model.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/taskCmcc/controller.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/taskCmcc/view.js?version=${cfg.version}"></script>
		<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>


		<div class="modal fade" role="dialog" id="modal-preview">
			<div class="modal-dialog" role="document" style="width:90%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" authority="false" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">详细</h4>
					</div>
					<div class="modal-body">
						<div id="msg-content" style="padding:5px;width:100%;height:100px;overflow: scroll;">

						</div>
						<div id="task-content" style="padding:5px;width:100%;height:200px;overflow: scroll;">

						</div>
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
