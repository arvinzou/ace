var cfg = {};
cfg.grid_load_data_url = contextPath + '/evScoreTemleteSub/findEvScoreTemleteSubList.do';
cfg.grid_add_data_url = contextPath + '/evScoreTemleteSub/insertEvScoreTemleteSub.do';
cfg.grid_edit_data_url = contextPath + '/evScoreTemleteSub/updateEvScoreTemleteSub.do';
cfg.grid_delete_data_url = contextPath + '/evScoreTemleteSub/deleteEvScoreTemleteSubByEvScoreTemleteSubId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption="";// "指标选项";
cfg.rowNum= 10;
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight-373;
cfg.jgridEditWinWidth=700;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}