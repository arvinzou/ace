<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>pressPayment</title>
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
						会员名称： <input name="name" type="text"
							style="width: 150px; height: 25px" /> <input type="hidden"
							name="status" value="1"> 入会时间: <input
							class="easyui-datebox" name="startDate"
							style="width: 120px; height: 25px; line-height: 25px;"> 至
						<input class="easyui-datebox" name="endsDate"
							style="width: 120px; height: 25px; line-height: 25px;">

						到期时间: <input class="easyui-datebox" name="outDate"
							style="width: 120px; height: 25px; line-height: 25px;">

						<button class="btn btn-info" id="btn-search" authority="false">
							查询 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>

					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar"></div>
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
							<input style="width: 300px" readOnly="true" name="departmentName">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">信息标题</div>
						<div class="profile-info-value">
							<input style="width: 300px" name="name">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">催缴项目</div>
						<div class="profile-info-value">
							<input style="width: 300px" name="chargingItemId">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">会员级别</div>
						<div class="profile-info-value">
							<input style="width: 300px" name="memberLevelId">
						</div>
					</div>

					<div class="profile-info-row">
						<div class="profile-info-name">截止日期</div>
						<div class="profile-info-value">
							<input class="easyui-datebox" name="endDate"
								data-options="required:true"
								style="width: 300px; height: 25px; line-height: 25px;">
						</div>
					</div>

					<div class="profile-info-row">
						<div class="profile-info-name">缴费金额</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 300px"
								name="payNum`" data-options="required:true,validType:'integer'">
						</div>

					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">信息备注</div>
						<div class="profile-info-value">
							<input class="easyui-validatebox textbox" style="width: 300px"
								name="remark" data-options="required:false,validType:'integer'">
						</div>

					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">催缴时间</div>
						<div class="profile-info-value">
							<input class="easyui-datebox" id="tt" name="createDate"
								data-options="required:true" value="${now}"
								style="width: 300px; height: 25px; line-height: 25px;">
						</div>
					</div>
					<div class="profile-info-row">
						<div class="profile-info-name">催缴人员</div>
						<div class="profile-info-value">
							<input class="easyui-textbox" name="createUserName"
								readOnly="true" data-options="required:true"
								value="${SESSION_USERPROP_KEY.nickName}"
								style="width: 300px; height: 25px; line-height: 25px;">
						</div>
					</div>
				</div>

			
		</form>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/pressPayment/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/pressPayment/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/pressPayment/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/pressPayment/view.js?version=${cfg.version}"></script>
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