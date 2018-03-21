var _colNames = [ '编号', '名称','说明', '创建时间' ];
var _colModel = function() {
	return [
			{
				name : 'id',
				index : 'id',
				width : 100,
				sortable : false,
				editable : true,
				editoptions : {
					readonly : true
				}
			},

			{
				name : 'name',
				index : 'name',
				width : 80,
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
				name : 'remark',
				width : 100,
				edittype : "textarea",
				editable : true,
				hidden : false,
				editoptions : {
					rows : "6",
					cols  : "45"
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