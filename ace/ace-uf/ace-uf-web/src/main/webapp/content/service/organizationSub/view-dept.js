function initSubGrid() {

	var html = [];
	html.push('');
	html.push('<div id="toolbar" class="toolbar center">');

	html.push('<button class="btn btn-info" id="btn-sub-view-add"');
	html.push('authority="false">');
	html.push('添加 <i');
	html
			.push('class="ace-icon fa fa-plus-square  align-middle bigger-125 icon-on-right"></i>');
	html.push('</button>');

	html.push('<button class="btn btn-warning" id="btn-sub-view-del"');
	html.push('authority="false">');
	html.push('删除 <i');
	html
			.push('class="ace-icon glyphicon  glyphicon-remove  align-middle bigger-125 icon-on-right"></i>');
	html.push('</button>');

	html.push('</div>');
	html.push('<table id="grid-table-sub"></table>');

	$("#TblGrid_grid-table").after(html.join(""));
	var gr = jQuery(cfg.grid_selector).jqGrid('getGridParam',
    						'selrow');
    var r = jQuery(cfg.grid_selector).jqGrid('getRowData', gr);

	var organizationId=r.departmentId;

	jQuery(cfgsub.grid_selector).jqGrid(
			{
				cfg : cfgsub,

				prmNames : {
					totalRecord : "totalRecord",
					start : "start",
					page : "page",
					rows : "limit"
				},
				datatype : "json",
				url : cfgsub.grid_load_data_url,
				jsonReader : {
					root : "rows",
					page : "page",
					total : "totalPages",
					records : "total",
					id : cfgsub.dataId
				},
				postData:{organizationId:organizationId},
				height : cfgsub.gridHeight,
				colNames : _colNamesSub,
				colModel : _colModelSub(),
				rownumbers : true,
				viewrecords : true,
				rowNum : cfgsub.rowNum,
				rowList : default_page_list,
				pager : cfgsub.pager_selector,
				altRows : true,
				multiselect : false,
				multiboxonly : true,
				shrinkToFit : true,
				autoScroll : false,
				loadComplete : function() {
				    $(cfgsub.grid_selector).jqGrid('setGridWidth', cfg.jgridEditWinWidth-5);
				},
				editurl : cfgsub.grid_edit_data_url,// nothing is saved
				addurl : cfgsub.grid_add_data_url,
				deleteurl : cfgsub.grid_delete_data_url,
				caption : cfgsub.caption
			});
			$(cfgsub.grid_selector).jqGrid('setGridWidth', cfg.jgridEditWinWidth);
			initController();
}