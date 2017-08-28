<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>config</title>
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
					收费项目： <input id="index_chargingItemId" name="chargingItemId" class="easyui-combotree"
							data-options="url:'${pageContext.request.contextPath}/chargingItem/getChargingItemTreeList.do?flag=0',method:'get',animate: true,lines:false"
							style='width: 130px; line-height: 25px; height: 25px;'> <%-- <a
							href="javascript:clearCombotree('#index_chargingItemId')">清除</a> --%> 
                   	<%--  会员级别：<input class="easyui-combobox" style="width: 120px" name="memberLevelId"
							data-options="
			                    url:'${pageContext.request.contextPath}/memberLevel/selectListByDeptId.do?selected=false',
			                    method:'get',
			                    valueField:'code',
			                    textField:'name',
			                    editable:false,
			                    panelHeight:'200'">
					缴费类型：<input class="easyui-combobox" style="width: 120px" name="payType"
							data-options="
			                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=69&selected=false',
			                    method:'get',
			                    valueField:'code',
			                    textField:'name',
			                    editable:false,
			                    panelHeight:'200'"> --%>
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/payCfg/findPayCfgList.do">
							 <i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button> 
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/payCfg/insertPayCfg.do">
							 <i
								class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/payCfg/updatePayCfg.do">
							 <i
								class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/payCfg/deletePayCfgByPayCfgId.do">
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
	<div id="tt">
		<a href="#" class="icon-add" onclick="javascript:alert('add')"></a>
		<a href="#" class="icon-edit" onclick="javascript:alert('edit')"></a>
	</div>
	
	<div id="dialog-update-message" class="hide">
		<form id="updatePayCfg" onsubmit="return beforeSubmit()">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row" id="update_ChargingItem">
					<div class="profile-info-name">收费项目</div>
					<div class="profile-info-value">
						<input type="hidden" id="update_id" name="id">
						<input style="width: 200px;height:25px;" id="update_chargingItemId" name="chargingItemId">
					</div>
				</div>
				<div class="profile-info-row" id="update_ChargingItem">
					<div class="profile-info-name">收费金额</div>
					<div class="profile-info-value">
						<input class="easyui-numberbox" style="width: 200px;height:25px;" id="update_payNum" name="payNum" data-options="precision:2,required:true" />
					</div>
				</div>
			</div>
		</form>
	</div>
	
	
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/payCfg/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/payCfg/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/payCfg/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/payCfg/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	<script type="text/javascript">
		window.onresize = function () {
			console.log('autoWidthJqgrid');
			$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
			$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight-layoutTopHeight);
			parent.autoWidth();
		}
	</script>
</body>
</html>