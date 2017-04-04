var _colNames = [ '角色编号', '所属系统','角色名称','类型', '创建时间', '备注' ];
var _colModel = function() {
	return [ {
		name : 'roleId',
		index : 'id',
		width : 8,
		hidden:true,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'syid',
		index : 'syid',
		width : 8,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "08");
		},
		editoptions : {
			value : odparse("08")
		}
	}, {
		name : 'roleName',
		index : 'roleName',
		width : 10,
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
		name : 'type',
		width : 5,
		editable : true,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "64");
		},
		editoptions : {
			value : odparse("64")
		}
	}, {
		name : 'createTime',
		width : 10,
		sortable : true,
		editable : false
	}, {
		name : 'remark',
		index : 'remark',
		width : 10,
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