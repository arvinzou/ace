var cfg = {};
cfg.view_load_data_url = contextPath + '/fopGeHelp/selectFopGeHelpByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/fopGeHelp/findFopGeHelpList';
cfg.grid_add_data_url = contextPath + '/fopGeHelp/insertFopGeHelp';
cfg.grid_edit_data_url = contextPath + '/fopGeHelp/updateFopGeHelp';
cfg.grid_delete_data_url = contextPath + '/fopGeHelp/deleteFopGeHelpByFopGeHelpId';
cfg.grid_audit_data_url = contextPath + '/fopGeHelp/audit';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = "auto";
cfg.jgridEditWinWidth = 950;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
if (cfg.gridHeight < 100) {
    cfg.gridHeight = 250;
}