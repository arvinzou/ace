var _colNames = [ '编号', '名称', '模板','创建时间' ];
var _colModel = function() {
	return [ {
		name : 'templateCmccId',
		index : 'templateCmccId',
		width : 150,
		sortable : false,
		editable : true,
		editoptions : {
			style:'width:98%;line-height: 30px;height: 30px;',
			readonly : false
		}
	},{
		name : 'name',
		index : 'name',
		width : 130,
		editable : true,
		editoptions : {
			style:'width:98%;line-height: 30px;height: 30px;',
			maxlength : "30"
		}
	},{
		name : 'content',
		index : 'content',
		width : 350,
		editable : true,
		edittype:"textarea",
		editoptions : {
			rows:"6",
			cols:"10",
			style:'width:98%;line-height: 30px;height: 100px;',
			maxlength : "200"
		}
	}, {
		name : 'createTime',
		width : 150,
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