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
	var deptId= '${SESSION_USERPROP_KEY.corpId}';
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
						会员名称： <input name="name" type="text"
							style="width: 150px; height: 25px" /> 审核状态：<input
							class="easyui-combobox" style="width: 100px" name="status"
							data-options="
                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=67&selected=false',
                    method:'get',
                    valueField:'code',
                    textField:'name',
                    panelHeight:'auto'">

						入会时间: <input class="easyui-datebox" name="startDate"
							style="width: 100px; height: 25px; line-height: 25px;"> 至
						<input class="easyui-datebox" name="endsDate"
							style="width: 1å00px; height: 25px; line-height: 25px;">

						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/memberInfo/findMemberInfoList.do">
							<i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-audit"
							authority="${pageContext.request.contextPath}/memberInfo/updateAudit.do">
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
		<jsp:include page="../include/deptInfo.jsp" />
		<div>
			<label> <input name="auditStatus" type="radio" class="ace"
				checked="checked" value="1" /> <span class="lbl"> 通过</span>
			</label> <label> <input name="auditStatus" type="radio" class="ace"
				value="2" /> <span class="lbl"> 驳回</span>
			</label>
		</div>
		<div>
			<label for="memberNo">会员证号</label>

			<input class="easyui-textbox" name="memberNo" style="width: 300px; height: 25px; line-height: 25px;"
				maxlength="50"></input>

		</div>
		<div>
			<label for="memberNo">入会时间</label>

			<input class="easyui-datebox" name="joinDate" style="width: 300px; height: 25px; line-height: 25px;"></input>

		</div>
		<div>
			<label for="auditRemark">审核备注</label>

			<textarea class="form-control limited" name="auditRemark"
				maxlength="50"></textarea>

		</div>
		<div class="formFiled">
			<label for="payDate">审核时间</label> <input class="easyui-datebox"
				id="tt" name="payDate" readOnly="true" data-options="required:true"
				value="${now}"
				style="width: 300px; height: 25px; line-height: 25px;">
		</div>
		<div class="formFiled">
			<label for="auditUserName">审核人员</label> <input class="easyui-textbox"
				name="auditUserName" readOnly="true" data-options="required:true"
				value="${SESSION_USERPROP_KEY.nickName}"
				style="width: 300px; height: 25px; line-height: 25px;">
		</div>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/memberAudit/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberAudit/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberAudit/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberCenter/controller-deptInfo.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberAudit/view.js?version=${cfg.version}"></script>
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