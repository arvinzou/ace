jQuery(function($) {
    $(window).on('resize.jqGrid', function() {
		resizeJqGrid();
	})
	jQuery(cfg.grid_selector).jqGrid(
			{
			   treeGrid: false,
               	ExpandColumn : 'name',
                treeGridModel: 'adjacency',
                treeReader:{
                        					"parent_id_field":"boss_id",
                        					"level_field":"level",
                        					"leaf_field":"isLeaf",
                        					"expanded_field":"expanded",
                        					"loaded":"loaded",
                        					"icon_field":"icon"
                        				},
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
				rownumbers : false,
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
function style_edit_form(form) {

}
function resizeJqGrid(){
    $(cfg.grid_selector).jqGrid('setGridWidth',$(".portlet-body").width());
}

