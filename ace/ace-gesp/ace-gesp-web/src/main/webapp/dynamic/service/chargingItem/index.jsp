<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<title>收费项目</title>
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
						<input type="hidden" name="pid"/>
						名称： <input name="name" type="text" style="width: 150px; height: 25px" />
						<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/chargingItem/findChargingItemList.do">
							<i class="ace-icon fa fa-search  align-middle bigger-125 icon-on-right"></i>
						</button>
					</form>
					<div class="space10"></div>
					<div id="toolbar" class="toolbar">
						<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/chargingItem/insertChargingItem.do">
							<i class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/chargingItem/updateChargingItem.do">
							<i class="ace-icon fa fa-edit  align-middle bigger-125 icon-on-right"></i>
						</button>
						<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/chargingItem/deleteChargingItemByChargingItemId.do">
							<i class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>
						</button>
					</div>
				</div>
			</div>
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
		
		<%-- <div class="easyui-layout" id="cc" style="width:100%;height:300px;">
			<div data-options="region:'center',border:false,fit:true" id="easyui-center">
				<table id="grid-table"></table>
				<div id="grid-pager"></div>
			</div>
			<div id="cc-west" class="easyui-west" data-options="region:'west',split:true" title="我的树" style="width:200px;">
				 <ul id="tt" class="easyui-tree" data-options="
		               url: '${pageContext.request.contextPath}/chargingItem/getChargingItemTreeList.do',
		                method: 'get',
		                animate: true,
		                lines:true,
		                onContextMenu: function(e,node){
		                    e.preventDefault();
		                    $(this).tree('select',node.target);
		                    autotreeq(node);
		                    $('#mm').menu('show',{
		                        left: e.pageX,
		                        top: e.pageY
		                    });
		                }
		            "></ul>
			</div>
			<!--  
			<div data-options="region:'south',split:true" style="height:50px;"></div>
	        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
	        -->
		</div> --%>
	</div>

	<!-- <div id="mm" class="easyui-menu" style="width:120px;">
        <div onclick="treeappend()" data-options="iconCls:'icon-add'">添加</div>
        <div onclick="treeedit()" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="treeremove()" data-options="iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div onclick="treereload()" data-options="iconCls:'icon-refresh'">刷新</div>
        <div onclick="expand()">展开</div>
        <div onclick="collapse()">收回</div>
    </div> -->
	
	<div id="dialog-add-message" class="hide">
		<form id="updateChargItem" onsubmit="return beforeSubmit()">
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name">项目名称</div>
					<div class="profile-info-value">
						<input type="hidden" id="update_id" name="id">
						<input class="easyui-validatebox textbox" style="width: 200px;height:25px;" id="update_name" name="name">
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">项目分类</div>
					<div class="profile-info-value">
						<input style="width: 200px;height:25px;" id="update_itemType" name="itemType" />
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">是否按会员等级收费</div>
					<div class="profile-info-value">
						<input style="width: 200px;height:25px;" id="update_whetherMemberlevel" name="whetherMemberlevel" />
					</div>
				</div>
				<div id="html_whetherPeriod" class="profile-info-row">
					<div class="profile-info-name">是否按周期收费</div>
					<div class="profile-info-value">
						<input style="width: 200px;height:25px;" id="update_whetherPeriod" name="whetherPeriod" />
					</div>
				</div>
				<%-- <div class="profile-info-row">
					<div class="profile-info-name">是否按自定义类型收费</div>
					<div class="profile-info-value">
						<input style="width: 200px;height:25px;" id="update_whetherCustom" name="whetherCustom" />
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">自定义类型</div>
					<div class="profile-info-value">
						<input style="width: 200px;height:25px;" id="update_custom" name="custom" />
						<input type="" value="添加自定义类型" />
					</div>
				</div>--%>
				<div class="profile-info-row">
					<div class="profile-info-name">状态</div>
					<div class="profile-info-value">
						<input type="checkbox" checked="true" value="1" offval="0" id="update_status" name="status" role="checkbox" class="FormElement ace ace-switch ace-switch-5">
						<span class="lbl"></span>
					</div>
				</div>
			</div>
		</form>
	</div>
	
		
	<jsp:include page="../../common/footer-1.jsp" />
	<script
		src="${pageContext.request.contextPath}/content/service/chargingItem/config.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/chargingItem/model.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/chargingItem/controller.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/service/chargingItem/view.js?version=${cfg.version}"></script>
	<script
		src="${pageContext.request.contextPath}/content/common/js/jquery.serializeObject.min.js?version=${cfg.version}"></script>
	<jsp:include page="../../common/footer-2.jsp" />
	
	<script type="text/javascript">
		window.onresize = function () {
			//autoResize();
			setTimeout("autoResize()",100);
			setTimeout("autoResize()",1000);
		}
		function autoResize(){
			jQuery('.panel-tool').find('a').on( 'click', function(e) {
				setTimeout("autoResize()",1000);
			});
			var h=window.innerHeight-130;
			/* $('#cc').layout('resize', {
				width:$(".page-content").width(),
				height:h
			});
			$('#cc').css("height",h); */
			$(cfg.grid_selector).jqGrid('setGridHeight', h-65);
			/* var display=$('#cc-west').css('display');
			console.log(display) */
			$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
			/* if(display=='none'){
				$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width()-26);
			}else{
				$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width()-200);
			}
			console.log('autoResize:'+h); */
			parent.autoWidth();
		}
		jQuery(function($) {
			jQuery('.layout-button-left').on( 'click', function(e) {
				setTimeout("autoResize()",1000);
			});
		});
	</script>
</body>
</html>