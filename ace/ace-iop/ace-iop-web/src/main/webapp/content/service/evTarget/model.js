var _colNames = ['指标序号', '所属模板序号', '指标名称', '指标模板', '指标分值', '指标等次分值', '评价要点',
		'统计方式', '创建时间'];
var _colModel = function() {
	return [
			{
				name : 'evTargetId',
				index : 'evTargetId',
				width : 100,
				sortable : false,
				hidden : true,
				editable : true,
				editoptions : {
					readonly : true
				}
			},

			{
				name : 'evTempleteId',
				width : 100,
				hidden : true,
				editable : true,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'evTargetName',
				width : 150,
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
			},
			{
				name : 'category',
				index : 'category',
				width : 100,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_52");
				},
				editoptions : {
					value : odparse("STATIC_DATA_52")
				}
			},
			{
				name : 'argetScore',
				width : 50,
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
			},
			{
				name : 'scoreType',
				width : 80,
				editable : false,
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
			},
			{
				name : 'evPoints',
				width : 200,
				editable : false,
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
				name : 'staType',
				width : 60,
				editable : true,
				edittype : "select",
				renderer : function(value) {
					return rsd(value, "STATIC_DATA_51");
				},
				editoptions : {
					value : odparse("STATIC_DATA_51")
				}
			}, {
				name : 'createTime',
				sortable : true,
				editable : false
			}];
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