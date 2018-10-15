jQuery(function($) {
    $(window).on('resize.jqGrid', function() {
		resizeJqGrid();
	})
	jQuery(cfg.grid_selector).jqGrid({
		prmNames : {
			totalRecord : "totalRecord",
			start : "start",
			page : "page",
			rows : "limit"
		},
		datatype : "json",
		url : cfg.grid_load_data_url,
		jsonReader : {
			root : "rows",
			page : "page",
			total : "totalPages",
			records : "total",
			id : cfg.dataId
		},
		height : cfg.gridHeight,
		colNames : _colNames,
		colModel : _colModel(),
		rownumbers : true,
		viewrecords : true,
		rowNum : cfg.rowNum,
		rowList : default_page_list,
		pager : cfg.pager_selector,
		altRows : true,
		shrinkToFit:true,
		autoScroll: false,
		multiselect : false,
		multiboxonly : true,
		loadComplete : function() {
            resizeJqGrid();
		},
		editurl : cfg.grid_edit_data_url,
		addurl : cfg.grid_add_data_url,
		deleteurl : cfg.grid_delete_data_url,
		caption : cfg.caption
		});

	jQuery("#allrole-grid-table").jqGrid({
				prmNames : {
					totalRecord : "totalRecord",
					start : "start",
					page : "page",
					rows : "limit"
				},
				datatype : "json",
				url : contextPath + "/users/selectRoleList.do",
				jsonReader : {
					root : "rows",
					page : "page",
					total : "totalPages",
					records : "total",
					id : 'roleId'
				},
				multiselect : true,
				multiboxonly : true,
				rownumbers : true,
				viewrecords : true,
				rowNum : 100,
				rowList : [10, 20, 30, 50],
				height : 200,
				width:350,
				pager : 'false',
				colNames : ['角色编号', '角色名称'],
				colModel : [{
							name : 'roleId',
							hidden:true,
							width : 90
						}, {
							name : 'roleName',
							width : 200
						}],
				caption : '可分配',
				loadComplete:function(){
					$("#allrole-grid-table").jqGrid('setGridWidth', $("#allrole-grid-table").width());
				}
			});

	jQuery("#myrole-grid-table").jqGrid({
				prmNames : {
					totalRecord : "totalRecord",
					start : "start",
					page : "page",
					rows : "limit"
				},
				datatype : "json",
				url : '',
				jsonReader : {
					root : "rows",
					page : "page",
					total : "totalPages",
					records : "total",
					id : 'roleId'
				},
				multiselect : true,
				multiboxonly : true,
				rownumbers : true,
				viewrecords : true,
				rowNum : 10,
				rowList : [10, 20, 30, 50],
				height : 200,
				width:350,
				pager : 'false',
				colNames : ['角色编号', '角色名称'],
				colModel : [{
							name : 'roleId',
							hidden:true,
							width : 90
						}, {
							name : 'roleName',
							width : 200
						}],
				caption : '已分配',
				loadComplete:function(){
					$("#myrole-grid-table").jqGrid('setGridWidth', $("#myrole-grid-table").width());
				}
			});


});
function resizeJqGrid(){
    $(cfg.grid_selector).jqGrid('setGridWidth',$(".portlet-body").width());
}