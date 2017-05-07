var cfg = {};
cfg.view_load_data_url = contextPath + '/normDetail/selectNormDetailByPrimaryKey.do';
cfg.grid_load_data_url = contextPath + '/normDetail/findNormDetailList.do';
cfg.grid_add_data_url = contextPath + '/normDetail/saveOrUpdateNormDetail.do';
cfg.grid_edit_data_url = contextPath + '/normDetail/saveOrUpdateNormDetail.do';
cfg.grid_delete_data_url = contextPath + '/normDetail/deleteNormDetailByNormDetailId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum= 99999
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight-layoutTopHeight;
cfg.jgridEditWinWidth=550;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}