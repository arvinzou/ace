var cfg = {};
cfg.grid_load_data_url = contextPath + '/evTemplete/findEvTempleteList.do';
cfg.grid_add_data_url = contextPath + '/evTemplete/insertEvTemplete.do';
cfg.grid_edit_data_url = contextPath + '/evTemplete/updateEvTemplete.do';
cfg.grid_delete_data_url = contextPath + '/evTemplete/deleteEvTempleteByEvTempleteId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption="";// "评测模板";
cfg.rowNum= 10;
cfg.dataId= 'evTempleteId';
cfg.gridHeight=window.innerHeight-373;
cfg.jgridEditWinWidth=950;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}