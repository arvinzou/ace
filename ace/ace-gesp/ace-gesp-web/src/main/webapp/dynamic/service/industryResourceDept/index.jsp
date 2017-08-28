<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>industryResourceDept</title>
</head>
<jsp:include page="../../common/common.jsp" />
<style>
	.update-button{
		display: none;
	}
</style>
<link rel="stylesheet"
	href="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/css/jquery.plupload.queue.css"
	type="text/css" media="screen" />
<script type="text/javascript">
var memberCode = '${SESSION_USERPROP_KEY.corpId}';
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
						名称： <input name="name" type="text"
							style="width:150px; height: 25px" /> <input type="hidden"
							name="status" value="1"> 
							
						是否会员：<input class="easyui-combobox" style="width: 120px" name="status"
									data-options="
					                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=91&selected=false',
					                    method:'get',
					                    valueField:'code',
					                    textField:'name',
					                    editable:false,
					                    panelHeight:'auto'">
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/industryResourceDept/findDeptList.do">
							查询 <i
								class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/memberInfo/insertMemberInfoByAdmin.do">
							<i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit" 
							authority="${pageContext.request.contextPath}/memberInfo/updateMemberInfo.do">
							<i 
 								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-add-depart" 
							authority="${pageContext.request.contextPath}/www/reg/insert.do">
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
		<jsp:include page="../include/deptInfo.jsp" />
		<form id="fm1" onsubmit="return beforeSubmit()">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">会员证号</div>
					<div class="profile-info-value">
						<input type="hidden" id="edit_status" name="status"/>
						<input type="hidden" id="edit_id" name="id"/>
						<input style="width: 300px" id="edit_memberNos" name="memberNo">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">会员级别</div>
					<div class="profile-info-value">
						<input
							class="easyui-combobox" style="width: 300px" id="edit_meberLevels" name="memberLevel" 
							data-options="
			                    url:'${pageContext.request.contextPath}/memberLevel/selectListByDeptId.do',
			                    method:'get',
			                    valueField:'code',
			                    textField:'name',
			                    panelHeight:'auto'">
					</div>
				</div>
			</div>
		</form>
	</div>
	<!-- 修改 -->
	<div id="dialog-edit" class="hide">
		<form id="fm2" onsubmit="return beforeSubmit()">
			<jsp:include page="../memberBaseInfo/index.jsp" />
		</form>
	</div>
	
	
	<!-- 添加 -->
	<div id="dialog-add" class="hide">
		<form id="fm3" onsubmit="return beforeSubmit()">
			<jsp:include page="../memberBaseInfo/add.jsp" />
		</form>
	</div>
	
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDept/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDept/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDept/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/industryResourceDept/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberCenter/controller-deptInfo.js?version=${cfg.version}"></script>
		
	<script
		src="${pageContext.request.contextPath}/content/service/memberBaseInfo/addController.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberBaseInfo/controller.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/plupload.full.min.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/i18n/zh_CN.js?version=${cfg.version}"></script>
	<script type="text/javascript"
		src="${portalPath}/content/common/js/plupload-2.1.2/js/jquery.plupload.queue/jquery.plupload.queue.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberBaseInfo/upload.js?version=${cfg.version}"></script>
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

</body>
</html>