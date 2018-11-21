<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
		<title>发送队列</title>
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

						



					</div>

					<div class="col-md-8">

						<form action="#" id="fm-search">

							<div style="width:40px;float:left;line-height:30px"> 时间 </div>
							<div class="input-group date form_datetime" style="width:25%;float:left;border: 1px solid #efefef;">
								<input type="text" size="16" name="startDate" readonly="" class="form-control">
							</div>

							<span class="input-group-addon" style="width:40px;float:left;">
								<font style="vertical-align: inherit;">
									<font style="vertical-align: inherit;"> 至 </font>
								</font>
							</span>
							<div class="input-group date form_datetime" style="width:25%;float:left;border: 1px solid #efefef;">
								<input type="text" size="16" name="endDate" readonly="" class="form-control">

							</div>

							<div class="input-group" style="width:35%;float:right">
								<input type="text" name="tel" class="form-control" placeholder="请输入手机号">
								<span class="input-group-btn">
									<button class="btn  btn-default search_btn" id="btn-search" authority="${pageContext.request.contextPath}/queueCmccWait/findQueueCmccWaitList.do">
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
		<script src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${portalPath}/content/common/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js?v=${cfg.version}"></script>
		<script src="${portalPath}/content/common/jqGrid/jquery.jqGrid.new.js?version=${cfg.version}"></script>
		<script src="${portalPath}/content/common/assets/js/jqGrid/i18n/grid.locale-cn.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/queueCmccWait/config.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/queueCmccWait/model.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/queueCmccWait/controller.js?version=${cfg.version}"></script>
		<script src="${pageContext.request.contextPath}/content/service/queueCmccWait/view.js?version=${cfg.version}"></script>
		<script src="${portalPath}/content/common/js/authority.js?version=${cfg.version}"></script>
	</body>
</html>
