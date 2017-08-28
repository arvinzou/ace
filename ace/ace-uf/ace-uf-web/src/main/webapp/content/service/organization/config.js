var cfg = {};
cfg.view_load_data_url = contextPath + '/organization/selectOrganizationByPrimaryKey.do';
cfg.grid_load_data_url = contextPath + '/organization/findOrganizationList.do';
cfg.grid_add_data_url = contextPath + '/organization/insertOrganization.do';
cfg.grid_edit_data_url = contextPath + '/organization/updateOrganization.do';
cfg.grid_delete_data_url = contextPath + '/organization/deleteOrganizationByOrganizationId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
//cfg.caption= "参数";
cfg.modal=false;
cfg.rowNum= default_page_list[0];
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight-layoutTopHeight;
cfg.jgridEditWinWidth=950;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}