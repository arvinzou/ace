function initEvent(){
    $('#btn-search').on('click', function () {
        $('#fm-search').ajaxForm({
            beforeSubmit: function (formData, jqForm, options) {
                var params = {};
                $.each(formData, function (n, obj) {
                    params[obj.name] = obj.value;
                });
                $.extend(params, {
                    time: new Date()
                });

                jQuery(cfg.grid_selector).jqGrid('setGridParam', {
                    page: 1,
                    postData: params
                }).trigger("reloadGrid");
                return false;
            }
        });
    });

    $('#btn-view-add').on('click', function () {
        jQuery(cfg.grid_selector).jqGrid(
            'editGridRow',
            'new',
            {
                closeAfterAdd: true,
                recreateForm: true,
                viewPagerButtons: false,
                beforeShowForm: function (e) {
                    var form = $(e[0]);
                    form.closest('.ui-jqdialog').find(
                        '.ui-jqdialog-titlebar').wrapInner(
                        '<div class="widget-header" />')
                }
            })
    });

}
function initGrid(){
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
				postData: {companyId: urlParams.companyId},
                formData: {companyId: urlParams.companyId},
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
				pager : 'false',
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

			initEvent();

}

function resizeJqGrid(){
    $(cfg.grid_selector).jqGrid('setGridWidth',$(".portlet-body").width());
}