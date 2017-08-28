<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>hisSelfPayment</title>
</head>
<jsp:include page="../../common/common.jsp" />
<script type="text/javascript">
	
</script>
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
						年度：<input class="easyui-combobox" style="width: 120px" name="year"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=year&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    editable:false,
                    panelHeight:'auto'">
						收费项目： <input id="cc2" name="chargingItemId"
							class="easyui-combotree"
							data-options="url:'${pageContext.request.contextPath}/chargingItem/getChargingItemTreeList.do?flag=0',method:'get',animate: true,
                lines:false,"
							style='width: 130px; line-height: 25px; height: 25px;'><%-- <a
							href="javascript:clearCombotree('#cc2')">清除</a>  --%>  
						审核状态：<input class="easyui-combobox" style="width: 120px"
							name="status"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=67&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    editable:false,
                    panelHeight:'auto'">


					
						缴费时间：<input class="easyui-datebox" name="startDate"
							style="width: 120px; height: 25px; line-height: 25px;"> 至
						<input class="easyui-datebox" name="endsDate"
							style="width: 120px; height: 25px; line-height: 25px;">

						<button class="btn btn-info" id="btn-search" authority="false">
							查询 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">

						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/memberPayInfo/deleteMemberPayInfoByMemberPayInfoId.do">
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


	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/hisSelfPayment/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/hisSelfPayment/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/hisSelfPayment/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/hisSelfPayment/view.js?version=${cfg.version}"></script>

	<script
		src="${pageContext.request.contextPath}/content/service/memberCenter/controller-deptInfo.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			autoSize();
		}
		parent.onresize = function() {
			autoSize();
		}
		function autoSize() {
			parent.autoWidth();
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight);

		}
	</script>
	<style>
.formFiled {
	padding: 2px;
}
</style>
</body>
</html>