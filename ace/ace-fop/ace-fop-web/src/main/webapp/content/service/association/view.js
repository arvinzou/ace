jQuery(function($) {
    $(window).on('resize.jqGrid', function() {
		resizeJqGrid();
	})
	jQuery(cfg.grid_selector).jqGrid(
			{
				prmNames : {
					totalRecord : "totalRecord",
					start : "start",
					page : "page",
					rows : "limit"
				},
				datatype : "json",
				postData: { status: 2},
                formData: { status: 2},
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
				multiselect : false,
				multiboxonly : true,
				shrinkToFit : true,
				autoScroll : false,
				loadComplete : function() {
					resizeJqGrid();
				},
				editurl : cfg.grid_edit_data_url,// nothing is saved
				addurl : cfg.grid_add_data_url,
				deleteurl : cfg.grid_delete_data_url,
				caption : cfg.caption
			});
			});

function resizeJqGrid(){
    $(cfg.grid_selector).jqGrid('setGridWidth',$(".portlet-body").width());
}