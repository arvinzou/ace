var _colNames = [ '编号','KEY','所属系统','类型', '名称', '值','创建时间', '备注' ];
var _colModel = function() {
	return [{
		name : 'id',
		index : 'id',
		width : 100,
		sortable : false,
		editable : true,
		hidden:true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'configKey',
		index : 'configKey',
		width : 100,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'syid',
		index : 'syid',
		width : 100,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "08");
		},
		editoptions : {
			value : odparse("08")
		}
	}, {
		name : 'category',
		index : 'category',
		width : 60,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "30");
		},
		editoptions : {
			value : odparse("30")
		}
	},{
		name : 'configName',
		index : 'configName',
		width : 130,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	},{
		name : 'configValue',
		index : 'configValue',
		width : 200,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "50"
		},
		formoptions : {
			elmprefix : "",
			elmsuffix : "<span style='color:red;font-size:16px;font-weight:800'>*</span>"
		},
		editrules : {
			required : true
		}
	}, {
		name : 'createTime',
		width : 150,
		sortable : true,
		editable : false
	}, {
		name : 'remark',
		index : 'remark',
		width : 100,
		editable : true,
		editoptions : {
			size : "20",
			maxlength : "30"
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