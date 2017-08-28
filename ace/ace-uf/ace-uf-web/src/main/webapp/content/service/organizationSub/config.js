var cfgsub = {};
cfgsub.view_load_data_url = contextPath + '/organizationSub/selectOrganizationSubByPrimaryKey.do';
cfgsub.grid_load_data_url = contextPath + '/organizationSub/findOrganizationSubList.do';
cfgsub.grid_add_data_url = contextPath + '/organizationSub/insertOrganizationSub.do';
cfgsub.grid_edit_data_url = contextPath + '/organizationSub/updateOrganizationSub.do';
cfgsub.grid_delete_data_url = contextPath + '/organizationSub/deleteOrganizationSubByOrganizationSubId.do';
cfgsub.grid_selector= "#grid-table-sub";
cfgsub.pager_selector= "#grid-pager-sub";
//cfgsub.caption= "参数";
cfgsub.modal=false;
cfgsub.rowNum= default_page_list[0];
cfgsub.dataId= 'id';
cfgsub.gridHeight=300;
cfgsub.jgridEditWinWidth=550;
cfgsub.jgridAlertWidth=400;
cfgsub.jgrdInfoDialogWidth=500;
if(cfgsub.gridHeight<100){
	cfgsub.gridHeight=250;
}