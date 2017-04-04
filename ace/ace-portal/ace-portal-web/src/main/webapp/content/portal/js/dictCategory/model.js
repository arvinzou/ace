var _colNames = [ '类型编号', '所属系统','名称',  '自定义','创建时间' ];
var _colModel = function() {
	return [ {
		name : 'categoryId',
		index : 'categoryId',
		width : 6,
		sortable : false,
		editable : true,
		editoptions : {
			readonly : true
		}
	}, {
		name : 'syid',
		index : 'syid',
		width : 60,
		editable : false,
		edittype : "select",
		renderer : function(value) {
			return rsd(value, "08");
		},
		editoptions : {
			value : odparse("08")
		}
	}, {
		name : 'name',
		index : 'name',
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
	}, {
		name : 'remark',
		index : 'remark',
		width : 10,
		editable : true,
		edittype : "textarea",
		editoptions : {
			style:'width:350px;height: 50px;',
			size : "20",
			maxlength : "200"
		}
	}, {
		name : 'createTime',
		width : 10,
		sortable : true,
		editable : false
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