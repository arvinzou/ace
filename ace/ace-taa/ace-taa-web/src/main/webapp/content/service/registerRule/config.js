var cfg = {};
cfg.view_load_data_url = contextPath + '/registerRule/selectRegisterRuleByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/registerRule/findRegisterRuleList';
cfg.grid_add_data_url = contextPath + '/registerRule/insertRegisterRule';
cfg.grid_edit_data_url = contextPath + '/registerRule/updateRegisterRule';
cfg.grid_delete_data_url = contextPath + '/registerRule/deleteRegisterRuleByRegisterRuleId';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = 'auto';
cfg.jgridEditWinWidth = 800;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
