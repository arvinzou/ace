var cfg = {};
cfg.view_load_data_url = contextPath + '/integrityPublicity/selectIntegrityPublicityByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/integrityPublicity/findIntegrityPublicityList';
cfg.grid_add_data_url = contextPath + '/integrityPublicity/insertIntegrityPublicity';
cfg.grid_edit_data_url = contextPath + '/integrityPublicity/updateIntegrityPublicity';
cfg.grid_delete_data_url = contextPath + '/integrityPublicity/deleteIntegrityPublicityByIntegrityPublicityId';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = 'auto';
cfg.jgridEditWinWidth = 1200;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;