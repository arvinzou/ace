<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberAudit</title>
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
						
						地区： <input id="cc2" name="areaCode"
							class="easyui-combotree"
							data-options="url:'${pageContext.request.contextPath}/system/selectProvinceTreeList.do',method:'get',animate: true,
                lines:false,"
							style='width: 200px; line-height: 25px; height: 25px;'> <a
							href="javascript:clearAreaCode()">清除</a> 
							
						上级机构： <input id="cc1" name="parentDepartmentId"
							class="easyui-combotree"
							data-options="url:'${pageContext.request.contextPath}/system/selectDepartmentTreeList.do',method:'get',animate: true,
                lines:false,"
							style='width: 200px; line-height: 25px; height: 25px;'> <a
							href="javascript:clearQparams()">清除</a> 机构名称： <input
							name="departmentName" type="text" maxlength="20"
							style="width: 200px; height: 25px;" />
						 审核状态：<input
							class="easyui-combobox" style="width: 100px" name="status"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=67&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

						

						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/department/findDepartmentList.do">
							<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">


						<button class="btn btn-info" id="btn-view-audit"
							authority="${pageContext.request.contextPath}/department/updateRegAudit.do">
							<i
								class="ace-icon fa fa-legal  align-middle bigger-125 icon-on-right"></i>
						</button>

					</div>
				</div>
			</div>
		</div>

		<table id="grid-table"></table>

		<div id="grid-pager"></div>


	</div>

	<div id="dialog-message" class="hide">
		<div>
			<label> <input name="auditStatus" type="radio" class="ace"
				checked="checked" value="1" /> <span class="lbl"> 通过</span>
			</label> <label> <input name="auditStatus" type="radio" class="ace"
				value="2" /> <span class="lbl"> 驳回</span>
			</label>
		</div>
		<div>
			<label for="auditRemark">审核备注</label>

			<textarea class="form-control limited" name="auditRemark"
				maxlength="50"></textarea>
		</div>

	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/portal/js/regAudit/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/regAudit/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/regAudit/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/portal/js/regAudit/view.js?version=${cfg.version}"></script>
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
</body>
</html>