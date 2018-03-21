var cfg = {};
cfg.grid_load_data_url = contextPath + '/evCategory/findEvCategoryList.do';
cfg.grid_add_data_url = contextPath + '/evCategory/insertEvCategory.do';
cfg.grid_edit_data_url = contextPath + '/evCategory/updateEvCategory.do';
cfg.grid_delete_data_url = contextPath + '/evCategory/deleteEvCategoryByEvCategoryId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
cfg.caption="";// "评测类别";
cfg.rowNum= 10;
cfg.dataId= 'code';
cfg.gridHeight=window.innerHeight-373;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}