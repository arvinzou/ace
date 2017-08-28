<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>user</title>
</head>
<script type="text/javascript">
	var urlid = '${param.id}';
	var edit = false;
</script>
<jsp:include page="../../common/common.jsp" />

<body>
	<div class="page-content">
		<div class="widget-box" id="widget-box">
			<div class="widget-header">
				<h5 class="widget-title smaller">设置查询条件</h5>

				<div class="widget-toolbar"></div>
			</div>
			<div class="widget-body">
				<div class="widget-main padding-6">
					<form action="#" id="fm-search">
						<input type="hidden" id="corpId" value="${SESSION_USERPROP_KEY.corpId}" />
						标题： <input name="title" type="text" style="width: 150px;height:25px" /> 类别：<input
							class="easyui-combobox"
							style="width: 150px; height: 25px; line-height: 25px;"
							name="category"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=21&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',editable:false">
						<button class="btn btn-info" id="btn-search"
							authority="${portalPath}/notice/findNoticeList.do">
							<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-purple" id="btn-view-review" authority="false">
							公告详情
							<i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
	
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/companyNotice/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyNotice/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyNotice/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyNotice/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />

	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight-layoutTopHeight);
			parent.autoWidth();
		}
	</script>
</body>
</html>