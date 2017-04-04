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
var rowid = 0;
function append() {
	jQuery('#grid').jqGrid('addRowData', rowid, {});
	rowid += 1;

}
function my_input(value, options) {
	var el = document.createElement("input");
	el.type = "text";
	el.value = value;
	return el;

}
function my_value(value) {
	return "My value: " + value.val();
}
$(function() {
	jQuery('#grid').jqGrid({
		url : '',
		cellEdit : true,
		cellsubmit : 'clientArray',
		datatype : "json",
		jsonReader : {
			repeatitems : false,
			root : "list"
		},
		height : 150,
		width:800,
		caption : 'jqGrid单元格编辑动态设置editoptions dataUrl实现select联动示例',
		colNames : [ 'ID', '省', '市', 'provid', 'cityid' ],
		formatCell : function(rowid, cellname, value, iRow, iCol) {
			var rec = $('#grid').jqGrid('getRowData', rowid);
			var type = $("input[name=itype]").val();
			console.log(value);
			if (type == '9') {
				$('#grid').jqGrid('setColProp', 'cityname', {
					edittype : 'custom',
					editoptions : {

					}
				});
			} else {
				// $('#grid').jqGrid('setColProp', 'cityname',
				// {edittype:'select', editoptions: { dataUrl:
				// 'prov.txt?provid=' + rec.provid} });
				$('#grid').jqGrid('setColProp', 'cityname', {
					edittype : "combotree",
					editoptions : {
						style:'width:175px;line-height: 25px;height: 25px;'
					},
					dataoptions:{
						url: contextPath +'/system/selectProvinceTreeList.do',
						required:false
					}
				});
			}

		},
		afterSaveCell : function(rowid, cellname, value, iRow, iCol) {
			if (cellname == 'cityname') { // 为联动的select列编辑，更新cityid，传入的value桉树为option的value，不是text
				$('#grid').jqGrid('setRowData', rowid, {
					cityid : value
				});
			}
		},
		colModel : [ {
			name : 'id'
		}, {
			name : 'provname',
			editable : true,
			edittype : 'select',
			editoptions : {
				dataUrl : 'prov.txt'
			}
		}, {
			name : 'cityname',
			editable : true,
			edittype : "combotree",
			editoptions : {
				style:'width:175px;line-height: 25px;height: 25px;'
			},
			dataoptions:{
				url: contextPath +'/system/selectProvinceTreeList.do',
				required:false
			}
		},
		// 需要添加这2个隐藏列，要不getRowData获取不到附加的行数据，只能获取到colModel中配置的
		{
			name : 'provid',
			hidden : true
		}, {
			name : 'cityid',
			hidden : true
		} ]
	});
});