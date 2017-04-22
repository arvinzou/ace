var cfg = {};
cfg.view_load_data_url = contextPath + '/writing/selectWritingByPrimaryKey.do';
cfg.grid_load_data_url = contextPath + '/writing/findWritingList.do';
cfg.grid_add_data_url = contextPath + '/writing/insertWriting.do';
cfg.grid_edit_data_url = contextPath + '/writing/updateWriting.do';
cfg.grid_delete_data_url = contextPath + '/writing/deleteWritingByWritingId.do';
cfg.grid_selector= "#grid-table";
cfg.pager_selector= "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum= default_page_list[0];
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight-layoutTopHeight;
cfg.jgridEditWinWidth=window.innerWidth*0.85;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
cfg.modal='false';
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}
