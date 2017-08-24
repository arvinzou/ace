var cfg = {};
cfg.grid_load_data_url = contextPath + '/group/findGroupList.do';
cfg.grid_add_data_url = contextPath + '/group/insertGroup.do';
cfg.grid_edit_data_url = contextPath + '/group/updateGroup.do';
cfg.grid_delete_data_url = contextPath + '/group/deleteGroupByGroupId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption= "";
cfg.rowNum= 10;
cfg.dataId= 'groupId';
cfg.gridHeight=window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}