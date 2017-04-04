var cfg = {};
cfg.grid_load_data_url = contextPath + '/kernel/findDictList.do';
cfg.grid_add_data_url = contextPath + '/kernel/insertDict.do';
cfg.grid_edit_data_url = contextPath + '/kernel/updateDict.do';
cfg.grid_delete_data_url = contextPath + '/kernel/deleteDictByDictId.do';
cfg.grid_selector = "#grid";
cfg.rowNum = 10;
cfg.dataId = 'id';
cfg.gridHeight = window.innerHeight - layoutTopHeight;
cfg.jgridEditWinWidth = 550;
cfg.jgridAlertWidth = 400;
cfg.jgrdInfoDialogWidth = 500;
if (cfg.gridHeight < 100) {
	cfg.gridHeight = 250;
}

jQuery("#treegrid").jqGrid({
   	url: 'NewFile.xml',
	treedatatype: "xml",
	mtype: "POST",
   	colNames:["id","Account","Acc Num", "Debit", "Credit","Balance"],
   	colModel:[
   		{name:'id',index:'id', width:1,hidden:true,key:true},
   		{name:'name',index:'name', width:500},
   		{name:'num',index:'acc_num', width:80, align:"center"},
   		{name:'debit',index:'debit', width:80, align:"right"},		
   		{name:'credit',index:'credit', width:80,align:"right"},		
   		{name:'balance',index:'balance', width:80,align:"right"}		
   	],
	height:'auto',
	pager : "#ptreegrid",
    treeGrid: true,
	ExpandColumn : 'name',
	caption: "Treegrid example"
});
