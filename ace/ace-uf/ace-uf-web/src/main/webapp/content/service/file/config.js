var cfg = {};
//cfg.view_load_data_url = contextPath + '/file/selectPersonageByPrimaryKey.do';
/*获取文件列表*/
cfg.grid_load_data_url = contextPath + '/file/findFilesList.do';
/*
* 添加新文件*/
cfg.grid_add_data_url = contextPath + '/file/insertFile.do';
/*文件信息的更新
 */
cfg.grid_edit_data_url = contextPath + '/file/updateFile.do';
/*删除文件*/
cfg.grid_delete_data_url = contextPath + '/file/deleteFileByFileId.do';
cfg.grid_selector= "#grid-table";

cfg.pager_selector= "#grid-pager";
//cfg.caption= "参数";
cfg.rowNum= default_page_list[0];
cfg.dataId= 'id';
cfg.gridHeight=window.innerHeight-layoutTopHeight;
cfg.jgridEditWinWidth=950;
cfg.jgridAlertWidth=400;
cfg.jgrdInfoDialogWidth=500;
if(cfg.gridHeight<100){
	cfg.gridHeight=250;
}