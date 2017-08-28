<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>memberPayInfo</title>
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
						会员名称： <input name="name" type="text" style="width: 140px; height: 25px" /> 
						收费项目： <input id="index_chargingItemId" name="chargingItemId" class="easyui-combotree"
							data-options="url:'${pageContext.request.contextPath}/chargingItem/getChargingItemTreeList.do?flag=0',method:'get',animate: true,lines:false,"
							style='width: 130px; line-height: 25px; height: 25px;'>
						<%-- <a href="javascript:clearCombotree('#index_chargingItemId')">清除</a>   --%>
						状态：<input class="easyui-combobox" style="width: 120px" name="payStatus"
									data-options="
					                    url:'${portalPath}/dict/findListByCategoryId.do?categoryId=92&selected=false',
					                    method:'get',
					                    valueField:'code',
					                    textField:'name',
					                    editable:false,
					                    panelHeight:'150'">
						会费到期时间: <input class="easyui-datebox" name="outDate" style="width: 120px; height: 25px; line-height: 25px;">
						<button class="btn btn-info" id="btn-search" authority="${pageContext.request.contextPath}/memberInfo/findMemberInfoList.do">
							<i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
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
						<input style="width: 300px" readOnly="true" name="departmentName">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">收费项目</div>
					<div class="profile-info-value">
						<input  name="chargingItemId" id="cc"
							class="easyui-combotree"
							data-options="url:'${pageContext.request.contextPath}/chargingItem/getChargingItemTreeList.do',method:'get',animate: true,
                lines:false,"
							style='width: 300px; line-height: 25px; height: 25px;'> 
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">会员级别</div>
					<div class="profile-info-value">
						<input style="width: 300px" name="memberLevelId" id="memberLevelId">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">截止日期</div>
					<div class="profile-info-value">
						<input class="easyui-datebox" name="endDate"
							data-options="required:true" id="endDate"
							style="width: 300px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">缴费金额</div>
					<div class="profile-info-value">
						<input class="easyui-validatebox textbox" style="width: 300px" id="payAmount"
							name="payAmount" data-options="required:true,validType:'integer'">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">收费时间</div>
					<div class="profile-info-value">
						<input class="easyui-datetimebox" id="tt" name="payDate"
							data-options="required:true,panelWidth:300" value="${nowTime}"
							style="width: 300px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">收费人员</div>
					<div class="profile-info-value">
						<input class="easyui-textbox" name="createUserName"
							readOnly="true" data-options="required:true"
							value="${SESSION_USERPROP_KEY.nickName}"
							style="width: 300px; height: 25px; line-height: 25px;">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">备注</div>
					<div class="profile-info-value">
						<input class="easyui-textbox" name="remark"
							style="width: 300px; height: 25px; line-height: 25px;">
					</div>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/memberPayInfo/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberPayInfo/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberPayInfo/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/memberPayInfo/view.js?version=${cfg.version}"></script>
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
					window.innerHeight - layoutTopHeight+25);
		}
	</script>

</body>
</html>