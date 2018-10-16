var cfg = {};
cfg.view_load_data_url = contextPath + '/cuProject/selectCuProjectByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/cuProject/findCuProjectList';
cfg.grid_add_data_url = contextPath + '/cuProject/insertCuProject';
cfg.grid_edit_data_url = contextPath + '/cuProject/updateCuProject';
cfg.grid_delete_data_url = contextPath + '/cuProject/deleteCuProjectByCuProjectId';
//custom
cfg.addUseRecord_url = contextPath + '/cuProject/addUseRecord';
cfg.audit_url = contextPath + '/cuProject/addUseRecord';
cfg.setup_url = contextPath + '/cuProject/setup';
cfg.shutdown_url = contextPath + '/cuProject/shutDown';
//===================
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = 'auto';
cfg.jgridEditWinWidth = 950;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;