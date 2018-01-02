<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberInfo</title>
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
						会员名称： <input name="name" type="text" style="width: 150px; height: 25px" />
						<input name="createUserId" type="hidden" value="2" />
						状态：<input class="easyui-combobox" style="width: 120px" 
								name="status"
								data-options="
				                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=96&selected=false',
				                    method:'get',
				                    valueField:'code',
				                    textField:'name',
				                    editable:false,
				                    panelHeight:'auto'">
							入会时间:
                        <input class="easyui-datebox" name="startDate" style="width:120px;">
							至
                        <input class="easyui-datebox" name="endsDate" style="width:120px;">

						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/memberInfo/findMemberInfoList.do">
							<i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/memberInfo/updateMemberBaseByPrimaryKey.do">
							<i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-update"
							authority="${pageContext.request.contextPath}/memberInfo/updateState.do">
							<i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
	
	<div id="dialog-message" class="hide">
		<form id="fm-dialog" onsubmit="return beforeSubmit()">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">会员名称</div>
					<div class="profile-info-value">
						<input type="hidden" id="edit-id" name="id" />
						<input style="width: 200px;height:25px;line-height:25px;" readOnly="true" 
							id="edit-departmentName" name="departmentName">
					</div>
					<div class="profile-info-name">入会时间</div>
					<div class="profile-info-value">
						<input class="easyui-datebox" style="width: 200px;height:25px;line-height:25px;" 
							name="joinDate" id="edit-joinDate">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">会员证号</div>
					<div class="profile-info-value">
						<input name="memberNo" class="easyui-validate textbox"
							data-options="required:true" id="edit-memberNo"
							style="width: 200px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">会员等级</div>
					<div class="profile-info-value">
						<input style="width:200px;height:25px;line-height:25px;" id="edit-memberLevel"
							name="memberLevel"  >
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">会员到期时间</div>
					<div class="profile-info-value">
						<input class="easyui-datebox" id="edit-endDate" name="endDate"
							data-options="required:true,panelWidth:200"
							style="width: 200px; height: 25px; line-height: 25px;">
					</div>
				</div>
			</div>
		</form>
	</div>
	
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/memberInfo/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberInfo/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberInfo/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberInfo/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function() {
			autoSize();
		}
		parent.onresize = function() {
			autoSize();
		}
		function autoSize() {
			$(cfg.grid_selector).jqGrid('setGridWidth',
					$(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight',
					window.innerHeight - layoutTopHeight-10);
			parent.autoWidth();
		}
	</script>
</body>
</html>