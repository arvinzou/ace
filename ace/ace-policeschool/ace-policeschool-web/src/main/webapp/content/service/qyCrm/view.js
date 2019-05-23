function initGrid() {
    $(window).on('resize.jqGrid', function () {
        resizeJqGrid();
    })
    jQuery(cfg.grid_selector).jqGrid(
        {
            prmNames: {
                totalRecord: "totalRecord",
                start: "start",
                page: "page",
                rows: "limit"
            },
            datatype: "json",
            postData: {},
            formData: {},
            url: cfg.grid_load_data_url,
            jsonReader: {
                root: "rows",
                page: "page",
                total: "totalPages",
                records: "total",
                id: cfg.dataId
            },
            height: cfg.gridHeight,
            colNames: _colNames,
            colModel: _colModel(),
            rownumbers: true,
            viewrecords: true,
            rowNum: cfg.rowNum,
            rowList: default_page_list,
            pager: cfg.pager_selector,
            altRows: true,
            multiselect: false,
            multiboxonly: true,
            shrinkToFit: true,
            autoScroll: false,
            loadComplete: function(a,b,c) {
                resizeJqGrid();
                jQuery("#gridTable").jqGrid('setLabel', 'rn', '序号', {
                    'text-align': 'center',
                    'vertical-align': 'middle',
                    "width": "50"
                });
                $("table[role='grid']").each(function () {//jqgrid 创建的表格都有role属性为grid
                    $('.' + $(this).attr("class") + ' tr:first th:first').css("width", "50"); //使表头的序号列宽度为40
                    $('.' + $(this).attr("class") + ' tr:first td:first').css("width", "50"); // 使表体的序号列宽度为40
                });
            },
            editurl: cfg.grid_edit_data_url,// nothing is saved
            addurl: cfg.grid_add_data_url,
            deleteurl: cfg.grid_delete_data_url,
            caption: cfg.caption
        });
    resizeJqGrid();
};

function resizeJqGrid() {
    $(cfg.grid_selector).jqGrid('setGridWidth', $(".portlet-body").width());
}