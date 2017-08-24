var _colNames = [ '组编号', '名称', 'SQL','创建时间', '备注' ];
var _colModel = function() {
	return [ {
		name : 'groupId',
		index : 'groupId',
		width : 100,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : false
		}
	},{
		name : 'groupName',
		index : 'groupName',
		width : 150,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		}
	},{
		name : 'sqlText',
		index : 'sqlText',
		width : 350,
		editable : true,
		hidden:true,
		edittype : "textarea",
		editoptions : {
			style:'width:300px;height: 150px;',
			size : "20",
			maxlength : "500"
		}
	}, {
		name : 'createTime',
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'remark',
		index : 'remark',
		width : 150,
		editable : true,
		edittype : "textarea",
		editoptions : {
			style:'width:175px;height: 150px;',
			size : "20",
			maxlength : "500"
		}
	} ];
}
function aceSwitch(cellvalue, options, cell) {
	console.log('aceSwitch');
	setTimeout(function() {
		$(cell).find('input[type=checkbox]').addClass(
				'ace ace-switch ace-switch-5').after(
				'<span class="lbl"></span>');
	}, 0);
}
// enable datepicker
function pickDate(cellvalue, options, cell) {
	setTimeout(function() {
		$(cell).find('input[type=text]').datepicker({
			format : 'yyyy-mm-dd',
			autoclose : true
		});
	}, 0);
}