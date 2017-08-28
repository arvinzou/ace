<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>deptContact</title>
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
						企业名称： <input name="corpName" type="text" style="width:150px; height: 25px" />
						姓名： <input name="name" type="text" style="width:150px; height: 25px" />
						<button class="btn btn-info" id="btn-search" authority="false">
							查询 <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-add"
							authority="${portalPath}/users/insertUsers.do">
							<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit" 
							authority="${portalPath}/users/updateUsers.do">
							<i  class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-init" 
							authority="${portalPath}/users/updateUsersForInitPassword.do">
							<i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
	
	
	<div id="dialog-messadd" class="hide">
		<form id="addContactInfo" method="post" novalidate>
			<jsp:include page="../companyUsers/addContact.jsp"></jsp:include>
		</form>
	</div>
	
	<div id="dialog-messeditCon" class="hide">
		<form id="updateContactInfo" method="post" novalidate>
		 	<jsp:include page="../companyUsers/contact.jsp" />
		</form>
	</div>
	
	<div id="dialog-messInit" class="hide">
		<form id="initPassword" method="post" novalidate>
		 	<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">用 户 名</div>
					<div class="profile-info-value">
						<span id="init_name"></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">初始密码</div>
					<div class="profile-info-value">
						<input type="hidden" name="userId" id="init_userId" />
						<input class="easyui-passwordbox" type="password" id="init_password" 
							style="width: 260px; height: 25px; line-height: 25px;"
							data-options="required:true">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">确认密码</div>
					<div class="profile-info-value">
						<input class="easyui-passwordbox" type="password" id="init_repassword" 
							data-options="required:true" style="width: 260px;line-height: 25px;height: 25px;"/>
					</div>
				</div>
			</div>
		</form>
	</div>
		
	<jsp:include page="../../common/footer-1.jsp" />
	<jsp:include page="../../common/footer-2.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/companyUsers/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyUsers/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyUsers/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/companyUsers/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
		
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