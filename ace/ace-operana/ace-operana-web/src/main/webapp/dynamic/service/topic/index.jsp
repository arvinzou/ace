<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>议题</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">


</script>
<style type="text/css">
			*{padding:0;margin:0;}
			ul li{list-style:none;}
			.ulinput{margin-left:50px;}
		</style>

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
					
				类别：<input
							class="easyui-combobox" style="width: 200px" name="category"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=90&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

                     名称： <input name="name" type="text"
                                        							style="width: 200px;" />
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/topic/findTopicList.do">
							 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

						
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						
						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/topic/insertTopic.do">
							 <i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/topic/updateTopic.do">
							 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/topic/deleteTopicByTopicId.do">
							 <i
								class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
						</button>
						
					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>
		
		
	</div>
	<div id="dialog-message" class="hide">


	</div>


    <div id="dialog-message-view" class="hide">
		<h5 class="header-title">指标</h5>
		<table id="detail"
			   class="table table-striped table-bordered table-hover">
			<thead>
			<tr>
				<th class="center" style="width:80px;">序号</th>
				<th>指标名称</th>
				<th>说明</th>
			</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
    </div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
			src="${portalPath}/content/common/js/xcheck/XCheck.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/topic/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script
			src="${portalPath}/content/common/js/easyui-draggable.js?version=${cfg.version}"></script>

	<script
			src= "${portalPath}/content/common/js/dataTable/jquery.dataTables.min.js?version=${cfg.version}"></script>
	<script
			src= "${portalPath}/content/common/js/dataTable/dataTables.bootstrap.min.js?version=${cfg.version}"></script>

	<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
	parent.autoWidth();
}
</script>


</body>
</html>