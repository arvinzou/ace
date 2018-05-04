var cfg = {};
cfg.view_load_data_url = contextPath + '/fopNotice/selectFopNoticeByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/fopNotice/findFopNoticeList';
cfg.grid_add_data_url = contextPath + '/fopNotice/insertFopNotice';
cfg.grid_edit_data_url = contextPath + '/fopNotice/updateFopNotice';
cfg.grid_delete_data_url = contextPath + '/fopNotice/deleteFopNoticeByFopNoticeId';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth = 1000;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
if (cfg.gridHeight < 100) {
    cfg.gridHeight = 250;
}