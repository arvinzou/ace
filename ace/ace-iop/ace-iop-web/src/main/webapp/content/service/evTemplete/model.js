var _colNames = [ '编号', '模板名称', '模板类型', '模板描述', '模板样式', '创建时间' ];
var _colModel = function() {
	return [
			{
				name : 'evTempleteId',
				index : 'evTempleteId',
				width : 100,
				sortable : false,
				editable : false,
				editoptions : {
					readonly : true
				}
			},
			{
				name : 'evName',
				width : 100,
				editable : true,
				editoptions : {
					size : "51",
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
					return rsd(value, "STATIC_DATA_20");
				},
				editoptions : {
					value : odparse("STATIC_DATA_20")
				}
			},
			{
				name : 'evDiscription',
				width : 100,
				editable : true,
				editoptions : {
					size : "20",
					maxlength : "50"
				},
				sortable : true,
				hidden:true,
				edittype : "textarea",
				editoptions : {
					rows : "12",
					cols : "50"
				}
			},
			{
				name : 'evContent',
				width : 100,
				hidden:true,
				editable : true,
				sortable : true,
				edittype : "textarea",
				editoptions : {
					rows : "12",
					cols : "50"
				}
			}, {
				name : 'createTime',
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