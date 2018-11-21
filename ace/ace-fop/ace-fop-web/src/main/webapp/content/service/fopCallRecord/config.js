var cfg = {};
cfg.view_load_data_url = contextPath + '/fopCallRecord/selectFopCallRecordByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/fopCallRecord/findFopCallRecordList';
cfg.grid_add_data_url = contextPath + '/fopCallRecord/insertFopCallRecord';
cfg.grid_edit_data_url = contextPath + '/fopCallRecord/updateFopCallRecord';
cfg.grid_delete_data_url = contextPath + '/fopCallRecord/deleteFopCallRecordByFopCallRecordId';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth = 700;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
if (cfg.gridHeight < 100) {
    cfg.gridHeight = 250;
}