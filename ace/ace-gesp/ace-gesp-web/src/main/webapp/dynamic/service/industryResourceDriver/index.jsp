<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>industryResourceDriver</title>
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
						姓名：<input name="name" type="text"
							style="width: 150px; height: 25px" /> 身份证号：<input name="idCard"
							type="text" style="width: 150px; height: 25px" /> <input
							type="hidden" name="status" value="1">
						<button class="btn btn-info" id="btn-search" authority="false">
							查询 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/industryResource/updateDriverInfoById.do">
							<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
	
	<div id="dialog-message" class="hide">
		<form id="updateDriverInfo" method="post" novalidate>
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">姓名</div>
					<div class="profile-info-value">
						<input type="hidden" name="id" id="id" />
						<input style="width: 260px;line-height: 25px;height: 25px;" readOnly="true" name="name" id="name" class="easyui-textbox"/>
					</div>
					<div class="profile-info-name">性别</div>
					<div class="profile-info-value">
						<input name="sex" id="sex" style='width: 260px; line-height: 25px; height: 25px;'> 
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">身份证号码</div>
					<div class="profile-info-value">
						<input style="width: 260px" name="idCard" id="idCard" class="easyui-textbox"/>
					</div>
					<div class="profile-info-name">出生日期</div>
					<div class="profile-info-value">
						<input class="easyui-datebox" name="birthdate" id="birthdate" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">初领驾驶证日期</div>
					<div class="profile-info-value">
						<input style="width: 260px" class="easyui-datebox" id="initDrivingLicDate" name="initDrivingLicDate">
					</div>
					<div class="profile-info-name">从业资格证号码</div>
					<div class="profile-info-value">
						<input id="certNumber" name="certNumber" class="easyui-textbox" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">从业人员类别</div>
					<div class="profile-info-value">
						<input name="certPersonType" id="certPersonType" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">从业资格类别</div>
					<div class="profile-info-value">
						<input name="certType" id="certType" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">备案资格类别</div>
					<div class="profile-info-value">
						<input name="filingCertType" id="filingCertType" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">驾驶证号</div>
					<div class="profile-info-value">
						<input name="driverLicNo" id="driverLicNo" class="easyui-textbox" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">驾驶证准驾车型</div>
					<div class="profile-info-value">
						<input name="driverLicCarType" id="driverLicCarType" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">入职时间</div>
					<div class="profile-info-value">
						<input name="entryTime" id="entryTime" class="easyui-datebox" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">从业发证机构</div>
					<div class="profile-info-value">
						<input name="certCompanyName" id="certCompanyName"
							style="width: 260px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">合作方式</div>
					<div class="profile-info-value">
						<input name="cooperationMode" id="cooperationMode" 
							style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">联系电话</div>
					<div class="profile-info-value">
						<input name="tel" id="tel" class="easyui-numberbox" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
					<div class="profile-info-name">备案状态</div>
					<div class="profile-info-value">
						<input name="recordTime" id="recordTime" style="width: 260px; height: 25px; line-height: 25px;">
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDriver/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDriver/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDriver/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDriver/view.js?version=${cfg.version}"></script>
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