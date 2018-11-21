var cfg = {};
cfg.view_load_data_url = contextPath + '/informationService/selectInformationServiceByPrimaryKey';
cfg.grid_load_data_url = contextPath + '/informationService/findInformationServiceList';
cfg.grid_add_data_url = contextPath + '/informationService/insertInformationService';
cfg.grid_edit_data_url = contextPath + '/informationService/updateInformationService';
cfg.grid_delete_data_url = contextPath + '/informationService/deleteInformationServiceByInformationServiceId';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum = default_page_list[0];
cfg.dataId = 'id';
cfg.gridHeight = 'auto';
cfg.jgridEditWinWidth = 1200;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
function isNull(arg1) {
    return !arg1 && arg1 !== 0 && typeof arg1 !== "boolean" ? true : false;
}
