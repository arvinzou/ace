var cfg = {};
cfg.grid_load_data_url = contextPath
		+ '/evTarget/findEvTargetList.do?evTempleteId=' + evTempleteId;
cfg.grid_add_data_url = contextPath + '/evTarget/insertEvTarget.do';
cfg.grid_edit_data_url = contextPath + '/evTarget/updateEvTarget.do';
cfg.grid_delete_data_url = contextPath
		+ '/evTarget/deleteEvTargetByEvTargetId.do';
cfg.grid_selector = "#grid-table";
cfg.pager_selector = "#grid-pager";
cfg.caption = "";// "";
cfg.rowNum = 100;
cfg.dataId = 'evTargetId';
cfg.gridHeight = window.innerHeight - 373;
cfg.jgridEditWinWidth = 550;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
if (cfg.gridHeight < 100) {
	cfg.gridHeight = 250;
}