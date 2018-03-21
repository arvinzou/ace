<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>evTask</title>
</head>
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
						名称： <input name="evTaskName" type="text" style="width: 200px;" />
										状态：		<input class="easyui-combobox"
										style="width: 200px; height: 20px; line-height: 20px;"
										name="status"
										data-options="
                    url:'${sessionScope.portalContextPath}/dict/findListByCategoryId.do?categoryId=54&selected=false',
                    method:'get',
                   
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'
            ">
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/evTask/findEvTaskList.do">
							<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/evTask/insertEvTask.do">
							<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/evTask/updateEvTask.do">
							<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
						</button>

						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/evTask/deleteEvTaskByEvTaskId.do">
							<i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
						</button>
						
						<button class="btn btn-purple" id="btn-view-cfg"
							authority="${pageContext.request.contextPath}/evTask/updateEvTask.do">
							<i
								class="ace-icon fa fa-cog  align-top bigger-125 icon-on-right"></i>
								评测任务配置
						</button>
						
					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>


	</div>
	<jsp:include page="../../common/footer-1.jsp" />

	<link rel="stylesheet" type="text/css" href="${portalContextPath}/content/simditor/styles/simditor.css"/>

	<script type="text/javascript" src="${portalContextPath}/content/simditor/scripts/module.js"></script>
	<script type="text/javascript" src="${portalContextPath}/content/simditor/scripts/hotkeys.js"></script>
	<script type="text/javascript" src="${portalContextPath}/content/simditor/scripts/uploader.js"></script>
	<script type="text/javascript" src="${portalContextPath}/content/simditor/scripts/simditor.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/config.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/model.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/controller.js"></script>
	<script
		src="${pageContext.request.contextPath}/content/js/service/evTask/view.js"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight-layoutTopHeight);
		}
	</script>	
</body>
</html>