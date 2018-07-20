var cfg = {};
cfg.view_load_data_url = contextPath + '/counselor/selectCounselorByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/counselor/findCounselorList';
cfg.grid_add_data_url = contextPath + '/counselor/insertCounselor';
cfg.grid_edit_data_url = contextPath + '/counselor/updateCounselor';
cfg.grid_delete_data_url = contextPath + '/counselor/deleteCounselorByCounselorId';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth = 900;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
if (cfg.gridHeight < 100) {
    cfg.gridHeight = 250;
}