var cfg = {};
cfg.view_load_data_url = contextPath + '/norm/selectNormByPrimaryKey.do';
cfg.grid_load_data_url = contextPath + '/norm/findNormList.do';
cfg.grid_add_data_url = contextPath + '/norm/insertNorm.do';
cfg.grid_edit_data_url = contextPath + '/norm/updateNorm.do';
cfg.grid_delete_data_url = contextPath + '/norm/deleteNormByNormId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum= default_page_list[0];
cfg.dataId= 'id';
cfg.gridHeight='auto';
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;