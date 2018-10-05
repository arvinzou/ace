<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
	<meta name="viewport"
		  content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<title>group</title>
</head>
<jsp:include page="../../common/common.jsp" />
<style>
.layout-user {
	width: 60px;
	height: 20px;
	float: left;
	margin: 1px 1px 1px;
}
</style>
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
					分组名称： <input name="groupName" type="text"
								 style="width: 200px;" />

					<button class="btn btn-info" id="btn-search"
							authority="${pageContext.request.contextPath}/group/findGroupList.do">
						<i
								class="ace-icon fa fa-search  align-top bigger-125 icon-on-right"></i>
					</button>

				</form>
				<div id="toolbar" class="toolbar">

					<button class="btn btn-info" id="btn-view-add"
							authority="${pageContext.request.contextPath}/group/insertGroup.do">
						<i
								class="ace-icon fa fa-plus-square  align-top bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-info" id="btn-view-edit"
							authority="${pageContext.request.contextPath}/group/updateGroup.do">
						<i
								class="ace-icon fa fa-edit  align-top bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-purple" id="btn-view-da"
							authority="${pageContext.request.contextPath}/group/batchSaveGroupUsersByUserIds.do">
						<i
								class="ace-icon glyphicon  glyphicon-cog  align-top bigger-125 icon-on-right"></i>
					</button>
					<button class="btn btn-warning" id="btn-view-del"
							authority="${pageContext.request.contextPath}/group/deleteGroupByGroupId.do">
						<i
								class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
					</button>

				</div>
			</div>
		</div>
	</div>

	<table id="grid-table"></table>

	<div class="paginationbar"><ul id="grid-pager" class="pagination"></ul></div>


</div>
<jsp:include page="../../common/footer-1.jsp" />
<script
		src="${pageContext.request.contextPath}/content/service/group/config.js"></script>
<script
		src="${pageContext.request.contextPath}/content/service/group/model.js"></script>
<script
		src="${pageContext.request.contextPath}/content/service/group/controller.js"></script>
<script
		src="${pageContext.request.contextPath}/content/service/group/view.js"></script>
<jsp:include page="../../common/footer-2.jsp" />
<script type="text/javascript">
window.onresize = function () {
	console.log('autoWidthJqgrid');
	$(cfg.grid_selector).jqGrid('setGridWidth', $(".page-content").width());
	$(cfg.grid_selector).jqGrid('setGridHeight', window.innerHeight - layoutTopHeight);
}
</script>
<div id="dialog-message" class="hide">
	<select class="easyui-combogrid"
			style="width: 560px; height: 25px; line-height: 25px;"
			id="combogrid-tmp"
			data-options="panelWidth: 530,idField: 'id',textField: 'name',url: '/uf/personage/selectPersonage.do',
			mode:'remote',
			fitColumns:true,
			method: 'get',columns: [[
			{field:'name',title:'姓名',width:100},
			{field:'mobile',title:'手机号',width:150,align:'right'},
			{field:'deptName',title:'单位',width:250,align:'right'}
			 ]]"></select>

	<div style="height: 5px"></div>
	<div>
		<button class="btn btn-purple" id="btn-view-select-tmp"
				authority="false">
			添加<i
				class="ace-icon glyphicon  glyphicon-plus  align-top bigger-125 icon-on-right"></i>
		</button>
		<button class="btn btn-purple" id="btn-view-remove-tmp"
				authority="false">
			清除<i
				class="ace-icon glyphicon  glyphicon-remove  align-top bigger-125 icon-on-right"></i>
		</button>
	</div>
	<div style="height: 5px"></div>
	<div id="task-content-tmp" class="easyui-panel"
		 style="padding: 5px; width: 585px; height: 200px"></div>

	<script type="text/javascript">
	jQuery(function($) {
		$( "#btn-view-select-tmp" ).on('click', function(e) {
			e.preventDefault();
			selectMobile();
		});
		$('#combogrid-tmp').combogrid({
			onSelect: function(index,row){
				selectMobile();
			}
		});
		$( "#btn-view-remove-tmp" ).on('click', function(e) {
			e.preventDefault();
			$('#task-content-tmp').html('');
		});
	});

	function selectMobile(){
		var html = new Array();
		var g = $('#combogrid-tmp').combogrid('grid');	// get datagrid object
		var r = g.datagrid('getSelected');	// get the selected row
		var isExit=false;
		if(r&&r.id){
			$.each($('user'),function(i,obj){
				if($(obj).attr("id")==r.id){
					alert("重复人员。");
					isExit=true;
					return;
				}
			});

			html.push('<div class="layout-user" >');
			html.push('<user id="'+r.id+'" class="badge badge-'+cssColor9[0]+'">');
			html.push(r.name);
			html.push('</user>');
			html.push('</div>');
			if(!isExit){
				$('#task-content-tmp').html($('#task-content-tmp').html()+html.join(''));
			}

		}else{
			alert("请选择人员且编号不能为空。");
		}
	}
	</script>
</body>
</html>