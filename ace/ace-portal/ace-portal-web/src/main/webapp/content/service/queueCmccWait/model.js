var _colNames = [ '编号','手机号','姓名','短信内容','入队时间' ];
var _colModel = function() {
	return [ {
		name : 'queueId',
		index : 'queueId',
		width : 200,
		sortable : false,
		editable : false
	},{
		name : 'tel',
		index : 'tel',
		width : 100,
		sortable : false,
		editable : false
	},{
		name : 'name',
		index : 'name',
		width : 60,
		sortable : false,
		editable : false
	},{
		name : 'msg',
		index : 'msg',
		width : 200,
		sortable : false,
		editable : false
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