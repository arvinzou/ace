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
						标题： <input name="name" type="text" style="width: 150px;height:25px" /> 紧急程度：<input
							class="easyui-combobox"
							style="width: 150px; height: 25px; line-height: 25px;"
							name="statusType"
							data-options="
                    url:'${pageContext.request.contextPath}/dict/findListByCategoryId.do?categoryId=97&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',editable:false
            ">
            
            消息类型： <input
							class="easyui-combobox"
							style="width: 150px; height: 25px; line-height: 25px;"
							name="messageType"
							data-options="
                    url:'${pageContext.request.contextPath}/dict/findListByCategoryId.do?categoryId=99&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',editable:false
            ">
            消息分类：<input
							class="easyui-combobox"
							style="width: 150px; height: 25px; line-height: 25px;"
							name="column1"
							data-options="
                    url:'${pageContext.request.contextPath}/dict/findListByCategoryId.do?categoryId=10&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',editable:false
            ">
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/message/findList.do">
							<i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						<%-- <button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/message/insert.do">
							<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/message/update.do">
							<i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button> --%>
						<button class="btn btn-purple" id="btn-view-details"
							authority="${pageContext.request.contextPath}/message/selectByPrimaryKey.do">
							<i class="ace-icon glyphicon  glyphicon-cog  align-middle bigger-125 icon-on-right"></i>
						</button>
						 
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/message/delete.do">
							<i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
						</button>

					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>


	</div>
	<div id="dialog-message" class="hide">
		确定发布吗？
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/portal/js/message/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/message/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/message/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/message/view.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />

	<script type="text/javascript">
		window.onresize = function() {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight-layoutTopHeight);
			//parent.autoWidth();
		}
	</script>
</body>
</html>