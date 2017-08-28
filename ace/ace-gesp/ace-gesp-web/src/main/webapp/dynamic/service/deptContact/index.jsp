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
						<button class="btn btn-info" id="btn-view-del" 
							authority="${portalPath}/users/deleteUsers.do">
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
			<jsp:include page="../deptContact/addContact.jsp"></jsp:include>
		</form>
	</div>
	
	<div id="dialog-messeditCon" class="hide">
		<form id="updateContactInfo" method="post" novalidate>
		 	<jsp:include page="../deptContact/contact.jsp" />
		</form>
	</div>
		
	<jsp:include page="../../common/footer-1.jsp" />
	<jsp:include page="../../common/footer-2.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/deptContact/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/deptContact/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/deptContact/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/deptContact/view.js?version=${cfg.version}"></script>
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