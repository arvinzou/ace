var cfg = {};
cfg.view_load_data_url = contextPath + '/cases/selectCasesByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/cases/findCasesList';
cfg.grid_add_data_url = contextPath + '/cases/insertCases';
cfg.grid_edit_data_url = contextPath + '/cases/updateCases';
cfg.grid_delete_data_url = contextPath + '/cases/deleteCasesByCasesId';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum= default_page_list[0];
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight-layoutTopHeight;
cfg.jgridEditWinWidth = 1000;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}