 var cfg = {};
 cfg.view_load_data_url = contextPath + '/evTask/selectEvTaskByPrimaryKey.do';
cfg.grid_load_data_url = contextPath + '/evTask/findEvTaskList.do';
cfg.grid_add_data_url = contextPath + '/evTask/insertEvTask.do';
cfg.grid_edit_data_url = contextPath + '/evTask/updateEvTask.do';
cfg.grid_delete_data_url = contextPath + '/evTask/deleteEvTaskByEvTaskId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption="";// "评测任务";
cfg.rowNum= 10;
cfg.dataId= 'evTaskId';
cfg.gridHeight=window.innerHeight-373;
cfg.jgridEditWinWidth=1000;
cfg.jgridAlertWidth=window.innerHeight-layoutTopHeight;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}